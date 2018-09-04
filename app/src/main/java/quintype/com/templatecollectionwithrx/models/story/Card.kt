package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
class Card : Parcelable {

    @SerializedName("id")
    private var id: String? = null
    @SerializedName("content-id")
    private var contentId: String? = null
    @SerializedName("story-elements")
    private var storyElements = emptyList<StoryElement>()
    @SerializedName("status")
    private val status: String
    @SerializedName("content-version-id")
    private var contentVersionId: String? = null
    @SerializedName("version")
    private val version: String
    @SerializedName("index")
    private val index: Int
    @SerializedName("totalCards")
    private val totalCards: Int
    @SerializedName("card-updated-at")
    private var cardUpdatedAt: Long = 0
    @SerializedName("card-added-at")
    private var cardAddedAt: Long = 0
    @SerializedName("metadata")
    private val metadata: CardMetadata

    private var uiStoryElements = ArrayList<StoryElement>()

    /**
     * takes the default story elements and builds them to a new list of story elements for ui
     * purposes
     */
    //    public void buildUIStoryElements() {
    //        uiStoryElements.clear();
    //        for (StoryElement storyElement : storyElements) {
    //            if (storyElement.isTypeText() && !storyElement.isTypeQuote()
    //                    && !storyElement.isTypeBlockQuote() && !storyElement.isTypeBlurb()) {
    //                Document htmlDoc = Jsoup.parse(storyElement.text());
    //                htmlDoc.outputSettings().prettyPrint(false).indentAmount(0);
    //                Elements blockQuoteElements = htmlDoc.select("blockquote");
    //                if (!blockQuoteElements.isEmpty()) {
    //                    StoryElement breakUpStoryElement = StoryElement.fromStoryElement(storyElement);
    //                    for (Element blockQuote : blockQuoteElements) {
    //                        StoryElement blockQuoteStoryElement = StoryElement.fromStoryElement
    //                                (breakUpStoryElement);
    //                        blockQuoteStoryElement.setTypeAsQuote();
    //                        blockQuoteStoryElement.text = (blockQuote.html());
    //
    //                        String textToBeRemoved = blockQuote.outerHtml();
    //                        int textRemovalIndex = breakUpStoryElement.text().indexOf(textToBeRemoved);
    //                        if (textRemovalIndex >= 0) {
    //                            StoryElement textBeforeQuoteStoryElement = StoryElement
    //                                    .fromStoryElement(breakUpStoryElement);
    //                            textBeforeQuoteStoryElement.text = (breakUpStoryElement.text()
    //                                    .substring(0,
    //                                            breakUpStoryElement.text().indexOf(textToBeRemoved)));
    //                            breakUpStoryElement.text = (breakUpStoryElement.text().
    //                                    replace(textBeforeQuoteStoryElement.text() + textToBeRemoved,
    //                                            ""));
    //
    //                            if (!textBeforeQuoteStoryElement.text().isEmpty()) {
    //                                uiStoryElements.add(textBeforeQuoteStoryElement);
    //                            }
    //                            uiStoryElements.add(blockQuoteStoryElement);
    //                        } else {
    //                            //something is wrong with the index
    //                            break;
    //                        }
    //                    }
    //
    //                    if (!breakUpStoryElement.text().isEmpty()) {
    //                        uiStoryElements.add(breakUpStoryElement);
    //                    }
    //
    //                } else {
    //                    if (!TextUtils.isEmpty(storyElement.text())) {
    //                        uiStoryElements.add(storyElement);
    //                    }
    //                }
    //            } else {
    //                if (storyElement.isTypeJsembed()) {
    //                    storyElement.prepareForTwitter();
    //                }
    //                uiStoryElements.add(storyElement);
    //            }
    //        }
    //    }

    /**
     * @return First youtube element in this card
     */
    val firstYoutubeElement: StoryElement?
        get() {
            for (elem in storyElements) {
                if ("youtube-video".equals(elem.type(), ignoreCase = true)) {
                    return elem
                }
            }
            return null
        }

    override fun toString(): String {
        return "Card{" +
                "id='" + id + '\''.toString() +
                ", contentId='" + contentId + '\''.toString() +
                ", storyElements=" + storyElements +
                ", status='" + status + '\''.toString() +
                ", contentVersionId='" + contentVersionId + '\''.toString() +
                ", version='" + version + '\''.toString() +
                ", index=" + index +
                ", totalCards=" + totalCards +
                ", cardUpdatedAt=" + cardUpdatedAt +
                ", cardAddedAt=" + cardAddedAt +
                ", uiStoryElements=" + uiStoryElements +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.contentId)
        dest.writeTypedList(this.storyElements)
        dest.writeString(this.status)
        dest.writeString(this.contentVersionId)
        dest.writeString(this.version)
        dest.writeInt(this.index)
        dest.writeInt(this.totalCards)
        dest.writeLong(this.cardUpdatedAt)
        dest.writeLong(this.cardAddedAt)
        dest.writeParcelable(this.metadata, flags)
        dest.writeTypedList(this.uiStoryElements)
    }

    protected constructor(parcel: Parcel) {
        this.id = parcel.readString()
        this.contentId = parcel.readString()
        this.storyElements = parcel.createTypedArrayList(StoryElement.CREATOR)
        this.status = parcel.readString()
        this.contentVersionId = parcel.readString()
        this.version = parcel.readString()
        this.index = parcel.readInt()
        this.totalCards = parcel.readInt()
        this.cardUpdatedAt = parcel.readLong()
        this.cardAddedAt = parcel.readLong()
        this.metadata = parcel.readParcelable(CardMetadata::class.java.classLoader)
        this.uiStoryElements = parcel.createTypedArrayList(StoryElement.CREATOR)
    }

    fun storyElements(storyElements: List<StoryElement>) {
        this.storyElements = storyElements
    }

    //    fun dummyCard(): Card {
//        val card = Card()
//        card.id = UUID.randomUUID().toString()
//        card.contentId = UUID.randomUUID().toString()
//        card.contentVersionId = UUID.randomUUID().toString()
//        return card
//    }
    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}
