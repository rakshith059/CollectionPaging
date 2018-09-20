package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Constants


class ElementWebViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var boundedId: String? = null
    var webView: WebView? = null

    companion object {
        fun create(parent: ViewGroup): ElementWebViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_web_element_holder, parent, false)
            var holder: ElementWebViewHolder = ElementWebViewHolder(view)
            holder.webView = view.findViewById<WebView>(R.id.story_web_element_holder_webview)
            holder.webView?.settings?.javaScriptEnabled = true
            holder.webView?.settings?.setSupportZoom(false)
            holder.webView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            //this makes sure that long click never gives text selection on webview
            holder.webView?.setOnLongClickListener(View.OnLongClickListener {
                //do nothing
                true
            })

            return ElementWebViewHolder(view)
        }
    }

    fun bind(element: StoryElement) {
        if (!boundedId.equals(element.id(), true)) {
            boundedId = element.id()
            webView?.loadUrl("about:blank")
            loadWebView(element)
            webView?.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    val uriClicked = Uri.parse(url)
                    return true
                }
            }
        } else {
            Log.d("Rakshith", "Skipping soundcloud binding to same elem $boundedId")
        }
    }

    private fun loadWebView(elem: StoryElement) {
        val loadedUrl = webView?.getTag() as String
        val urlToLoad = Constants.BASE_URL + elem.pageUrl()
        if (urlToLoad != loadedUrl) {
            webView?.setTag(urlToLoad)
            Log.d("Force loading url %s", elem.pageUrl())
            webView?.loadUrl(urlToLoad)
        } else {
            Log.d("Skip loading url %s", elem.pageUrl())
        }
    }
}
