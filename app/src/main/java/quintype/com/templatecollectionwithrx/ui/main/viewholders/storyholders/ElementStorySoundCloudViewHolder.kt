package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Utilities


class ElementStorySoundCloudViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private var webView: WebView? = null
    var boundedId: String? = null

    companion object {
        fun create(parent: ViewGroup): ElementStorySoundCloudViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_web_element_holder, parent, false)
            val holder = ElementStorySoundCloudViewHolder(view)
            holder.webView = view.findViewById(R.id.story_web_element_holder_webview)
            holder.webView?.settings?.javaScriptEnabled = true
            holder.webView?.settings?.setSupportZoom(false)
            holder.webView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            //this makes sure that long click never gives text selection on webview
            holder.webView?.setOnLongClickListener(View.OnLongClickListener {
                //do nothing
                true
            })
            return holder
        }
    }

    fun bind(element: StoryElement) {
        if (!boundedId.equals(element.id(), true)) {
            boundedId = element.id()
            var html = webView?.resources?.getString(R.string.soundcloud_html)
            html = html?.replace("replace", element.embedUrl())
            webView?.loadData(html, "text/html; charset=UTF-8", "UTF-8")

            val title = Utilities.parseHtml(element.title())
        } else {
            Log.d("Rakshith", "Skipping soundcloud binding to same elem $boundedId")
        }
    }
}
