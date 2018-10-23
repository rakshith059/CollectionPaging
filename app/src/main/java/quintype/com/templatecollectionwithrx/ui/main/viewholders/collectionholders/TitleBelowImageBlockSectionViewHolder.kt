package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks


class TitleBelowImageBlockSectionViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        var ivHeroImage = itemView?.findViewById<SimpleDraweeView>(R.id.title_below_image_block_section_header_row_iv_hero_icon)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_below_image_block_section_header_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivHeroImage?.setImageURI(heroImageURL)

//        Glide.with(ivHeroImage?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage)

//        ivHeroImage.setOnClickListener(View.OnClickListener {
//            var intent = Intent(ivHeroImage.context, WebActivity::class.java)
//            ivHeroImage.context.startActivity(intent)
//        })

//        itemView?.setOnClickListener({
//            var storyList = ArrayList<Story>()
//            storyList.add(collectionItem)
//            mFragmentCallbacks?.addFragment(StoryPagerFragment.newInstance(storyList), "StoryDetailFragment")
//        })
    }

    companion object {
        var mFragmentCallbacks: FragmentCallbacks? = null
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): TitleBelowImageBlockSectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_block_section_header_row, parent, false)
            this.mFragmentCallbacks = mFragmentCallbacks
            return TitleBelowImageBlockSectionViewHolder(view)
        }
    }

}
