package quintype.com.templatecollectionwithrx.models

class CollectionInnerListModel(mStory: Story?, viewHolderType: Int?, associatedMetadata: AssociatedMetadata?) {
    var story: Story? = mStory
    var viewHolderType = viewHolderType
    var associatedMetadata: AssociatedMetadata? = associatedMetadata
    var collectionItemList: List<CollectionItem>? = null

    constructor(collectionInnerList: List<CollectionItem>, story: Story?, viewHolderType: Int, associatedMetadata: AssociatedMetadata) :
            this(story, viewHolderType, associatedMetadata) {
        collectionItemList = collectionInnerList
    }
}
