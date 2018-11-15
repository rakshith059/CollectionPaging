package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities

open class TitleInsideImageGridViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        val ivHeroImage = view.findViewById<SimpleDraweeView>(R.id.title_inside_image_grid_row_iv_hero_icon)
        val tvStoryTitle = view.findViewById<TextView>(R.id.title_inside_image_grid_row_tv_title)

        val clMainContainer = view.findViewById<ConstraintLayout>(R.id.title_inside_image_grid_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val cdnHostName = Utilities.getSharedPreferences(itemView.context, Constants.SP_CDN_IMAGE_NAME)
        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivHeroImage?.setImageURI(heroImageURL)
        tvStoryTitle?.text = collectionItem.headline
    }
}