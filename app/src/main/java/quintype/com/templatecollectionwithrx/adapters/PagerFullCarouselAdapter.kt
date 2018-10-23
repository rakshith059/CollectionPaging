package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.collection.AssociatedMetadata
import com.example.androidcore.models.collection.CollectionItem
import com.example.androidcore.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.PagerCarouselFullScreenViewHolder


class PagerFullCarouselAdapter(collectionAssociatedMetadata: AssociatedMetadata?, collectionList: List<CollectionItem>?, listner: View.OnClickListener) : PagerAdapter() {
    var storyList = collectionList
    var mListner = listner

    var mCollectionAssociatedMetadata = collectionAssociatedMetadata
    override fun getCount(): Int {
        return mCollectionAssociatedMetadata?.associatedMetadataNumberOfStoriesToShow as Int
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(container.context).inflate(R.layout.pager_carousel_full_slider_row, container, false)
        var slideShowViewHolder = PagerCarouselFullScreenViewHolder(view)
        val story = storyList?.get(position)?.story as Story

        slideShowViewHolder.bind(story, mCollectionAssociatedMetadata, mListner)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
