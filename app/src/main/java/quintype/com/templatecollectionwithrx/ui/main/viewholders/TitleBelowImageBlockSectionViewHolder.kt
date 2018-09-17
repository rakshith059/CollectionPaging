package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryPagerFragment
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks


class TitleBelowImageBlockSectionViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_below_image_block_section_header_row_iv_hero_icon)

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage)

//        ivHeroImage.setOnClickListener(View.OnClickListener {
//            var intent = Intent(ivHeroImage.context, WebActivity::class.java)
//            ivHeroImage.context.startActivity(intent)
//        })

        itemView?.setOnClickListener({
            var storyList = ArrayList<Story>()
            storyList.add(collectionItem)
            mFragmentCallbacks?.replaceFragment(StoryPagerFragment.newInstance(storyList), "StoryDetailFragment")
        })
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
