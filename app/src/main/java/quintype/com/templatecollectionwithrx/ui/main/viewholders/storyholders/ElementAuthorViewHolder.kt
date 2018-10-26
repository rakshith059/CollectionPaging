package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.R.id.*
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.AuthorListFragment
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar


class ElementAuthorViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var mFragmentCallbacks: FragmentCallbacks? = null

    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementAuthorViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_element_author_view_holder, parent, false)
            val elementAuthorViewHolder = ElementAuthorViewHolder(view)
            elementAuthorViewHolder.mFragmentCallbacks = mFragmentCallbacks
            return elementAuthorViewHolder
        }
    }

    fun bind(mStory: Story) {
        val view = this.itemView.rootView

        val clMainContainer = view?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        val ivAuthorIcon = view?.findViewById<SimpleDraweeView>(R.id.author_image_row_iv_author_icon)
        val tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        val tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)
        val rbCustomRatingBar = view?.findViewById<CustomRatingBar>(R.id.story_element_author_view_holder_item_rating_bar)

        val tvSectionName = view?.findViewById<TextView>(R.id.fragment_story_detail_tv_section_name)
        val llSection = view?.findViewById<LinearLayout>(R.id.fragment_story_detail_ll_section)
        val tvTitle = view?.findViewById<TextView>(R.id.fragment_story_detail_tv_title)

        val reviewRatingValue: Float? = mStory.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.GONE
        }

        /**
         * checking for sections display name if it's null then checking for section's name
         */
        var sectionName: String? = null
        val storySection = mStory.sections?.first()
        if (storySection?.displayName != null)
            sectionName = storySection.displayName
        else if (storySection?.name != null)
            sectionName = storySection.name
        if (!TextUtils.isEmpty(sectionName)) {
            tvSectionName?.text = sectionName
            llSection?.visibility = View.VISIBLE
        }

        /**
         * checking for story headline
         */
        val mStoryTitle: String? = mStory.headline
        if (!TextUtils.isEmpty(mStoryTitle)) {
            tvTitle?.visibility = View.VISIBLE
//            fragment_story_detail_tv_title.minLines = 2
            tvTitle?.text = mStory.headline
        }

        val authorName = mStory.authors?.first()?.name
        if (authorName != null) {
            tvAuthorName?.text = authorName
            tvAuthorName?.visibility = View.VISIBLE
            clMainContainer?.visibility = View.VISIBLE

            val heroImageURL = mStory.authors?.first()?.avatarUrl
            if (heroImageURL != null) {
                ivAuthorIcon?.visibility = View.VISIBLE

                ivAuthorIcon?.hierarchy = Utilities.getFriscoRoundImageHierarchy(ivAuthorIcon?.context as Context, Constants.CIRCLE_IMAGE_BORDER_WIDTH_3F, ivAuthorIcon.context?.resources?.getColor(R.color.colorPrimary) as Int)
                ivAuthorIcon.setImageURI(heroImageURL)

//                Glide.with(ivAuthorIcon?.context as Context)
//                        .load(heroImageURL)
//                        .into(ivAuthorIcon)
            }

            tvAuthorName?.setOnClickListener {
                mFragmentCallbacks?.addFragment(AuthorListFragment.newInstance(authorName, heroImageURL),
                        AuthorListFragment::class.java.name + " : " + authorName)
            }
            ivAuthorIcon?.setOnClickListener {
                mFragmentCallbacks?.addFragment(AuthorListFragment.newInstance(authorName, heroImageURL),
                        AuthorListFragment::class.java.name + " : " + authorName)
            }
        }
        val publishedDate = mStory.publishedAt.toString()
        tvPublishedDate?.text = Utilities.formatDate(publishedDate)

        clMainContainer?.visibility = View.VISIBLE
        tvPublishedDate?.visibility = View.VISIBLE
    }
}