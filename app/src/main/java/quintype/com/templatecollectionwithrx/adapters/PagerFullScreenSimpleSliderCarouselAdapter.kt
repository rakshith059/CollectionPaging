package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.collection.CollectionItem
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.PagerCarouselFullScreenSimpleSliderViewHolder
import quintype.com.templatecollectionwithrx.utils.Constants

class PagerFullScreenSimpleSliderCarouselAdapter(collectionAssociatedMetadata: AssociatedMetadata?, collectionList: List<CollectionItem>?, collectionName: String?) : PagerAdapter() {
    var storyList = collectionList
    var mCollectionAssociatedMetadata = collectionAssociatedMetadata
    var mCollectionName = collectionName

    override fun getCount(): Int {
        if (mCollectionAssociatedMetadata?.associatedMetadataNumberOfStoriesToShow as Int > 0)
            return mCollectionAssociatedMetadata?.associatedMetadataNumberOfStoriesToShow as Int
        else
            return Constants.PAGE_LIMIT_CHILD
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(container.context).inflate(R.layout.pager_carousel_full_screen_simple_slider_row, container, false)
        var slideShowViewHolder = PagerCarouselFullScreenSimpleSliderViewHolder(view)
        val story = storyList?.get(position)?.story as Story

        slideShowViewHolder.bind(story, mCollectionAssociatedMetadata, mCollectionName)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

