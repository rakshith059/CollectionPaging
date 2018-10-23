package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.collection.AssociatedMetadata
import com.example.androidcore.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.BaseAuthorAndPublishedDateViewHolder
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class ElementStoryHeroImageViewHolder(itemView: View?) : BaseAuthorAndPublishedDateViewHolder(itemView) {

    var ivHeroImage: SimpleDraweeView? = null
    var tvTitle: TextView? = null

    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementStoryHeroImageViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_hero_image_view_holder, parent, false)
            val storyHeroImageViewHolder = ElementStoryHeroImageViewHolder(view)

            storyHeroImageViewHolder.ivHeroImage = view?.findViewById(R.id.pager_carousel_half_slider_row_iv_hero_icon)
            storyHeroImageViewHolder.tvTitle = view?.findViewById(R.id.section_block_title_author_row_tv_title)

            return storyHeroImageViewHolder
        }
    }

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        tvTitle?.maxLines = 2
        tvTitle?.minLines = 2
        tvTitle?.ellipsize = TextUtils.TruncateAt.END

        val clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.pager_carousel_half_slider_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key
        ivHeroImage?.setImageURI(heroImageURL)

//        Glide.with(itemView?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage as ImageView)

        val rbCustomRatingBar = itemView?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)

        val reviewRatingValue: Float? = collectionItem.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.INVISIBLE
        }
    }
}
