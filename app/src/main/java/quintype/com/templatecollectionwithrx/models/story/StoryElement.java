package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Encapsulates story element present inside a {@linkplain Card}
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class StoryElement implements Parcelable {
    //quote: is virtual and client side only type
    public static final String TYPE_STORY_ELEMENT_VIRTUAL_QUOTE = "virtual-quote";
    //posted-at: is virtual and client side only type, specific to live-blog style story template
    public static final String TYPE_STORY_ELEMENT_BLOG_POSTED_AT = "blog-posted-at";
    //created-at: is virtual and client side only type, specific to live-blog style story template
    public static final String TYPE_STORY_ELEMENT_BLOG_UPDATED_AT = "blog-updated-at";


    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface TYPE {

    }


    //INVALID storyElement
    static final String INVALID_ID = "-1";
    public static final StoryElement INVALID_ELEMENT = new StoryElement();


    static {
        INVALID_ELEMENT.id = INVALID_ID;
    }

    public static StoryElement dummyElement() {
        StoryElement element = new StoryElement();
        element.id = UUID.randomUUID().toString();
        element.type = "text";
        return element;
    }

    public static StoryElement newStoryElement(String elementType) {
        StoryElement element = new StoryElement();
        element.id = UUID.randomUUID().toString();
        element.type = elementType;
        return element;
    }

    //Known types for StoryElement
    public static final String TYPE_TEXT = "text";
    private static final String TYPE_STORY_ELEMENT_JSEMBED = "jsembed";
    private static final String TYPE_STORY_ELEMENT_SOUNDCLOUD_AUDIO = "soundcloud-audio";
    private static final String TYPE_STORY_ELEMENT_AUDIO = "audio";
    private static final String TYPE_STORY_ELEMENT_MEDIA = "media";
    private static final String TYPE_STORY_ELEMENT_YOUTUBE_VIDEO = "youtube-video";
    private static final String TYPE_STORY_ELEMENT_EXTERNAL = "external-file";
    private static final String TYPE_STORY_ELEMENT_SUB_BIT_GRAVITY = "bitgravity-video";
    private static final String TYPE_STORY_ELEMENT_VIDEO = "video";
    private static final String TYPE_STORY_ELEMENT_TITLE = "title";
    protected static final String TYPE_STORY_ELEMENT_IMAGE = "image";
    private static final String TYPE_STORY_ELEMENT_COMPOSITE = "composite";
    private static final String TYPE_STORY_ELEMENT_POLLTYPE = "polltype";
    private static final String TYPE_STORY_ELEMENT_DATA = "data";
    public static final String TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP =
            "type_live_blog_added_at_time_stamp"; // Dummy type specifically for 'TheQuint'
    public static final String TYPE_SORT_CARDS = "type_sort_cards"; // Dummy type specifically
    // for 'TheQuint'
    public static final String TYPE_NESTED_STORY = "type_nested_story";

    private static final String SUB_TYPE_STORY_ELEMENT_SUMMARY = "summary";
    private static final String SUB_TYPE_STORY_ELEMENT_TWEET = "tweet";
    private static final String SUB_TYPE_STORY_ELEMENT_QUOTE = "quote";
    private static final String SUB_TYPE_STORY_ELEMENT_BLOCKQUOTE = "blockquote";
    private static final String SUB_TYPE_STORY_ELEMENT_BLURB = "blurb";
    private static final String SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY = "image-gallery";
    private static final String SUB_TYPE_STORY_ELEMENT_BIG_FACT = "bigfact";
    private static final String SUB_TYPE_STORY_ELEMENT_QUESTION_ANSWER = "q-and-a";
    private static final String SUB_TYPE_STORY_ELEMENT_BRIGHTCOVE = "brightcove-video";
    private static final String SUB_TYPE_STORY_ELEMENT_ALSO_READ = "also-read";
    private static final String SUB_TYPE_STORY_ELEMENT_POLLTYPE = "opinion-poll";
    private static final String SUB_TYPE_STORY_ELEMENT_TABLE = "table";
    private static final String SUB_TYPE_STORY_ELEMENT_QUESTION = "question";
    private static final String SUB_TYPE_STORY_ELEMENT_ANSWER = "answer";

    public static final Set<String> SUPPORTED_ELEM_TYPES = new HashSet<>();
    public static final String ID_HERO = "hero";


    private static final Pattern TWITTER_EMBED_PATTERN
            = Pattern.compile("(https|http)://twitter\\.com/(\\w{1,15})/status/(\\d+)");

    private static final Pattern POLLTYPE_EMBED_PATTERN
            = Pattern.compile("data-polltype-embed-id=\\d+");

    static {
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_VIRTUAL_QUOTE);
        SUPPORTED_ELEM_TYPES.add(TYPE_TEXT);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_JSEMBED);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_SOUNDCLOUD_AUDIO);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_AUDIO);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_MEDIA);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_YOUTUBE_VIDEO);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_VIDEO);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_IMAGE);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_TITLE);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_COMPOSITE);
        SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_DATA);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_TWEET);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_QUOTE);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BLOCKQUOTE);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BLURB);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BIG_FACT);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_QUESTION_ANSWER);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BRIGHTCOVE);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_POLLTYPE);
        SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_TABLE);
        SUPPORTED_ELEM_TYPES.add(TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP);
        SUPPORTED_ELEM_TYPES.add(TYPE_SORT_CARDS);
        SUPPORTED_ELEM_TYPES.add(TYPE_NESTED_STORY);
    }


    @SerializedName("id")
    protected String id = "";
    @SerializedName("title")
    protected String title;
    @SerializedName("description")
    protected String description;
    @SerializedName("text")
    protected String text;
    @SerializedName("type")
    protected String type = "";
    @SerializedName("image-s3-key")
    protected String imageS3Key;
    @SerializedName("embed-url")
    private String embedUrl;
    @SerializedName("url")
    private String url;
    @SerializedName("page-url")
    private String pageUrl;
    @SerializedName("embed-js")
    private String embedJs;
    @SerializedName("image-metadata")
    private ImageMetaData imageMeta;
    @SerializedName("subtype")
    private String subType;
    @SerializedName("metadata")
    private StoryElementSubTypeMetaData subTypeMeta;
    @SerializedName("story-elements")
    private List<StoryElement> storyElements = Collections.emptyList();
    @SerializedName("polltype-id")
    public String polltypeId;
    @SerializedName("data")
    private StoryElementData data;
    @SerializedName("image-attribution")
    protected String imageAttribution;


    protected Story nestedStory;


    private String decodedJsEmbed = "";
    private Long tweetId;
    private boolean isTypeJsEmbedWithTwitter;
    private Long cardAddedAt;// Used for TheQuint's LiveBlogTemplate
    private boolean keyEvent;// Used for TheQuint's LiveBlogTemplate
    private boolean cardPinStatus;

    @Override
    public String toString() {
        return "StoryElement{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", imageS3Key='" + imageS3Key + '\'' +
                ", embedUrl='" + embedUrl + '\'' +
                ", url='" + url + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", embedJs='" + embedJs + '\'' +
                ", imageMeta=" + imageMeta +
                ", subType='" + subType + '\'' +
                ", subTypeMeta=" + subTypeMeta +
                ", storyElements=" + storyElements +
                ", decodedJsEmbed='" + decodedJsEmbed + '\'' +
                ", tweetId=" + tweetId +
                ", isTypeJsEmbedWithTwitter=" + isTypeJsEmbedWithTwitter +
                ", data=" + data +
                ", imageAttribution=" + imageAttribution +
                '}';
    }

    StoryElement(StoryElement element) {
        this.id = element.id;
        this.title = element.title;
        this.description = element.description;
        this.text = element.text;
        this.type = element.type;
        this.imageS3Key = element.imageS3Key;
        this.embedUrl = element.embedUrl;
        this.url = element.url;
        this.pageUrl = element.pageUrl;
        this.subType = element.subType;
        this.polltypeId = element.polltypeId;
        this.cardAddedAt = element.cardAddedAt;
        this.keyEvent = element.keyEvent;
        this.imageAttribution = element.imageAttribution;
        this.cardPinStatus = element.cardPinStatus;
    }

    /**
     * @return String Id of StoryElement
     */
    public String id() {
        return id;
    }

    /**
     * @return String title of StoryElement
     */
    public String title() {
        return title;
    }

    /**
     * @return String description of StoryElement
     */
    public String description() {
        return description;
    }

    /**
     * @return String StoryElement text
     */
    public String text() {
        return text;
    }

    /**
     * @return String StoryElement type
     */
    public String type() {
        return type;
    }

    /**
     * @return String image Url for StoryElement
     */
    public String imageS3Key() {
        return imageS3Key;
    }

    /**
     * @return String embed Url
     */
    public String embedUrl() {
        return embedUrl;
    }

    /**
     * @return String StoryElement url
     */
    public String url() {
        return url;
    }

    /**
     * @return String StoryElement page urls
     */
    public String pageUrl() {
        return pageUrl;
    }

    /**
     * @return String StoryElement embed js
     */
    public String embedJs() {
        return embedJs;
    }

    /**
     * @return {@link ImageMetaData}
     */
    public ImageMetaData imageMeta() {
        return imageMeta;
    }

    /**
     * @return String StoryElement subtype
     */
    public String subType() {
        return subType;
    }

    /**
     * @return {@link StoryElementSubTypeMetaData}
     */
    public StoryElementSubTypeMetaData subTypeMeta() {
        return subTypeMeta;
    }

    /**
     * @return List of internal StoryElements
     */
    public List<StoryElement> storyElements() {
        return storyElements;
    }

    /**
     * @return true is StoryElement is of type {@link #TYPE_STORY_ELEMENT_JSEMBED} and Twitter
     */
    public boolean isTypeJsEmbedWithTwitter() {
        return isTypeJsEmbedWithTwitter;
    }

    /**
     * @return String decoded Js Embed
     */
    public String decodedJsEmbed() {
        return decodedJsEmbed;
    }

    /**
     * @return contents of a table element and the content type
     */
    public StoryElementData data() {
        return data;
    }

    /**
     * returns a partial clone of the story element to be used (used for breaking current story
     * element into multiple)
     *
     * @param storyElement - the story element that needs to be broken
     * @return the partially cloned story element object
     */
    public static StoryElement fromStoryElement(StoryElement storyElement) {
        return new StoryElement(storyElement);
    }

    /**
     * set the type of the calling story element as quote
     */
    public void setTypeAsQuote() {
        type = TYPE_STORY_ELEMENT_VIRTUAL_QUOTE;
    }

    /**
     * set the type of the calling story element as blog posted at
     */
    public void setTypeAsBlogPostedAt() {
        type = TYPE_STORY_ELEMENT_BLOG_POSTED_AT;
    }

    /**
     * set the type of the calling story element as blog updated at
     */
    public void setTypeAsBlogUpdatedAt() {
        type = TYPE_STORY_ELEMENT_BLOG_UPDATED_AT;
    }

    /**
     * @return whether this story element is text type
     */
    public boolean isTypeText() {
        return type.equalsIgnoreCase(TYPE_TEXT);
    }

    /**
     * @return whether this story element is image type and gif
     */
    public boolean isTypeImageGif() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_IMAGE) &&
                imageS3Key.endsWith(".gif");
    }

    /**
     * @return whether this story element is image type
     */
    public boolean isTypeImage() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_IMAGE);
    }

    /**
     * @return whether this story element is video type
     */
    public boolean isTypeVideo() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_VIDEO);
    }

    /**
     * @return whether this story element is youtube type
     */
    public boolean isTypeYoutube() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_YOUTUBE_VIDEO);
    }


    /**
     * @return whether this story element is BitGravity Video
     */
    public boolean isTypeBitGravity() {
        if (subType != null)
            return isTypeExternal() && subType.equalsIgnoreCase(
                    TYPE_STORY_ELEMENT_SUB_BIT_GRAVITY);
        else return false;
    }

    /**
     * @return whether this story element is media type
     */
    public boolean isTypeMedia() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_MEDIA);
    }

    /**
     * @return whether this story element is audio type
     */
    public boolean isTypeAudio() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_AUDIO);
    }

    /**
     * @return whether this story element is quote type
     */
    public boolean isTypeVirtualQuote() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_VIRTUAL_QUOTE);
    }

    /**
     * @return whether this story element is blog posted at type
     */
    public boolean isTypeBlogPostedAt() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_BLOG_POSTED_AT);
    }

    /**
     * @return whether this story element is blog updated at type
     */
    public boolean isTypeBlogUpdatedAt() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_BLOG_UPDATED_AT);
    }

    /**
     * @return whether this story element is soundcloud type
     */
    public boolean isTypeSoundcloud() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_SOUNDCLOUD_AUDIO);
    }

    /**
     * @return whether this story element is title type
     */
    public boolean isTypeTitle() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_TITLE);
    }

    /**
     * @return whether this story element is title type
     */
    public boolean isTypeTable() {
        if (subType != null)
            return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_DATA) &&
                subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_TABLE);
        return false;
    }

    /**
     * @return whether this story element is title type
     */
    public boolean isTypeTimeStamp() {
        return type.equalsIgnoreCase(TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP);
    }

    /**
     * @return whether this story element is dummy sort card type
     */
    public boolean isTypeSortCards() {
        return type.equalsIgnoreCase(TYPE_SORT_CARDS);
    }

    /**
     * @return whether this story element is dummy type nested story
     */
    public boolean isTypeNestedStory() {
        return type.equalsIgnoreCase(TYPE_NESTED_STORY);
    }


    /**
     * @return whether this story element is poll type
     */
    public boolean isTypePollType() {

        if (type.equalsIgnoreCase(TYPE_STORY_ELEMENT_POLLTYPE)) {
            return true;
        } else if (!TextUtils.isEmpty(polltypeId)) {
            return true;
        } else if (isTypeJsembed()) {
            if (TextUtils.isEmpty(decodedJsEmbed)) {
                try {
                    byte[] data = Base64.decode(embedJs, Base64.DEFAULT);
                    String decodedJsEmbed = new String(data, "UTF-8");
                    decodedJsEmbed = decodedJsEmbed.replace("src=\"//", "src=\"http://");
                    Matcher m = POLLTYPE_EMBED_PATTERN.matcher(decodedJsEmbed);
                    String pollId = null;
                    while (m.find() && pollId == null) {
                        System.out.println(m.group());
                        pollId = m.group();
                    }
                    if (TextUtils.isEmpty(pollId)) {
                        return false;
                    } else {
                        polltypeId = pollId.replaceAll("[^0-9]", "");
                        return true;
                    }
                } catch (UnsupportedEncodingException e) {
                    return false;
                }
            } else {
                Matcher m = POLLTYPE_EMBED_PATTERN.matcher(decodedJsEmbed);
                String pollId = null;
                while (m.find() && pollId == null) {
                    System.out.println(m.group());
                    pollId = m.group();
                }
                if (TextUtils.isEmpty(pollId)) {
                    return false;
                } else {
                    polltypeId = pollId.replaceAll("[^0-9]", "");
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * @return whether this story element is external file type
     */

    public boolean isTypeExternal() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_EXTERNAL);
    }

    /**
     * @return whether the type of this element is summary
     */
    public boolean isTypeSummary() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_SUMMARY);
        else return false;
    }

    /**
     * @return whether the type of the element is quote
     */
    public boolean isTypeQuote() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_QUOTE);
        else return false;
    }

    /**
     * @return whether the type of the element is blockquote
     */
    public boolean isTypeBlockQuote() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_BLOCKQUOTE);
        else return false;
    }

    public boolean isTypeQuestionAnswer() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_QUESTION_ANSWER);
        else return false;
    }

    public boolean isTypeQuestion() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_QUESTION);
        else return false;
    }

    public boolean isTypeAnswer() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_ANSWER);
        else return false;
    }


    /**
     * @return whether the type of the element is blurb
     */
    public boolean isTypeBlurb() {
        if (subType != null)
            return isTypeText() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_BLURB);
        else return false;
    }

    /**
     * @return whether the type of the element is composite
     */
    public boolean isTypeComposite() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_COMPOSITE);
    }

    /**
     * @return whether the type of the element is image gallery
     */
    public boolean isImageGallery() {
        if (subType != null)
            return isTypeComposite() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY) && subTypeMeta.isTypeGallery();
        else return false;
    }

    /**
     * Check
     *
     * @return whether the type of the element is image slideshow
     */
    public boolean isImageSlideshow() {
        if (subType != null)
            return isTypeComposite() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY) && subTypeMeta.isTypeSlideShow();
        else return false;
    }

    /**
     * @return whether this story element is jsembed type
     */
    public boolean isTypeJsembed() {
        return type.equalsIgnoreCase(TYPE_STORY_ELEMENT_JSEMBED);
    }

    public boolean isTypeTwitter() {
        if (subType != null)
            return isTypeJsembed() && subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_TWEET);
        else return false;
    }

    /**
     * @return whether this story element is type big fact
     */
    public boolean isTypeBigFact() {
        if (subType != null)
            return isTypeText() && subType.endsWith(SUB_TYPE_STORY_ELEMENT_BIG_FACT);
        else return false;
    }

    /**
     * @return whether this story element is type brightcove-video
     */
    public boolean isTypeBrightCoveVideo() {
        if (subType != null)
            return isTypeExternal() && subType.endsWith(SUB_TYPE_STORY_ELEMENT_BRIGHTCOVE);
        else return false;
    }

    /**
     * @return whether this story element is type AlsoRead
     */
    public boolean isAlsoRead() {
        if (subType != null)
            return isTypeText() && subType.endsWith(SUB_TYPE_STORY_ELEMENT_ALSO_READ);
        else return false;
    }

    public Long tweetId() {
        return tweetId;
    }

    public Long getCardAddedAt() {
        return cardAddedAt;
    }

    public void setCardAddedAt(Long cardAddedAt) {
        this.cardAddedAt = cardAddedAt;
    }

    public boolean isKeyEvent() {
        return keyEvent;
    }

    public void setKeyEvent(boolean keyEvent) {
        this.keyEvent = keyEvent;
    }

    /**
     * Parses and prepares this story element for twitter
     */
    public void prepareForTwitter() {
        isTypeJsEmbedWithTwitter = false;
        if (subType != null)
            if (subType.equalsIgnoreCase(SUB_TYPE_STORY_ELEMENT_TWEET)
                    && !TextUtils.isEmpty(subTypeMeta.getTweetId())) {
                //Timber.d("Sub type matches twitter");
                try {

                    tweetId = Long.valueOf(subTypeMeta.getTweetId());
                    isTypeJsEmbedWithTwitter = true;
                } catch (Exception e) {
                    //Timber.e(e, "Failed parsing twitter sub type");
                }
            } else {
                if (!TextUtils.isEmpty(embedJs)) {
                    try {
                        byte[] data = Base64.decode(embedJs, Base64.DEFAULT);
                        decodedJsEmbed = new String(data, "UTF-8");
                        decodedJsEmbed = decodedJsEmbed.replace("src=\"//", "src=\"http://");
                        Matcher matcher = TWITTER_EMBED_PATTERN.matcher(decodedJsEmbed);
                        if (matcher.find()) {
                            String tid = matcher.group(3);
                            tweetId = Long.valueOf(tid);
                            isTypeJsEmbedWithTwitter = true;
                        }
                    } catch (Exception e) {
                        //Timber.e(e, "Failed parsing js embed");
                        decodedJsEmbed = "";
                    }
                }
            }
    }

    /**
     * @return whether this story element image is landscape, true by default
     */
    public boolean isImageLandscape() {
        if (imageMeta != null) {
            return imageMeta.getWidth() > imageMeta.getHeight();
        }
        return true;
    }

    /**
     * @return String imageAttribution of StoryElement
     */
    public String imageAttribution() {
        return imageAttribution;
    }

    public StoryElement() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.text);
        dest.writeString(this.type);
        dest.writeString(this.imageS3Key);
        dest.writeString(this.embedUrl);
        dest.writeString(this.url);
        dest.writeString(this.pageUrl);
        dest.writeString(this.embedJs);
        dest.writeParcelable(this.imageMeta, flags);
        dest.writeString(this.subType);
        dest.writeParcelable(this.subTypeMeta, flags);
        dest.writeTypedList(this.storyElements);
        dest.writeString(this.polltypeId);
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.decodedJsEmbed);
        dest.writeValue(this.tweetId);
        dest.writeByte(this.isTypeJsEmbedWithTwitter ? (byte) 1 : (byte) 0);
        dest.writeValue(this.cardAddedAt);
        dest.writeByte((byte) (keyEvent ? 1 : 0));
        dest.writeString(this.imageAttribution);
        dest.writeByte((byte) (cardPinStatus ? 1 : 0));
    }

    protected StoryElement(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.text = in.readString();
        this.type = in.readString();
        this.imageS3Key = in.readString();
        this.embedUrl = in.readString();
        this.url = in.readString();
        this.pageUrl = in.readString();
        this.embedJs = in.readString();
        this.imageMeta = in.readParcelable(ImageMetaData.class.getClassLoader());
        this.subType = in.readString();
        this.subTypeMeta = in.readParcelable(StoryElementSubTypeMetaData.class.getClassLoader());
        this.storyElements = in.createTypedArrayList(StoryElement.CREATOR);
        this.polltypeId = in.readString();
        this.data = in.readParcelable(StoryElementData.class.getClassLoader());
        this.decodedJsEmbed = in.readString();
        this.tweetId = (Long) in.readValue(Long.class.getClassLoader());
        this.isTypeJsEmbedWithTwitter = in.readByte() != 0;
        this.cardAddedAt = (Long) in.readValue(Long.class.getClassLoader());
        this.keyEvent = in.readByte() != 0;
        this.imageAttribution = in.readString();
        this.cardPinStatus = in.readByte() != 0;
    }

    public static final Creator<StoryElement> CREATOR = new Creator<StoryElement>() {
        @Override
        public StoryElement createFromParcel(Parcel source) {
            return new StoryElement(source);
        }

        @Override
        public StoryElement[] newArray(int size) {
            return new StoryElement[size];
        }
    };

    public Story nestedStory() {
        return nestedStory;
    }

    public void nestedStory(Story nestedStory) {
        this.nestedStory = nestedStory;
    }

    public void title(String title) {
        this.title = title;
    }

    public boolean isCardPinned() {
        return cardPinStatus;
    }

    public void setCardPinStatus(boolean cardPinStatus) {
        this.cardPinStatus = cardPinStatus;
    }
}
