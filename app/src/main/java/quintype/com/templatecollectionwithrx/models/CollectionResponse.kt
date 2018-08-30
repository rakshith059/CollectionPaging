package quintype.com.templatecollectionwithrx.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class CollectionResponse {

    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("fallback")
    @Expose
    var fallback: Boolean? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("data-source")
    @Expose
    var dataSource: String? = null
    @SerializedName("automated")
    @Expose
    var automated: Boolean? = null
    @SerializedName("template")
    @Expose
    var template: String? = null
    @SerializedName("rules")
    @Expose
    var rules: Rules? = null
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("total-count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<CollectionItem>? = null
    @SerializedName("created-at")
    @Expose
    var createdAt: Long? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata____? = null
}

class Author {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("avatar-url")
    @Expose
    var avatarUrl: String? = null
    @SerializedName("avatar-s3-key")
    @Expose
    var avatarS3Key: String? = null
    @SerializedName("twitter-handle")
    @Expose
    var twitterHandle: String? = null
    @SerializedName("bio")
    @Expose
    var bio: String? = null

}

class Card {

    @SerializedName("story-elements")
    @Expose
    var storyElements: List<StoryElement>? = null
    @SerializedName("card-updated-at")
    @Expose
    var cardUpdatedAt: Long? = null
    @SerializedName("content-version-id")
    @Expose
    var contentVersionId: String? = null
    @SerializedName("card-added-at")
    @Expose
    var cardAddedAt: Long? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("content-id")
    @Expose
    var contentId: String? = null
    @SerializedName("version")
    @Expose
    var version: Int? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata_? = null

}

class CardShare {

    @SerializedName("shareable")
    @Expose
    var shareable: Boolean? = null

}

class ClaimReviews {

    @SerializedName("story")
    @Expose
    var story: Any? = null

}

class Collection {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null

}

public class Rules {
}

public class CoverImage {
}

public class Entities {
}

class HeroImageMetadata {

    @SerializedName("mime-type")
    @Expose
    var mimeType: String? = null
    @SerializedName("focus-point")
    @Expose
    var focusPoint: List<Int>? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null

}

class Image {

    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("url")
    @Expose
    var url: Any? = null
    @SerializedName("attribution")
    @Expose
    var attribution: String? = null
    @SerializedName("caption")
    @Expose
    var caption: String? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata__? = null

}

class ImageMetadata {

    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("mime-type")
    @Expose
    var mimeType: String? = null
    @SerializedName("focus-point")
    @Expose
    var focusPoint: List<Int>? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null

}

class CollectionItem {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("associated-metadata")
    @Expose
    var associatedMetadata: AssociatedMetadata? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("template")
    @Expose
    var template: String? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
    @SerializedName("score")
    @Expose
    var score: Any? = null
    @SerializedName("item")
    @Expose
    var item: Item_? = null
    @SerializedName("story")
    @Expose
    var story: Story? = null
}

class AssociatedMetadata() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SerializedName("layout")
    @Expose
    var associatedMetadataLayout: String? = null
    @SerializedName("show_arrows")
    @Expose
    var associatedMetadataShowArrow: Boolean = false
    @SerializedName("show_author_name")
    @Expose
    var associatedMetadataShowAuthorName: Boolean = true
    @SerializedName("slider_type_dots")
    @Expose
    var associatedMetadataSliderTypeDots: Boolean = false
    @SerializedName("show_section_tag")
    @Expose
    var associatedMetadataShowSectionTag: Boolean = false
    @SerializedName("show_time_of_publish")
    @Expose
    var associatedMetadataShowTimeToPublish: Boolean = false
    @SerializedName("show_collection_name")
    @Expose
    var associatedMetadataShowCollectionName: Boolean = true
    @SerializedName("scroll_speed_ms")
    @Expose
    var associatedMetadataScrollSpeedMs: Int = 0
    @SerializedName("number_of_stories_to_show")
    @Expose
    var associatedMetadataNumberOfStoriesToShow: Int = 0
    @SerializedName("theme")
    @Expose
    var associatedMetadataTheme: String? = null
    @SerializedName("slider_type_dashes")
    @Expose
    var associatedMetadataSliderTypeDashes: Boolean = false
    @SerializedName("enable_auto_play")
    @Expose
    var associatedMetadataEnableAutoPlay: Boolean = false

    constructor(parcel: Parcel) : this() {
        associatedMetadataLayout = parcel.readString()
        associatedMetadataShowArrow = parcel.readByte() != 0.toByte()
        associatedMetadataShowAuthorName = parcel.readByte() != 0.toByte()
        associatedMetadataSliderTypeDots = parcel.readByte() != 0.toByte()
        associatedMetadataShowSectionTag = parcel.readByte() != 0.toByte()
        associatedMetadataShowTimeToPublish = parcel.readByte() != 0.toByte()
        associatedMetadataShowCollectionName = parcel.readByte() != 0.toByte()
        associatedMetadataScrollSpeedMs = parcel.readInt()
        associatedMetadataNumberOfStoriesToShow = parcel.readInt()
        associatedMetadataTheme = parcel.readString()
        associatedMetadataSliderTypeDashes = parcel.readByte() != 0.toByte()
        associatedMetadataEnableAutoPlay = parcel.readByte() != 0.toByte()
    }

    companion object CREATOR : Parcelable.Creator<AssociatedMetadata> {
        override fun createFromParcel(parcel: Parcel): AssociatedMetadata {
            return AssociatedMetadata(parcel)
        }

        override fun newArray(size: Int): Array<AssociatedMetadata?> {
            return arrayOfNulls(size)
        }
    }
}

class Item_ {

    @SerializedName("headline")
    @Expose
    var headline: List<String>? = null

}

class Metadata {

    @SerializedName("promotional-message")
    @Expose
    var promotionalMessage: Boolean? = null

}

class Metadata_ {

    @SerializedName("social-share")
    @Expose
    var socialShare: SocialShare? = null

}

class Metadata__ {

    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("mime-type")
    @Expose
    var mimeType: String? = null
    @SerializedName("focus-point")
    @Expose
    var focusPoint: List<Int>? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null

}

class Metadata___ {
    @SerializedName("story-attributes")
    @Expose
    var storyAttributes: StoryAttributes? = null
    @SerializedName("card-share")
    @Expose
    var cardShare: CardShare? = null
    @SerializedName("review-title")
    @Expose
    var reviewTitle: String? = null
    @SerializedName("review-rating")
    @Expose
    var reviewRating: MetadataReviewRating? = null
    @SerializedName("sponsored-by")
    @Expose
    var sponsoredBy: String? = null
}

class MetadataReviewRating {
    @SerializedName("label")
    @Expose
    var metadataReviewRatingLabel: String? = null
    @SerializedName("value")
    @Expose
    var metadataReviewRatingValue: String? = null
}

class Metadata____ {
    @SerializedName("cover-image")
    @Expose
    var coverImage: CoverImage? = null
}

class Section {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("display-name")
    @Expose
    var displayName: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("parent-id")
    @Expose
    var parentId: Any? = null
    @SerializedName("collection")
    @Expose
    var collection: Collection? = null

}

class Seo {

    @SerializedName("claim-reviews")
    @Expose
    var claimReviews: ClaimReviews? = null
    @SerializedName("meta-description")
    @Expose
    var metaDescription: String? = null
    @SerializedName("meta-title")
    @Expose
    var metaTitle: String? = null
    @SerializedName("meta-keywords")
    @Expose
    var metaKeywords: List<Any>? = null
    @SerializedName("meta-google-news-standout")
    @Expose
    var metaGoogleNewsStandout: Boolean? = null

}

class SocialShare {

    @SerializedName("shareable")
    @Expose
    var shareable: Boolean? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("image")
    @Expose
    var image: Image? = null

}

class Story() : Parcelable {

    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long? = null
    @SerializedName("seo")
    @Expose
    var seo: Seo? = null
    @SerializedName("assignee-id")
    @Expose
    var assigneeId: Int? = null
    @SerializedName("author-name")
    @Expose
    var authorName: String? = null
    @SerializedName("tags")
    @Expose
    var tags: List<Tag>? = null
    @SerializedName("headline")
    @Expose
    var headline: String? = null
    @SerializedName("storyline-id")
    @Expose
    var storylineId: Any? = null
    @SerializedName("votes")
    @Expose
    var votes: Votes? = null
    @SerializedName("story-content-id")
    @Expose
    var storyContentId: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("last-published-at")
    @Expose
    var lastPublishedAt: Long? = null
    @SerializedName("subheadline")
    @Expose
    var subheadline: Any? = null
    @SerializedName("alternative")
    @Expose
    var alternative: Any? = null
    @SerializedName("sections")
    @Expose
    var sections: List<Section>? = null
    @SerializedName("access-level-value")
    @Expose
    var accessLevelValue: Any? = null
    @SerializedName("content-created-at")
    @Expose
    var contentCreatedAt: Long? = null
    @SerializedName("owner-name")
    @Expose
    var ownerName: String? = null
    @SerializedName("custom-slug")
    @Expose
    var customSlug: Any? = null
    @SerializedName("push-notification")
    @Expose
    var pushNotification: Any? = null
    @SerializedName("publisher-id")
    @Expose
    var publisherId: Int? = null
    @SerializedName("hero-image-metadata")
    @Expose
    var heroImageMetadata: HeroImageMetadata? = null
    @SerializedName("comments")
    @Expose
    var comments: Any? = null
    @SerializedName("entities")
    @Expose
    var entities: Entities? = null
    @SerializedName("published-at")
    @Expose
    var publishedAt: Long? = null
    @SerializedName("breaking-news-linked-story-id")
    @Expose
    var breakingNewsLinkedStoryId: Any? = null
    @SerializedName("storyline-title")
    @Expose
    var storylineTitle: Any? = null
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("canonical-url")
    @Expose
    var canonicalUrl: Any? = null
    @SerializedName("autotags")
    @Expose
    var autotags: List<Any>? = null
    @SerializedName("linked-entities")
    @Expose
    var linkedEntities: List<Any>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("hero-image-attribution")
    @Expose
    var heroImageAttribution: String? = null
    @SerializedName("bullet-type")
    @Expose
    var bulletType: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("hero-image-s3-key")
    @Expose
    var heroImageS3Key: String? = null
    @SerializedName("cards")
    @Expose
    var cards: List<Card>? = null
    @SerializedName("story-version-id")
    @Expose
    var storyVersionId: String? = null
    @SerializedName("content-type")
    @Expose
    var contentType: String? = null
    @SerializedName("content-updated-at")
    @Expose
    var contentUpdatedAt: Long? = null
    @SerializedName("author-id")
    @Expose
    var authorId: Int? = null
    @SerializedName("owner-id")
    @Expose
    var ownerId: Int? = null
    @SerializedName("linked-story-ids")
    @Expose
    var linkedStoryIds: List<Any>? = null
    @SerializedName("access")
    @Expose
    var access: Any? = null
    @SerializedName("promotional-message")
    @Expose
    var promotionalMessage: String? = null
    @SerializedName("first-published-at")
    @Expose
    var firstPublishedAt: Long? = null
    @SerializedName("hero-image-caption")
    @Expose
    var heroImageCaption: String? = null
    @SerializedName("version")
    @Expose
    var version: Int? = null
    @SerializedName("story-template")
    @Expose
    var storyTemplate: String? = null
    @SerializedName("sequence-no")
    @Expose
    var sequenceNo: Any? = null
    @SerializedName("created-at")
    @Expose
    var createdAt: Long? = null
    @SerializedName("authors")
    @Expose
    var authors: List<Author>? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata___? = null
    @SerializedName("publish-at")
    @Expose
    var publishAt: Any? = null
    @SerializedName("assignee-name")
    @Expose
    var assigneeName: String? = null

    constructor(parcel: Parcel) : this() {
        updatedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        assigneeId = parcel.readValue(Int::class.java.classLoader) as? Int
        authorName = parcel.readString()
        headline = parcel.readString()
        storyContentId = parcel.readString()
        slug = parcel.readString()
        lastPublishedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        contentCreatedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        ownerName = parcel.readString()
        publisherId = parcel.readValue(Int::class.java.classLoader) as? Int
        publishedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        summary = parcel.readString()
        status = parcel.readString()
        heroImageAttribution = parcel.readString()
        bulletType = parcel.readString()
        id = parcel.readString()
        heroImageS3Key = parcel.readString()
        storyVersionId = parcel.readString()
        contentType = parcel.readString()
        contentUpdatedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        authorId = parcel.readValue(Int::class.java.classLoader) as? Int
        ownerId = parcel.readValue(Int::class.java.classLoader) as? Int
        promotionalMessage = parcel.readString()
        firstPublishedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        heroImageCaption = parcel.readString()
        version = parcel.readValue(Int::class.java.classLoader) as? Int
        storyTemplate = parcel.readString()
        createdAt = parcel.readValue(Long::class.java.classLoader) as? Long
        assigneeName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(updatedAt)
        parcel.writeValue(assigneeId)
        parcel.writeString(authorName)
        parcel.writeString(headline)
        parcel.writeString(storyContentId)
        parcel.writeString(slug)
        parcel.writeValue(lastPublishedAt)
        parcel.writeValue(contentCreatedAt)
        parcel.writeString(ownerName)
        parcel.writeValue(publisherId)
        parcel.writeValue(publishedAt)
        parcel.writeString(summary)
        parcel.writeString(status)
        parcel.writeString(heroImageAttribution)
        parcel.writeString(bulletType)
        parcel.writeString(id)
        parcel.writeString(heroImageS3Key)
        parcel.writeString(storyVersionId)
        parcel.writeString(contentType)
        parcel.writeValue(contentUpdatedAt)
        parcel.writeValue(authorId)
        parcel.writeValue(ownerId)
        parcel.writeString(promotionalMessage)
        parcel.writeValue(firstPublishedAt)
        parcel.writeString(heroImageCaption)
        parcel.writeValue(version)
        parcel.writeString(storyTemplate)
        parcel.writeValue(createdAt)
        parcel.writeString(assigneeName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Story> {
        override fun createFromParcel(parcel: Parcel): Story {
            return Story(parcel)
        }

        override fun newArray(size: Int): Array<Story?> {
            return arrayOfNulls(size)
        }
    }

}

class StoryAttributes {

    @SerializedName("displaybyline")
    @Expose
    var displaybyline: List<String>? = null

}

class StoryElement {

    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("page-url")
    @Expose
    var pageUrl: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("family-id")
    @Expose
    var familyId: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("embed-url")
    @Expose
    var embedUrl: String? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
    @SerializedName("subtype")
    @Expose
    var subtype: Any? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("image-metadata")
    @Expose
    var imageMetadata: ImageMetadata? = null
    @SerializedName("image-attribution")
    @Expose
    var imageAttribution: String? = null
    @SerializedName("image-s3-key")
    @Expose
    var imageS3Key: String? = null
    @SerializedName("file-name")
    @Expose
    var fileName: String? = null
    @SerializedName("s3-key")
    @Expose
    var s3Key: String? = null
    @SerializedName("content-type")
    @Expose
    var contentType: String? = null

}

class Tag {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("meta-description")
    @Expose
    var metaDescription: Any? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null

}

public class Votes {
}