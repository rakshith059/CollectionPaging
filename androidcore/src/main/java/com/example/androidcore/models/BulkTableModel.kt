package com.example.androidcore.models

import com.example.androidcore.models.collection.AssociatedMetadata
import com.example.androidcore.models.collection.CollectionItem
import com.example.androidcore.models.collection.CollectionResponse
import com.example.androidcore.models.story.Story

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