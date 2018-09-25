package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities

class SlideShowImageHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var ivSlideShowImage: ImageView? = null
    var tvSlideShowImageTitle: TextView? = null

    companion object {

        fun create(view: View, mImageWidth: Int, height: Int?): SlideShowImageHolder {
            val slideShowImageHolder = SlideShowImageHolder(view)
            val displayWidth = Constants.getScreenWidth(view.context)
            val panelHeight = (displayWidth * 0.75).toInt()

            slideShowImageHolder.ivSlideShowImage = view.findViewById(R.id.item_story_element_slideshow_image_iv_hero_icon)
            slideShowImageHolder.tvSlideShowImageTitle = view.findViewById(R.id.item_story_element_slideshow_image_tv_image_title)

            slideShowImageHolder.ivSlideShowImage?.layoutParams = Utilities.createLayoutParams(slideShowImageHolder.ivSlideShowImage as ImageView, mImageWidth, panelHeight)

//            val panel = view as ConstraintLayout
//            val params = panel.layoutParams
//            params.height = panelHeight
//            params.width = mImageWidth
//            panel.layoutParams = params

            return slideShowImageHolder
        }
    }

    fun bind(storyElement: StoryElement) {
        if (!TextUtils.isEmpty(storyElement.title())) {
            tvSlideShowImageTitle?.visibility = View.VISIBLE
            tvSlideShowImageTitle?.text = Utilities.parseHtml(storyElement.title())
        } else {
            tvSlideShowImageTitle?.visibility = View.GONE
        }

        val cdnHostName = Constants.getSharedPreferences(ivSlideShowImage?.context as Context, Constants.SP_CDN_IMAGE_NAME)
        val heroImageURL = cdnHostName + storyElement.imageS3Key()

        Glide.with(ivSlideShowImage?.context as Context)
                .load(heroImageURL)
                .into(ivSlideShowImage as ImageView)
    }
}
