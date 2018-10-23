package com.example.androidcore.models

import com.example.androidcore.models.collection.AssociatedMetadata
import com.example.androidcore.models.collection.CollectionItem
import com.example.androidcore.models.story.Story

class CollectionInnerListModel(mStory: Story?, viewHolderType: Int?, associatedMetadata: AssociatedMetadata?, outerCollectionName: String?) {
    var story: Story? = mStory
    var viewHolderType = viewHolderType
    var associatedMetadata: AssociatedMetadata? = associatedMetadata
    var collectionItemList: List<CollectionItem>? = null
    var outerCollectionName: String? = outerCollectionName

    constructor(collectionInnerList: List<CollectionItem>, story: Story?, viewHolderType: Int, associatedMetadata: AssociatedMetadata, outerCollectionName: String?) :
            this(story, viewHolderType, associatedMetadata, outerCollectionName) {
        collectionItemList = collectionInnerList
        this.outerCollectionName = outerCollectionName
    }
}
