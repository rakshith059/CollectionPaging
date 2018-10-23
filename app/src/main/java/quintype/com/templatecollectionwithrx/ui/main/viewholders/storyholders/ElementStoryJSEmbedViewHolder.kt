package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.story.StoryElement
import quintype.com.templatecollectionwithrx.Constants
import java.util.regex.Pattern


class ElementStoryJSEmbedViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var jsEmbedWebView: WebView? = null
    var boundedId: String? = null

    companion object {
        fun create(parent: ViewGroup): ElementStoryJSEmbedViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_js_embed_view_holder, parent, false)
            val elementStoryJSEmbedViewHolder = ElementStoryJSEmbedViewHolder(view)
            elementStoryJSEmbedViewHolder.jsEmbedWebView = view.findViewById(R.id.story_js_embed_view_holder_wv)

            elementStoryJSEmbedViewHolder.jsEmbedWebView?.settings?.javaScriptEnabled = true
            elementStoryJSEmbedViewHolder.jsEmbedWebView?.settings?.setSupportZoom(false)
            elementStoryJSEmbedViewHolder.jsEmbedWebView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            //this makes sure that long click never gives text selection on webview
            elementStoryJSEmbedViewHolder.jsEmbedWebView?.setOnLongClickListener(View.OnLongClickListener {
                //do nothing
                true
            })
            return elementStoryJSEmbedViewHolder
        }
    }

    fun bind(element: StoryElement) {
        if (!boundedId.equals(element.id())) {
            boundedId = element.id()
            loadWebView(element)
            jsEmbedWebView?.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    val uriClicked = Uri.parse(url)
                    return true
                }
            }
        } else {
            Log.d("Rakshith", "Bounded Id is $boundedId")
        }
    }

    /**
     * Load {@link StoryElement} into WebView
     *
     * @param elem
     */
    private fun loadWebView(elem: StoryElement) {
        val loadedUrl = jsEmbedWebView?.tag?.toString()
        val urlToLoad = Constants.BASE_URL + elem.pageUrl()
        if (urlToLoad != loadedUrl) {
            jsEmbedWebView?.tag = urlToLoad
            val htmlContent = jsEmbedWebView?.context?.getString(R.string.frameless_html_css)
            Log.d("Force loading url %s", elem.pageUrl())
            if (!TextUtils.isEmpty(elem.decodedJsEmbed())) {
                val jsEmbedd = fixAspectRatio(elem.decodedJsEmbed())
                val htmlData = htmlContent?.replace("replace".toRegex(), jsEmbedd)

                Log.d("Data is %s ", htmlData)
                jsEmbedWebView?.loadDataWithBaseURL("http://localhost/", htmlData, "text/html; " + "charset=UTF-8", "UTF-8", null)
            } else {
                jsEmbedWebView?.loadUrl(urlToLoad)
            }
        } else {
            Log.d("Skip loading url %s", elem.pageUrl())
        }
    }

    private fun fixAspectRatio(jsEmbedd: String): String {
        var jsEmbedd = jsEmbedd
        Log.d("Rakshith", "Matcher Initial string = $jsEmbedd")
        val patternWidth = Pattern.compile("width=\"\\d+\"")
        var m = patternWidth.matcher(jsEmbedd)
        var widthChar: String? = null
        while (m.find() && widthChar == null) {
            System.out.println(m.group())
            Log.d("Rakshith", "Matcher found width = " + m.group())
            widthChar = m.group()
        }
        val patternHeight = Pattern.compile("height=\"\\d+\"")
        m = patternHeight.matcher(jsEmbedd)
        var heightChar: String? = null
        while (m.find() && heightChar == null) {
            System.out.println(m.group())
            Log.d("Rakshith", "Matcher width = " + m.group())
            heightChar = m.group()
        }

        if (!TextUtils.isEmpty(widthChar) && !TextUtils.isEmpty(heightChar)) {
            widthChar = widthChar?.replace("[^0-9]".toRegex(), "")
            heightChar = heightChar?.replace("[^0-9]".toRegex(), "")
            val orignalWidth = Integer.parseInt(widthChar)
            val orignalHeight = Integer.parseInt(heightChar)

            val dm = jsEmbedWebView?.context?.resources?.displayMetrics
            val wm = jsEmbedWebView?.context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(dm)
            var newWidth = (dm?.widthPixels?.toFloat()?.div(dm?.density))?.toInt() as Int
            // factoring in right and left padding
            newWidth -= 2 * 8
            val aspectRatio = orignalHeight.toFloat() / orignalWidth
            val newHeight = (aspectRatio * newWidth).toInt()
            Log.d("Rakshith", "height = $newHeight , width = $newWidth")
            jsEmbedd = jsEmbedd.replace(("" + orignalHeight).toRegex(), "" + newHeight)
            jsEmbedd = jsEmbedd.replace(("" + orignalWidth).toRegex(), "" + newWidth)

        }
        return jsEmbedd
    }

}
