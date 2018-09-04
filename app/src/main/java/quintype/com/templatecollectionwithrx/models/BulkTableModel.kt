package quintype.com.templatecollectionwithrx.models

import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.collection.CollectionItem
import quintype.com.templatecollectionwithrx.models.collection.CollectionResponse
import quintype.com.templatecollectionwithrx.models.story.Story

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class BulkTableModel(mSlug: String?,
                     mStory: Story?,
                     mOuterCollectionName: String?,
                     mOuterCollectionAssociatedMetadata: AssociatedMetadata?,
                     mOuterCollectionTemplate: String?,
                     mOuterCollectionInnerSlug: String?,
                     mOuterCollectionInnerItem: CollectionItem?,
                     mInnerCollectionResponse: CollectionResponse?) {
    var slug: String? = mSlug
    var story: Story? = mStory
    var outerCollectionName: String? = mOuterCollectionName
    var mOuterCollectionAssociatedMetadata: AssociatedMetadata? = mOuterCollectionAssociatedMetadata
    var outerCollectionTemplate: String? = mOuterCollectionTemplate
    var outerCollectionInnerSlug: String? = mOuterCollectionInnerSlug
    var outerCollectionInnerItem: CollectionItem? = mOuterCollectionInnerItem
    var innerCollectionResponse: CollectionResponse? = mInnerCollectionResponse
}