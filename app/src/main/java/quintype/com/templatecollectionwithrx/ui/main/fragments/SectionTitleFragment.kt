package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.author_image_row.*
import kotlinx.android.synthetic.main.section_block_title_author_row.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.utils.Constants

/**
 * Created TemplateCollectionWithRx by rakshith on 8/24/18.
 */

@SuppressLint("ValidFragment")
class SectionTitleFragment() : Fragment() {
    constructor(collectionAssociatedMetadata: AssociatedMetadata?, collectionItem: Story) : this() {
        setAssosiatedMetaData(collectionAssociatedMetadata, collectionItem)
    }

    var tvStoryHeadline: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = null
        if (view == null) {
            view = inflater?.inflate(R.layout.section_block_title_author_row, container, false) as View
        }

        tvStoryHeadline = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var collectionAssociatedMetadata: AssociatedMetadata? = arguments?.getParcelable("collectionAssociatedMetadata")
        var collectionItem: Story? = arguments?.getParcelable("story")
        setAssosiatedMetaData(collectionAssociatedMetadata, collectionItem)
    }

    fun setAssosiatedMetaData(collectionAssociatedMetadata: AssociatedMetadata?, collectionItem: Story?) {
        if (collectionItem != null) {
            tvStoryHeadline?.text = collectionItem?.headline

            if (collectionAssociatedMetadata != null) {
                var isShowAuthorName = collectionAssociatedMetadata.associatedMetadataShowAuthorName
                var isShowTimeToPublish = collectionAssociatedMetadata.associatedMetadataShowTimeToPublish
                var isShowSectionName = collectionAssociatedMetadata.associatedMetadataShowSectionTag

                val assosiatedMetadataTheme = collectionAssociatedMetadata.associatedMetadataTheme

                if (isShowAuthorName) {
                    val authorName = collectionItem?.authorName
                    if (authorName != null) {
                        author_image_row_tv_author_name?.text = authorName
                        author_image_row_tv_author_name?.visibility = View.VISIBLE
                        author_image_row_main_container?.visibility = View.VISIBLE

                        val heroImageURL = collectionItem.authors?.first()?.avatarUrl
                        if (heroImageURL != null) {
                            author_image_row_iv_author_icon?.visibility = View.VISIBLE
                            Glide.with(activity as Activity)
                                    .load(heroImageURL)
                                    .into(author_image_row_iv_author_icon as ImageView)
                        }
                    }
                }
                if (isShowTimeToPublish) {
                    val publishedDate = collectionItem.publishedAt.toString()
                    if (publishedDate != null) {
                        author_image_row_tv_published_date?.text = publishedDate

                        author_image_row_main_container?.visibility = View.VISIBLE
                        author_image_row_tv_published_date?.visibility = View.VISIBLE
                    }
                }
                if (isShowSectionName) {
                    val sectionName = collectionItem.sections?.first()?.displayName
                    if (sectionName != null) {
                        section_block_title_author_row_tv_section_name?.visibility = View.VISIBLE
                        section_block_title_author_row_tv_section_name?.text = sectionName
                    } else
                        section_block_title_author_row_tv_section_name?.visibility = View.GONE
                }
                if (assosiatedMetadataTheme != null) {
                    var themePrimaryColor = activity?.resources?.getColor(R.color.theme_primary_color) as Int
                    var themeSecondaryColor = activity?.resources?.getColor(R.color.theme_secondary_color) as Int

                    when (assosiatedMetadataTheme) {
                        Constants.ASSOISATED_THEME_DARK -> {
                            section_block_title_author_row_tv_title?.setTextColor(themePrimaryColor)
                            author_image_row_tv_author_name?.setTextColor(themePrimaryColor)
                            author_image_row_tv_published_date?.setTextColor(themePrimaryColor)
                            section_block_title_author_row_main_container?.setBackgroundColor(themeSecondaryColor)
                        }
                        Constants.ASSOISATED_THEME_LIGHT -> {
                            section_block_title_author_row_tv_title?.setTextColor(themeSecondaryColor)
                            author_image_row_tv_author_name?.setTextColor(themeSecondaryColor)
                            author_image_row_tv_published_date?.setTextColor(themeSecondaryColor)
                            section_block_title_author_row_main_container?.setBackgroundColor(themePrimaryColor)
                        }
                    }
                }
            }
//            else {
//                var themePrimaryColor = activity.resources.getColor(R.color.theme_primary_color)
//                var themeSecondaryColor = activity.resources.getColor(R.color.theme_secondary_color)
//                section_block_title_author_row_tv_title?.setTextColor(themePrimaryColor)
//                author_image_row_tv_author_name?.setTextColor(themePrimaryColor)
//                author_image_row_tv_published_date?.setTextColor(themePrimaryColor)
//                section_block_title_author_row_main_container?.setBackgroundColor(themeSecondaryColor)
//            }
        }
    }
}