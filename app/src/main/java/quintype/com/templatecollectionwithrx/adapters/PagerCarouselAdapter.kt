package quintype.com.templatecollectionwithrx.adapters

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.title_inside_image_header_row.view.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.CollectionItem
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.PagerCarouselViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.TitleInsideImageViewHolder


class PagerCarouselAdapter(collectionAssociatedMetadata: AssociatedMetadata?, collectionList: List<CollectionItem>?) : PagerAdapter() {
    var storyList = collectionList
    var mCollectionAssociatedMetadata = collectionAssociatedMetadata

    override fun getCount(): Int {
        //Hardcoding to only 5 items
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(container.context).inflate(R.layout.pager_carousel_title_inside_image_row, container, false)
        var slideShowViewHolder = PagerCarouselViewHolder(view)
        val story = storyList?.get(position)?.story as Story

        slideShowViewHolder.bind(story, mCollectionAssociatedMetadata)
        container.addView(view)
        return view
    }

//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
////        var view = LayoutInflater.from(container.context).inflate(R.layout.title_inside_image_header_row, container, false)
//        val slideShowHolder = TitleInsideImageViewHolder.create(container)
//        val story = storyList?.get(position)?.story as Story
//        slideShowHolder.bind(story, mCollectionAssociatedMetadata)
////        container.setOnClickListener(View.OnClickListener {
////            fragmentCallbacks.addFragment(StoryPagerFragment.create(StoriesList(storyList), position, true),
////                    StoryDetailFragment::class.java!!.getSimpleName())
////        })
//        return container
//    }


//    override fun saveState(): Parcelable? {
//        val bundle = super.saveState() as Bundle?
//        if (bundle != null) {
//            // Never maintain any states from the base class, just null it out
//            bundle.putParcelableArray("states", null)
//        } else {
//            // do nothing
//        }
//        return bundle
//    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
