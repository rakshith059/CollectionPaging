package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quintype.com.templatecollectionwithrx.models.author.Author;
import quintype.com.templatecollectionwithrx.models.entities.EntityModel;
import quintype.com.templatecollectionwithrx.models.sections.Section;

public class Story implements Parcelable {

    public static final String[] DEFAULT_FIELDS = {
            "id", "hero-image-s3-key", "sections", "headline", "subheadline",
            "author-name", "created-at", "hero-image-caption",
            "story-content-id", "tags", "hero-image-metadata", "story-template", "slug",
            "summary", "last-published-at", "metadata", "alternative", "access"
    };

    public static final String INVALID_ID = "-1";
    public static final Story INVALID_STORY = new Story();

    //Story Types
    public static final String TYPE_TEMPLATE_LISTICLE = "listicle";
    public static final String TYPE_TEMPLATE_VIDEO = "video";
    public static final String TYPE_TEMPLATE_LIVE_BLOG = "live-blog";
    public static final String TYPE_TEMPLATE_NEWS_ELSEWHERE = "news-elsewhere";
    public static final String TYPE_BULLET_ASCENDING = "123";
    public static final String TYPE_BULLET_DESCENDING = "321";
    public static final String TYPE_BULLET_BULLETED = "bullets";
    public static final String TYPE_TEMPLATE_REVIEW = "review";

    public static final String STORY_THEME = "theme";
    public static final String STORY_THEME_LONGFORM = "longform";
    public static final String STORY_THEME_PARALLAX = "parallax";

    static {
        INVALID_STORY.id = INVALID_ID;
    }

    @SerializedName("id")
    protected String id;
    /**
     * Epoch in millis
     */
    @SerializedName("updated-at")
    public long updatedAt;
    @SerializedName("tags")
    public List<Tag> tags;
    @SerializedName("headline")
    public String headline;
    @SerializedName("subheadline")
    public String subHeadLine;
    @SerializedName("story-content-id")
    public String storyContentId;
    @SerializedName("slug")
    public String slug;
    @SerializedName("linked-stories")
    public HashMap<String, Story> linkedStories;
    @SerializedName("last-published-at")
    public long lastPublishedAt;
    @SerializedName("sections")
    public List<Section> sections;
    @SerializedName("owner-name")
    public String ownerName;
    @SerializedName("access")
    public String access;
    @SerializedName("push-notification")
    public String pushNotification;
    @SerializedName("comments")
    public String comments;


    @SerializedName("is-published")
    public String isPublished;
    @SerializedName("publisher-id")
    public String publisherId;
    @SerializedName("published-at")
    public long publishedAt;


    @SerializedName("storyline-id")
    public String storyLineId;
    @SerializedName("storyline-title")
    public String storylineTitle;
    @SerializedName("summary")
    public String summary;
    @SerializedName("status")
    public String status;
    @SerializedName("hero-image-s3-key")
    public String heroImageS3Key;
    @SerializedName("cards")
    public List<Card> cards = Collections.emptyList();
    @SerializedName("story-version-id")
    public String storyVersionId;


    @SerializedName("author-name")
    public String authorName;
    @SerializedName("author-id")
    public String authorId;
    @SerializedName("owner-id")
    public String ownerId;
    @SerializedName("first-published-at")
    public long firstPublishedAt;
    @SerializedName("hero-image-caption")
    public String heroImageCaption;
    @SerializedName("hero-image-attribution")
    public String heroImageAttribution;
    @SerializedName("version")
    public String version;
    @SerializedName("bullet-type")
    public String bulletType;
    /**
     * Epoch in millis
     */
    @SerializedName("created-at")
    public long createdAt;

    @SerializedName("assignee-id")
    public String assigneeId;
    @SerializedName("assignee-name")
    public String assigneeName;

    public void cards(List<Card> cards) {
        this.cards = cards;
    }

    @SerializedName("hero-image-metadata")
    public ImageMetaData heroImageMeta;
    @SerializedName("story-template")
    public String template;
    @SerializedName("metadata")
    public StoryMetaData storyMetaData;
    @SerializedName("authors")
    public List<Author> authors = Collections.emptyList();

    @SerializedName("alternative")
    public Alternative alternative;
    @SerializedName("linked-entities")
    public JsonArray linkedEntities;

    public List<EntityModel> storyEntityList;
    public Map<String, EntityModel> expandedEntitiesMap;

    /**
     * Parses the story attributes json
     *
     * @return parsed list of story level entities
     */
//    public List<EntityModel> entitiesInStoryAttributes() {
//        //if story entities doesn't have anything yet, try to parse it from the json
//        if (storyEntityList == null || storyEntityList.isEmpty()) {
//            storyEntityList = new ArrayList<>();
//            Map<String, EntityModel> entityModelMap = parsedEntityList();
//            JsonObject storyAttributes = storyMetaData.storyAttributes();
//            //assumption: linked entities has valid json data and not residual data from deleted
//            //entities.
//            if (hasLinkedEntities() && storyAttributes != null) {
//                JsonObject storyAttributesJsonObject = storyAttributes.getAsJsonObject();
//                //parsing the storyAttributes json to get the id/name list of entities
//                for (Map.Entry<String, JsonElement> entry : storyAttributesJsonObject.entrySet()) {
//                    //getting the array at this position in the story attribute set
//                    String key = entry.getKey();
//                    JsonElement attrElement = storyAttributesJsonObject.get(key);
//                    JsonArray jsonArrayElement = attrElement.getAsJsonArray();
//
//                    //parsing id and name as this will be constant
//                    for (int i = 0; i < jsonArrayElement.size(); i++) {
//                        JsonElement jsonElement = jsonArrayElement.get(i);
//                        //a json primitive is definitely not our guy,
//                        //nor is one that doesn't have an id
//                        if (!jsonElement.isJsonPrimitive() &&
//                                !jsonElement.getAsJsonObject().get("id").isJsonNull())
//                            storyEntityList.add(entityModelMap.get(
//                                    jsonElement.getAsJsonObject().get("id").getAsString())
//                            );
//                    }
//                }
//            }
//        }
//        return storyEntityList;
//    }

    /**
     * Parses all the linked entities Json into the model class and then puts them in a map
     * with their corresponding IDs as keys
     *
     * @return all linked entities parsed into their respective model classes
     */
//    public Map<String, EntityModel> parsedEntityList() {
//        if (linkedEntities != null &&
//                (expandedEntitiesMap == null || expandedEntitiesMap.isEmpty())) {
//            //get the model classes for the current app
//            Map<String, Class> typeClassMap = Quintype.config().entityTypeModelMap();
//            if (typeClassMap == null)
//                return new HashMap<>();
//            expandedEntitiesMap = new HashMap<>(linkedEntities.size());
//            for (JsonElement element : linkedEntities) {
//                if (!element.isJsonPrimitive()) { //to eliminate residual deleted linked entities
//                    //get the .class from the map using the type name from the jsonObject
//                    //and use that to parse the entity
//                    EntityModel entity = EntityModel.createFromJson(element.getAsJsonObject(),
//                            typeClassMap.get(element.getAsJsonObject().get("type").getAsString()));
//                    expandedEntitiesMap.put(entity.id(), entity);
//                }
//            }
//        }
//        return expandedEntitiesMap;
//    }
    public boolean hasLinkedEntities() {
        return linkedEntities != null;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", tags=" + tags +
                ", headline='" + headline + '\'' +
                ", subHeadLine='" + subHeadLine + '\'' +
                ", storyContentId='" + storyContentId + '\'' +
                ", slug='" + slug + '\'' +
                ", linkedStories='" + linkedStories + '\'' +
                ", lastPublishedAt=" + lastPublishedAt +
                ", sections=" + sections +
                ", ownerName='" + ownerName + '\'' +
                ", access='" + access + '\'' +
                ", pushNotification='" + pushNotification + '\'' +
                ", comments='" + comments + '\'' +
                ", isPublished='" + isPublished + '\'' +
                ", publisherId='" + publisherId + '\'' +
                ", publishedAt=" + publishedAt +
                ", storyLineId='" + storyLineId + '\'' +
                ", storylineTitle='" + storylineTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", status='" + status + '\'' +
                ", heroImageS3Key='" + heroImageS3Key + '\'' +
                ", cards=" + cards +
                ", storyVersionId='" + storyVersionId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorId='" + authorId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", firstPublishedAt=" + firstPublishedAt +
                ", heroImageCaption='" + heroImageCaption + '\'' +
                ", heroImageAttribution='" + heroImageAttribution + '\'' +
                ", version='" + version + '\'' +
                ", bulletType='" + bulletType + '\'' +
                ", createdAt=" + createdAt +
                ", assigneeId='" + assigneeId + '\'' +
                ", assigneeName='" + assigneeName + '\'' +
                ", heroImageMeta=" + heroImageMeta +
                ", template='" + template + '\'' +
                ", storyMetaData=" + storyMetaData +
                ", authors=" + authors +
                ", alternative=" + alternative +
                ", linkedEntities=" + linkedEntities +
                '}';
    }

    /**
     * @return a JSON Array of linked entities
     */
    public JsonArray linkedEntities() {
        return linkedEntities;
    }

    /**
     * @return {@link Long} updated time in millis
     */
    public long updatedAt() {
        return updatedAt;
    }

    /**
     * @return {@link List<Author>} list of authors in this story
     */
    public List<Author> authors() {
        return authors;
    }

    /**
     * @return List of story tags
     */
    public List<Tag> tags() {
        return tags;
    }

    /**
     * @return String headline
     */
    public String headline() {
        return headline;
    }

    /**
     * @return String subHeadLine
     */
    public String subHeadLine() {
        return subHeadLine;
    }

    /**
     * @return String content id
     */
    public String contentId() {
        return storyContentId;
    }

    /**
     * @return String content id
     */
    public void contentId(String storyContentId) {
        this.storyContentId = storyContentId;
    }

    /**
     * @return String slug
     */
    public String slug() {
        return slug;
    }

    public String getLinkedStorySlug(String storyId) {
        if (!TextUtils.isEmpty(storyId) && linkedStories != null && !linkedStories.isEmpty()) {
            Story linkedStoryValue = linkedStories.get(storyId);
            if (linkedStoryValue != null)
                return linkedStoryValue.slug;
            else return null;
        } else {
            return null;
        }
    }

    /**
     * @return {@link Long} last published time in millis
     */
    public long lastPublishedAt() {
        return lastPublishedAt;
    }

    /**
     * @return List of sections the story is categorised into
     */
    public List<Section> sections() {
        return sections;
    }

    /**
     * @return String owner name
     */
    public String ownerName() {
        return ownerName;
    }

    /**
     * @return String push notification
     */
    public String pushNotification() {
        return pushNotification;
    }

    /**
     * @return String story comments
     */
    public String comments() {
        return comments;
    }

    /**
     * @return String status if published
     */
    public String isPublished() {
        return isPublished;
    }

    /**
     * @return String publisher id
     */
    public String publisherId() {
        return publisherId;
    }

    /**
     * @return {@link Long} Story published time in millis
     */
    public long publishedAt() {
        return publishedAt;
    }

    /**
     * @return String story line id
     */
    public String storyLineId() {
        return storyLineId;
    }

    /**
     * @return String story line title
     */
    public String storylineTitle() {
        return storylineTitle;
    }

    /**
     * @return String story summary
     */
    public String summary() {
        return summary;
    }

    /**
     * @return String story access
     */
    public String access() {
        return access;
    }

    /**
     * @return String story status
     */
    public String status() {
        return status;
    }

    /**
     * @return String hero-image s3 key
     */
    public String heroImageS3Key() {
        return heroImageS3Key;
    }

    /**
     * @return List of story cards
     */
    public List<Card> cards() {
        return cards;
    }

    /**
     * @return String story version id
     */
    public String storyVersionId() {
        return storyVersionId;
    }

    /**
     * @return String author name
     */
    public String authorName() {
        return authorName;
    }

    /**
     * @return String author Id
     */
    public String authorId() {
        return authorId;
    }

    /**
     * @return String owner Id
     */
    public String ownerId() {
        return ownerId;
    }

    /**
     * @return {@link Long} first published time in millis
     */
    public long firstPublishedAt() {
        return firstPublishedAt;
    }

    /**
     * @return String hero image caption
     */
    public String heroImageCaption() {
        return heroImageCaption;
    }

    /**
     * @return String hero image attribution
     */
    public String heroImageAttribution() {
        return heroImageAttribution;
    }

    /**
     * @return String story version
     */
    public String version() {
        return version;
    }

    /**
     * @return String bullet type
     */
    public String bulletType() {
        return bulletType;
    }

    /**
     * @return {@link Long} Story creation time in millis
     */
    public long createdAt() {
        return createdAt;
    }

    /**
     * @return String Assignee Id
     */
    public String assigneeId() {
        return assigneeId;
    }

    /**
     * @return String Assignee Name
     */
    public String assigneeName() {
        return assigneeName;
    }

    /**
     * @return {@link ImageMetaData}
     */
    public ImageMetaData heroImageMeta() {
        return heroImageMeta;
    }

    /**
     * @return String story template
     */
    public String template() {
        return template;
    }

    /**
     * @return {@link StoryMetaData}
     */
    public StoryMetaData storyMetaData() {
        return storyMetaData;
    }

    /**
     * @return true is story is marked read false otherwise
     */
//    public boolean isRead() {
//        if (Quintype.status() == Quintype.CONNECTED) {
//            Set<String> set = Quintype.config().sharedPreferences().getStringSet
//                    ("stories_is_read", new HashSet<String>());
//            return set.contains(id());
//        }
//        return false;
//    }

    /**
     * Mark a story as read.
     *
     * @param isRead true is the story should be marked read false otherwise
     */
//    public void markAsRead(boolean isRead) {
//        if (Quintype.status() == Quintype.CONNECTED) {
//            Set<String> set = Quintype.config().sharedPreferences().getStringSet
//                    ("stories_is_read", new HashSet<String>());
//            if (isRead) {
//                set.add(id());
//            } else {
//                set.remove(id());
//            }
//
//            SharedPreferences.Editor editor = Quintype.config().sharedPreferences().edit();
//            editor.putStringSet("stories_is_read", set).apply();
//        }
//    }

    /**
     * @return Whether this story has story line id
     */
    public boolean hasStoryLineId() {
        return !TextUtils.isEmpty(storyLineId);
    }

    /**
     * checks if all the fields of the story are present in the calling story object.
     *
     * @return if all fields are not present returns true other wise returns false
     */
    public boolean requiresFields() {
        return cards.isEmpty();
    }

    /**
     * tells all the cards in the story to modify their story elements to suit the UI
     */
//    public void buildUIStoryElements() {
//        if (isListicle() && cards != null && cards.size() > 0) {
//            int start = 0;
//            int incrementer = 0;
//
//            if (isAscendingBulletType()) {
//                //ascending order
//                start = 1;
//                incrementer = 1;
//            } else if (isDescendingBulletType()) {
//                //descending order
//                start = cards.size();
//                incrementer = -1;
//            } else if (isBulletedBulletType()) {
//                start = 0;
//                incrementer = 0;
//            }
//
//            for (int i = 0; i < cards.size(); i++) {
//                //in listicle first element is always a title
//                StoryElement elem = cards.get(i).storyElements().get(0);
//                if (elem.isTypeTitle()) {
//                    if (incrementer != 0) {
//                        elem.text = ((start + (incrementer * i)) + ". " + elem.text());
//                    } else {
//                        elem.text = ('\u2022' + " " + elem.text());
//                    }
//                }
//            }
//        }
//        if (cards != null) {
//            for (Card card : cards) {
//                card.buildUIStoryElements();
//            }
//            //now handle live-blog type
//            if (isLiveBlogTemplate()) {
//                for (Card card : cards) {
//                    StoryElement postedAtElem = new StoryElement();
//                    postedAtElem.text = (String.valueOf(card.cardAddedAt()));
//                    postedAtElem.setTypeAsBlogPostedAt();
//                    card.uiStoryElements().add(0, postedAtElem);
//                    if (card.cardUpdatedAt() > 0 && card.cardUpdatedAt() != card.cardAddedAt()) {
//                        StoryElement updatedAtElem = new StoryElement();
//                        updatedAtElem.text = (String.valueOf(card.cardUpdatedAt()));
//                        updatedAtElem.setTypeAsBlogUpdatedAt();
//                        card.uiStoryElements().add(updatedAtElem);
//                    }
//                }
//            }
//        }
//    }

//    private boolean isAscendingBulletType() {
//        return StringUtils.endsWithIgnoreCase(TYPE_BULLET_ASCENDING, bulletType);
//    }
//
//    private boolean isDescendingBulletType() {
//        return StringUtils.endsWithIgnoreCase(TYPE_BULLET_DESCENDING, bulletType);
//    }
//
//    private boolean isBulletedBulletType() {
//        return StringUtils.endsWithIgnoreCase(TYPE_BULLET_BULLETED, bulletType);
//    }

    /**
     * checks if the story is invalid
     *
     * @param story - the story to be checked
     * @return true if invalid, false otherwise
     */
    public static boolean isInvalid(Story story) {
        return INVALID_ID.equals(story.id);
    }

    /**
     * method to get an instance of an invalid story
     *
     * @return - an invalid story instance
     */
    public static Story getInvalidStory() {
        return INVALID_STORY;
    }

    /**
     * a method to get a invalid story with the slug set so that the actual story can be queried
     * from its slug
     *
     * @param slug - the slug that needs to be set in the slug story
     * @return a new story object
     */
    public static Story getSlugStory(String slug) {
        Story slugStory = getInvalidStory();
        if (slug == null || slug.isEmpty()) {
            throw new IllegalArgumentException("Slug cannot be null or empty when asking for a " +
                    "slug story");
        }
        slugStory.slug = slug;
        return slugStory;
    }

    /**
     * @return whether hero image is landscape, default value is true
     */
    public boolean isHeroImageLandscape() {
        if (heroImageMeta != null) {
            return heroImageMeta.getWidth() > heroImageMeta.getHeight();
        }
        return true;
    }

    /**
     * @return whether this story element is image type and gif
     */
//    public boolean isTypeImageGif() {
//        if (!StringUtils.isEmpty(heroImageS3Key()))
//            return heroImageS3Key().endsWith(".gif");
//        else
//            return false;
//    }

    /**
     * checks if the story passed as a parameter is an invalid story but contains a slug from
     * which actual story can be
     * queried
     *
     * @param story - a reference to the story
     * @return true if the story is a slug story, false otherwise
     */
//    public static boolean isSlugStory(Story story) {
//        return (StringUtils.isNotEmpty(story.slug()));
//    }

    /**
     * check if the story has tags.
     *
     * @return true if the story has tags false otherwise
     */
    public boolean hasTags() {
        return (tags != null && !tags.isEmpty());
    }

    /**
     * check whether the story's template is a video template
     *
     * @return true if the story's template is a video template, false otherwise
     */
//    public boolean isVideoTemplate() {
//        return StringUtils.equalsIgnoreCase(TYPE_TEMPLATE_VIDEO, template);
//    }

    /**
     * check whether the story's template is a review template
     *
     * @return true if the story's template is a review template, false otherwise
     */
//    public boolean isReviewTemplate() {
//        return StringUtils.equalsIgnoreCase(TYPE_TEMPLATE_REVIEW, template);
//    }

    /**
     * check whether the story's template is a live blog
     *
     * @return true if the story's template is a live blog, false otherwise
     */
//    public boolean isLiveBlogTemplate() {
//        return StringUtils.equalsIgnoreCase(TYPE_TEMPLATE_LIVE_BLOG, template);
//    }

    /**
     * check whether the story's theme is a longform or parallax
     *
     * @return true if the story's theme is longform or parallax, false otherwise. As of now we
     * are considering both as a same. And looking only for the 1st item inside the 'theme' array.
     */
    public boolean isLongFormStory() {
        JsonObject storyAttributes = storyMetaData.getStoryAttributes();
        if (storyAttributes.has(STORY_THEME)) {
            JsonArray themeArray = storyAttributes.getAsJsonArray(STORY_THEME);
            if (themeArray.size() > 0)
                if (themeArray.get(0) != null && themeArray.get(0).getAsString().equals(STORY_THEME_LONGFORM) || themeArray.get(0)
                        .getAsString().equals(STORY_THEME_PARALLAX)) {
                    return true;
                }
        }
        return false;
    }

    /**
     * check whether the story's template is a listicle
     *
     * @return true if the story's template is a listicle, false otherwise
     */
//    public boolean isListicle() {
//        return StringUtils.equalsIgnoreCase(TYPE_TEMPLATE_LISTICLE, template);
//    }

    /**
     * check whether the story's template is a news elsewhere template
     *
     * @return true if the story's template is a news elsewhere template, false otherwise
     */
//    public boolean isNewsElseWhereTemplate() {
//        return StringUtils.equalsIgnoreCase(TYPE_TEMPLATE_NEWS_ELSEWHERE, template);
//    }

    /**
     * @return First youtube story element
     */
    public StoryElement getFirstYoutubeElement() {
        for (Card card : cards) {
            StoryElement elem = card.getFirstYoutubeElement();
            if (elem != null) {
                return elem;
            }
        }
        return null;
    }

    /**
     * @param given given section
     * @return true if story contains the given section
     */
//    public boolean hasSection(Section given) {
//        for (Section section : sections) {
//            if (StringUtils.equalsIgnoreCase(section.name(), given.name())) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * @return Id field of a story
     */
    public String id() {
        if (TextUtils.isEmpty(id)) {
            //Timber.d("Story id is empty, will fallback to content id");
            return storyContentId;
        } else {
            return id;
        }
    }

    /**
     * @return story element for hero element
     */
    public StoryElement getHeroElement() {
        StoryElement heroElement = new StoryElement();
        heroElement.imageS3Key = this.heroImageS3Key;
        heroElement.id = StoryElement.ID_HERO;
        heroElement.title = this.heroImageCaption;
        heroElement.type = StoryElement.TYPE_STORY_ELEMENT_IMAGE;
        return heroElement;
    }

    /**
     * @return list of all image elements along with a fake hero element
     */
//    public List<StoryElement> getImageElementsWithHeroElement() {
//        List<StoryElement> imageElements = new ArrayList<>();
//        StoryElement heroCard = getHeroElement();
//        imageElements.add(heroCard);
//        if (cards != null) {
//            for (Card card : cards) {
//                for (StoryElement elem : card.storyElements()) {
//                    if (elem.isTypeImage() && !elem.isTypeImageGif()) {
//                        imageElements.add(elem);
//                    }
//                }
//            }
//        }
//        return imageElements;
//    }

    /**
     * @return a list of all image elements along with hero element and gif elements
     */
//    public List<StoryElement> getAllImageElements() {
//        List<StoryElement> imageElements = new ArrayList<>();
//        StoryElement heroCard = getHeroElement();
//        imageElements.add(heroCard);
//        if (cards != null) {
//            for (Card card : cards) {
//                for (StoryElement elem : card.storyElements()) {
//                    if (elem.isTypeImage()) {
//                        imageElements.add(elem);
//                    }
//                }
//            }
//        }
//        return imageElements;
//    }
//
//    public List<StoryElement> getImageElementsInCards() {
//        List<StoryElement> imageElements = new ArrayList<>();
//        if (cards != null) {
//            for (Card card : cards) {
//                for (StoryElement elem : card.storyElements()) {
//                    if (elem.isTypeComposite() && (elem.subTypeMeta() != null && (elem
//                            .subTypeMeta().isTypeGallery()) || elem.subTypeMeta().isTypeSlideShow
//                            ())) {
//                        imageElements.addAll(elem.storyElements());
//                    }
//                }
//            }
//        }
//        return imageElements;
//    }
//
//    public List<StoryElement> getSlideshowImageElementsInCards() {
//        List<StoryElement> imageElements = new ArrayList<>();
//        if (cards != null) {
//            for (Card card : cards) {
//                for (StoryElement elem : card.storyElements()) {
//                    if (elem.isTypeComposite() || elem.subTypeMeta().isTypeSlideShow()) {
//                        imageElements.addAll(elem.storyElements());
//                    }
//                }
//            }
//        }
//        return imageElements;
//    }
    public Story() {
    }

    public Story(String id, String slug, String heroImageS3Key, String headline) {
        this.id = id;
        this.slug = slug;
        this.heroImageS3Key = heroImageS3Key;
        this.headline = headline;
    }

    /**
     * Created this constructor for TheQuint's Explainer Story
     *
     * @param id
     * @param cards
     */
    public Story(String id, List<Card> cards) {
        this.id = id;
        this.cards = cards;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(this.updatedAt);
        dest.writeTypedList(this.tags);
        dest.writeString(this.headline);
        dest.writeString(this.subHeadLine);
        dest.writeString(this.storyContentId);
        dest.writeString(this.slug);
        dest.writeMap(this.linkedStories);
        dest.writeLong(this.lastPublishedAt);
        dest.writeTypedList(this.sections);
        dest.writeString(this.ownerName);
        dest.writeString(this.access);
        dest.writeString(this.pushNotification);
        dest.writeString(this.comments);
        dest.writeString(this.isPublished);
        dest.writeString(this.publisherId);
        dest.writeLong(this.publishedAt);
        dest.writeString(this.storyLineId);
        dest.writeString(this.storylineTitle);
        dest.writeString(this.summary);
        dest.writeString(this.status);
        dest.writeString(this.heroImageS3Key);
        dest.writeTypedList(this.cards);
        dest.writeString(this.storyVersionId);
        dest.writeString(this.authorName);
        dest.writeString(this.authorId);
        dest.writeString(this.ownerId);
        dest.writeLong(this.firstPublishedAt);
        dest.writeString(this.heroImageCaption);
        dest.writeString(this.heroImageAttribution);
        dest.writeString(this.version);
        dest.writeString(this.bulletType);
        dest.writeLong(this.createdAt);
        dest.writeString(this.assigneeId);
        dest.writeString(this.assigneeName);
        dest.writeParcelable(this.heroImageMeta, flags);
        dest.writeString(this.template);
        dest.writeParcelable(this.storyMetaData, flags);
        dest.writeTypedList(this.authors);
        dest.writeParcelable(this.alternative, flags);
        dest.writeByte((byte) (linkedEntities == null ? 0 : 1));
        if (linkedEntities != null) {
            dest.writeString(linkedEntities.toString());
        }
    }

    protected Story(Parcel in) {
        this.id = in.readString();
        this.updatedAt = in.readLong();
        this.tags = in.createTypedArrayList(Tag.CREATOR);
        this.headline = in.readString();
        this.subHeadLine = in.readString();
        this.storyContentId = in.readString();
        this.slug = in.readString();
        this.linkedStories = in.readHashMap(Story.class.getClassLoader());
        this.lastPublishedAt = in.readLong();
        this.sections = in.createTypedArrayList(Section.CREATOR);
        this.ownerName = in.readString();
        this.access = in.readString();
        this.pushNotification = in.readString();
        this.comments = in.readString();
        this.isPublished = in.readString();
        this.publisherId = in.readString();
        this.publishedAt = in.readLong();
        this.storyLineId = in.readString();
        this.storylineTitle = in.readString();
        this.summary = in.readString();
        this.status = in.readString();
        this.heroImageS3Key = in.readString();
        this.cards = in.createTypedArrayList(Card.CREATOR);
        this.storyVersionId = in.readString();
        this.authorName = in.readString();
        this.authorId = in.readString();
        this.ownerId = in.readString();
        this.firstPublishedAt = in.readLong();
        this.heroImageCaption = in.readString();
        this.heroImageAttribution = in.readString();
        this.version = in.readString();
        this.bulletType = in.readString();
        this.createdAt = in.readLong();
        this.assigneeId = in.readString();
        this.assigneeName = in.readString();
        this.heroImageMeta = in.readParcelable(ImageMetaData.class.getClassLoader());
        this.template = in.readString();
        this.storyMetaData = in.readParcelable(StoryMetaData.class.getClassLoader());
        this.authors = in.createTypedArrayList(Author.CREATOR);
        this.alternative = in.readParcelable(Alternative.class.getClassLoader());
        if (in.readByte() != 0) {
            this.linkedEntities = (JsonArray) new JsonParser().parse(in.readString());
        }
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public Alternative alternative() {
        return alternative;
    }


}

