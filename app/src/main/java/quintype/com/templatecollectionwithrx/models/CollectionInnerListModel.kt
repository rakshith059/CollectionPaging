package quintype.com.templatecollectionwithrx.models

class CollectionInnerListModel(mStory: Story, viewHolderType: Int, associatedMetadata: AssociatedMetadata?) {
    var story: Story? = mStory
    var viewHolderType = viewHolderType
    var associatedMetadata: AssociatedMetadata? = associatedMetadata
}
