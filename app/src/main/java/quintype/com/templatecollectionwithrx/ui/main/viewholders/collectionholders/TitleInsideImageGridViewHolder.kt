package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.collection.AssociatedMetadata
import com.example.androidcore.models.story.Story
import quintype.com.templatecollectionwithrx.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities

open class TitleInsideImageGridViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    open fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        var ivHeroImage = itemView?.findViewById<SimpleDraweeView>(R.id.title_inside_image_grid_row_iv_hero_icon)
        var tvStoryTitle = itemView?.findViewById<TextView>(R.id.title_inside_image_grid_row_tv_title)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_inside_image_grid_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val cdnHostName = Utilities.getSharedPreferences(itemView.context, Constants.SP_CDN_IMAGE_NAME)
        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivHeroImage?.setImageURI(heroImageURL)
//        Glide.with(itemView?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage as ImageView)

        tvStoryTitle?.text = collectionItem?.headline
    }

    companion object {
        fun create(parent: ViewGroup): TitleInsideImageGridViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_grid_row, parent, false)
            return TitleInsideImageGridViewHolder(view)
        }
    }
}

