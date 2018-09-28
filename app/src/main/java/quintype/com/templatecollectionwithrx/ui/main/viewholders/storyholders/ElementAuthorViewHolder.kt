package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class ElementAuthorViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): ElementAuthorViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_element_author_view_holder, parent, false)
            val elementAuthorViewHolder = ElementAuthorViewHolder(view)
            return elementAuthorViewHolder
        }
    }

    fun bind(mStory: Story) {
        val view = this.itemView.rootView

        val clMainContainer = view?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        val ivAuthorIcon = view?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
        val tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        val tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)
        val rbCustomRatingBar = view?.findViewById<CustomRatingBar>(R.id.story_element_author_view_holder_item_rating_bar)

        val reviewRatingValue: Float? = mStory.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.GONE
        }

        val authorName = mStory.authors?.first()?.name
        if (authorName != null) {
            tvAuthorName?.text = authorName
            tvAuthorName?.visibility = View.VISIBLE
            clMainContainer?.visibility = View.VISIBLE

            val heroImageURL = mStory.authors?.first()?.avatarUrl
            if (heroImageURL != null) {
                ivAuthorIcon?.visibility = View.VISIBLE
                Glide.with(ivAuthorIcon?.context as Context)
                        .load(heroImageURL)
                        .into(ivAuthorIcon)
            }
        }
        val publishedDate = mStory.publishedAt.toString()
        tvPublishedDate?.text = Utilities.formatDate(publishedDate)

        clMainContainer?.visibility = View.VISIBLE
        tvPublishedDate?.visibility = View.VISIBLE
    }
}