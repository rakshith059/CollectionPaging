package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.BaseAuthorAndPublishedDateViewHolder
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class ElementStoryHeroImageViewHolder(itemView: View?) : BaseAuthorAndPublishedDateViewHolder(itemView) {

    var ivHeroImage: ImageView? = null
    var tvTitle: TextView? = null

    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementStoryHeroImageViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_hero_image_view_holder, parent, false)
            val storyHeroImageViewHolder = ElementStoryHeroImageViewHolder(view)

            storyHeroImageViewHolder.ivHeroImage = view?.findViewById<ImageView>(R.id.pager_carousel_half_slider_row_iv_hero_icon)
            storyHeroImageViewHolder.tvTitle = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

            return storyHeroImageViewHolder
        }
    }

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        tvTitle?.maxLines = 2
        tvTitle?.minLines = 2
        tvTitle?.ellipsize = TextUtils.TruncateAt.END

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.pager_carousel_half_slider_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)

        var rbCustomRatingBar = itemView?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)

        var reviewRatingValue: Float? = collectionItem?.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.INVISIBLE
        }
    }
}
