package quintype.com.templatecollectionwithrx.models

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
