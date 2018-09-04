package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata


/**
 * Created TemplateCollectionWithRx by rakshith on 8/13/18.
 */
class SectionBlockTitleAuthorFragment : ConstraintLayout {

    private var isShowAuthorName = true
    private var isShowTimeToPublish = false
    private var isShowSectionName = false

    val mContext: Context? = null

    constructor(context: Context?) : super(context) {
        if (mContext != null) {
            setUpView(mContext)
        }
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context) {
        if (mContext != null) {
            setUpView(mContext)
        }
    }

    var tvTitle: TextView? = null
    var tvSection: TextView? = null
    var tvPublishedDate: TextView? = null
    var tvAuthorName: TextView? = null
    var clSectionBlockMainContainer: ConstraintLayout? = null
    var clMainContainer: LinearLayout? = null
    var ivAuthorIcon: ImageView? = null

    fun setUpView(mContext: Context) {
        val view = LayoutInflater.from(mContext)?.inflate(R.layout.section_block_title_author_row, this)

        tvTitle = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

        tvSection = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_section_name)

        clSectionBlockMainContainer = view?.findViewById<ConstraintLayout>(R.id.section_block_title_author_row_main_container)
        clMainContainer = view?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        ivAuthorIcon = view?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
        tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)
    }

    fun setAssosiatedMetaData(mContext: Context, collectionAssociatedMetadata: AssociatedMetadata?, collectionItem: Story) {
        setUpView(mContext)

        tvTitle?.text = collectionItem.headline

        if (collectionAssociatedMetadata != null) {
            isShowAuthorName = collectionAssociatedMetadata.associatedMetadataShowAuthorName
            isShowTimeToPublish = collectionAssociatedMetadata.associatedMetadataShowTimeToPublish
            isShowSectionName = collectionAssociatedMetadata.associatedMetadataShowSectionTag

            val assosiatedMetadataTheme = collectionAssociatedMetadata.associatedMetadataTheme

            if (isShowAuthorName) {
                val authorName = collectionItem.authorName
                if (authorName != null) {
                    tvAuthorName?.text = authorName
                    tvAuthorName?.visibility = View.VISIBLE
                    clMainContainer?.visibility = View.VISIBLE

                    val heroImageURL = collectionItem.authors?.first()?.avatarUrl
                    if (heroImageURL != null) {
                        ivAuthorIcon?.visibility = View.VISIBLE
                        Glide.with(mContext)
                                .load(heroImageURL)
                                .into(ivAuthorIcon as ImageView)
                    }
                }
            }
            if (isShowTimeToPublish) {
                val publishedDate = collectionItem.publishedAt.toString()
                if (publishedDate != null) {
                    tvPublishedDate?.text = publishedDate

                    clMainContainer?.visibility = View.VISIBLE
                    tvPublishedDate?.visibility = View.VISIBLE
                }
            }
            if (isShowSectionName) {
                val sectionName = collectionItem.sections?.first()?.displayName
                if (sectionName != null) {
                    tvSection?.visibility = View.VISIBLE
                    tvSection?.text = sectionName
                } else
                    tvSection?.visibility = View.GONE
            }
            if (assosiatedMetadataTheme != null) {
                var themePrimaryColor = mContext.resources.getColor(R.color.theme_primary_color)
                var themeSecondaryColor = mContext.resources.getColor(R.color.theme_secondary_color)

                when (assosiatedMetadataTheme) {
                    Constants.ASSOISATED_THEME_DARK -> {
                        tvTitle?.setTextColor(themePrimaryColor)
                        tvAuthorName?.setTextColor(themePrimaryColor)
                        tvPublishedDate?.setTextColor(themePrimaryColor)
                        clSectionBlockMainContainer?.setBackgroundColor(themeSecondaryColor)
                    }
                    Constants.ASSOISATED_THEME_LIGHT -> {
                        tvTitle?.setTextColor(themeSecondaryColor)
                        tvAuthorName?.setTextColor(themeSecondaryColor)
                        tvPublishedDate?.setTextColor(themeSecondaryColor)
                        clSectionBlockMainContainer?.setBackgroundColor(themePrimaryColor)
                    }
                }
            } else {
                var themePrimaryColor = mContext.resources.getColor(R.color.theme_primary_color)
                var themeSecondaryColor = mContext.resources.getColor(R.color.theme_secondary_color)
                tvTitle?.setTextColor(themePrimaryColor)
                tvAuthorName?.setTextColor(themePrimaryColor)
                tvPublishedDate?.setTextColor(themePrimaryColor)
                clSectionBlockMainContainer?.setBackgroundColor(themeSecondaryColor)
            }
        }
    }
}