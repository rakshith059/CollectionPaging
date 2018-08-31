package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * An object instance representing Story Card
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class Card implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("content-id")
    private String contentId;
    @SerializedName("story-elements")
    private List<StoryElement> storyElements = Collections.emptyList();
    @SerializedName("status")
    private String status;
    @SerializedName("content-version-id")
    private String contentVersionId;
    @SerializedName("version")
    private String version;
    @SerializedName("index")
    private int index;
    @SerializedName("totalCards")
    private int totalCards;
    @SerializedName("card-updated-at")
    private long cardUpdatedAt = 0;
    @SerializedName("card-added-at")
    private long cardAddedAt = 0;
    @SerializedName("metadata")
    private CardMetadata metadata;

    private List<StoryElement> uiStoryElements = new ArrayList<>();

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", contentId='" + contentId + '\'' +
                ", storyElements=" + storyElements +
                ", status='" + status + '\'' +
                ", contentVersionId='" + contentVersionId + '\'' +
                ", version='" + version + '\'' +
                ", index=" + index +
                ", totalCards=" + totalCards +
                ", cardUpdatedAt=" + cardUpdatedAt +
                ", cardAddedAt=" + cardAddedAt +
                ", uiStoryElements=" + uiStoryElements +
                '}';
    }

    public String id() {
        return id;
    }

    public String contentId() {
        return contentId;
    }

    public List<StoryElement> storyElements() {
        return storyElements;
    }

    public String status() {
        return status;
    }

    public String contentVersionId() {
        return contentVersionId;
    }

    public String version() {
        return version;
    }

    public int index() {
        return index;
    }

    public int totalCards() {
        return totalCards;
    }

    public long cardUpdatedAt() {
        return cardUpdatedAt;
    }

    public long cardAddedAt() {
        return cardAddedAt;
    }

    public List<StoryElement> uiStoryElements() {
        return uiStoryElements;
    }

    public CardMetadata metadata() {
        return metadata;
    }

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
    public StoryElement getFirstYoutubeElement() {
        for (StoryElement elem : storyElements) {
            if ("youtube-video".equalsIgnoreCase(elem.type())) {
                return elem;
            }
        }
        return null;
    }


    public Card() {
    }

    public Card(String id, List<StoryElement> storyElements) {
        this.id = id;
        this.storyElements = storyElements;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.contentId);
        dest.writeTypedList(this.storyElements);
        dest.writeString(this.status);
        dest.writeString(this.contentVersionId);
        dest.writeString(this.version);
        dest.writeInt(this.index);
        dest.writeInt(this.totalCards);
        dest.writeLong(this.cardUpdatedAt);
        dest.writeLong(this.cardAddedAt);
        dest.writeParcelable(this.metadata, flags);
        dest.writeTypedList(this.uiStoryElements);
    }

    protected Card(Parcel in) {
        this.id = in.readString();
        this.contentId = in.readString();
        this.storyElements = in.createTypedArrayList(StoryElement.CREATOR);
        this.status = in.readString();
        this.contentVersionId = in.readString();
        this.version = in.readString();
        this.index = in.readInt();
        this.totalCards = in.readInt();
        this.cardUpdatedAt = in.readLong();
        this.cardAddedAt = in.readLong();
        this.metadata = in.readParcelable(CardMetadata.class.getClassLoader());
        this.uiStoryElements = in.createTypedArrayList(StoryElement.CREATOR);
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public void storyElements(List<StoryElement> storyElements) {
        this.storyElements = storyElements;
    }

    public static Card dummyCard() {
        Card card = new Card();
        card.id = UUID.randomUUID().toString();
        card.contentId = UUID.randomUUID().toString();
        card.contentVersionId = UUID.randomUUID().toString();
        return card;
    }
}
