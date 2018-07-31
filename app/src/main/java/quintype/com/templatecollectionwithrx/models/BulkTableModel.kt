package quintype.com.templatecollectionwithrx.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class BulkTableModel(mSlug: String?,
                     mStory: Story?,
                     mOuterCollectionName: String?,
//                     mOuterCollectionSummary: String?,
                     mOuterCollectionTemplate: String?,
                     mOuterCollectionInnerSlug: String?,
                     mOuterCollectionInnerItem: CollectionItem?,
                     mInnerCollectionResponse: CollectionResponse?) {
    var slug: String? = mSlug
    var story: Story? = mStory
    var outerCollectionName: String? = mOuterCollectionName
    //    var outerCollectionSummary: String? = mOuterCollectionSummary
    var outerCollectionTemplate: String? = mOuterCollectionTemplate
    var outerCollectionInnerSlug: String? = mOuterCollectionInnerSlug
    var outerCollectionInnerItem: CollectionItem? = mOuterCollectionInnerItem
    var innerCollectionResponse: CollectionResponse? = mInnerCollectionResponse
}