package quintype.com.templatecollectionwithrx.models.storypresenter;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import quintype.com.templatecollectionwithrx.models.entities.EntityModel;
import quintype.com.templatecollectionwithrx.models.story.Card;
import quintype.com.templatecollectionwithrx.models.story.EntityMapperItem;
import quintype.com.templatecollectionwithrx.models.story.SortCards;
import quintype.com.templatecollectionwithrx.models.story.Story;
import quintype.com.templatecollectionwithrx.models.story.StoryElement;

/**
 * An object representing StoryPresenter
 * <p></p>
 * Use the presenter to load an adapter or bind views.
 * Embed views.
 */
public class StoryPresenter implements Parcelable {

    /**
     * Callback to register elements types with {@link StoryElementBinder}
     */
    public interface BinderCallback {
        Class<? extends StoryElementBinder> onRegisterViewTypeClass(@ElementViewType int viewType);

        StoryElementBinder onCreateElementBinder(ViewGroup parent, Class<? extends
                StoryElementBinder> cls);
    }


    Story story;
    List<StoryElement> flattenedStoryElements = new ArrayList<>();
    int[] cardPositions;
    Map<Integer, List<EntityModel>> cardEntityMap = new HashMap<>();
    Map<String, Card> storyElementToCardMap = new HashMap<>();

    //base view type to increment custom view types
    static final int INCREMENTAL_BASE_VIEW_TYPE = 1000;

    /**
     * // Known view types while recreating
     * viewType = 1 // actual view type
     * (1 * 100) + 1 = 101 // view type given to recycler view
     * 101 % 100 = 1
     * <p>
     * // custom view types
     * 30 + 1 = 31
     */

    List<Integer> recreateHolderList = new ArrayList<>();

    BinderCallback callback;
    LinkedHashMap<Integer, Class> embedElementsMap = new
            LinkedHashMap<>();

    /**
     * @return an array of the positions of the first story element in each card
     */
    public int[] cardPositions() {
        return cardPositions;
    }

    /**
     * @return a list of card entities with Integer keys that correspond to the number of story
     * elements in the card above
     */
    public Map<Integer, List<EntityModel>> getCardEntityMap() {
        return cardEntityMap;
    }

    public Map<String, Card> getStoryElementToCardMap() {
        return storyElementToCardMap;
    }

    StoryPresenter(Story story) {
        this.story = story;
        if (story != null) {
            Map<String, EntityModel> parsedEntityMap = story.parsedEntityList();
            int[] positionArray = new int[story.cards().size()];
            int counter = 0;
            //Add story elements to elements list.
            for (Card card : story.cards()) {
                card.buildUIStoryElements();
                //keep a track of where each card starts
                positionArray[counter] = flattenedStoryElements.size();
                //flatten the elements in this card
                for (StoryElement elem : card.getUiStoryElements()) {
                    flattenedStoryElements.add(elem);
                    storyElementToCardMap.put(elem.id(), card);
                }

                //parse the card attributes and put the list of card entities with its corresponding
                //card position into a map
//            if (parsedEntityMap != null && !parsedEntityMap.isEmpty()
//                    && card.getMetadata() != null && card.getMetadata().attributes() != null) {
//                //parsing the attributes json into a map
//                Gson gson = new Gson();
//                JsonObject attributes = card.getMetadata().attributes();
//                Type type = new TypeToken<Map<String, List<EntityMapperItem>>>() {
//                }.getType();
//                Map<String, List<EntityMapperItem>> attributeMap = gson.fromJson(attributes, type);
//
//                //parsing the map to create a list of entities this card has
//                List<EntityModel> cardEntityList = new ArrayList<>();
//                for (List<EntityMapperItem> itemList : attributeMap.values()) {
//                    for (EntityMapperItem entityMapperItem : itemList) {
//                        cardEntityList.add(parsedEntityMap.get(entityMapperItem.getId()));
//                    }
//                }
//
//                // finally, adding the list of entities in this card to a map corresponding where
//                // the first story element in this card is in the flattenedStoryElements
//                cardEntityMap.put(counter, cardEntityList);
//            }
                counter++;
            }
            cardPositions = positionArray;
        }
    }

    /**
     * This constructor is created especially for TheQuint Publisher - LiveBlog Template
     * Conditions for LiveBlog :
     * 1. Sort all the cards based on cardAddedAt value.
     * 2. Look for summary element and move it to the top of the list.
     * 3. HardCode ElementViewType SORT_CARDS below summary element if summary element exists, else SORT_CARDS will be on the top of the list
     *
     * @param story
     * @param newestFirst {By Default the order should be Newest first}
     */
    StoryPresenter(Story story, boolean newestFirst) {
        this.story = story;
        Map<String, EntityModel> parsedEntityMap = story.parsedEntityList();
        int[] positionArray = new int[story.cards().size()];
        int counter = 0;

        //Hardcoding ElementViewType SORT_CARDS to the top
        flattenedStoryElements.add(0, StoryElement.newStoryElement(StoryElement.TYPE_SORT_CARDS));

        List<Card> cardList = story.cards();
        if (!newestFirst)
            Collections.sort(cardList, new SortCards());// Collections sort will always give list in ascending order which is oldest first.
        else
            Collections.sort(cardList, Collections.<Card>reverseOrder(new SortCards()));

        for (Card card : story.cards()) {
            card.buildUIStoryElements();
            //keep a track of where each card starts
            positionArray[counter] = flattenedStoryElements.size();

            /*If the card is pinned move the whole card to the top. The pinned card expected to have summary element and displayed about ElementViewType SORT_CARDS*/
            if (card.getMetadata().attributes() != null) {
                JsonObject attributeJsonObj = card.getMetadata().attributes();
                if (attributeJsonObj.has("is-pinned?")) {
                    boolean isCardPinned = attributeJsonObj.get("is-pinned?").getAsBoolean();
                    if (isCardPinned) {
                        //Timber.d("This card is pinned(positive), add(hardcode) all the story elements to the top of the story(flattenedStoryElements).");
                        List<StoryElement> pinnedCardStoryElements = card.getUiStoryElements();
                        /*Lopping it in a reverse order, because we are hardcoding the insert position as '0' always*/
                        for (int i = pinnedCardStoryElements.size(); i > 0; i--) {
                            StoryElement storyElement = pinnedCardStoryElements.get(i - 1);
                            storyElement.setCardPinStatus(true);
                            flattenedStoryElements.add(0, storyElement);
                        }
                    } else {
                        //Timber.d("This card is not pinned(negative), add story elements in the respective positions.");
                        addLiveBlogStoryElements(card, attributeJsonObj);
                    }
                } else {
                    //Timber.d("There is no card metadata'is-pinned',This card is not pinned, add story elements in the respective positions.");
                    addLiveBlogStoryElements(card, attributeJsonObj);
                }
            } else {
                //Timber.d("Card metadata is null,add story elements in the respective positions.");
                addLiveBlogStoryElements(card, null);
            }

            //parse the card attributes and put the list of card entities with its corresponding
            //card position into a map
            if (parsedEntityMap != null && !parsedEntityMap.isEmpty()
                    && card.getMetadata() != null && card.getMetadata().attributes() != null) {
                //parsing the attributes json into a map
                Gson gson = new Gson();
                JsonObject attributes = card.getMetadata().attributes();
                Type type = new TypeToken<Map<String, List<EntityMapperItem>>>() {
                }.getType();
                Map<String, List<EntityMapperItem>> attributeMap = gson.fromJson(attributes, type);

                //parsing the map to create a list of entities this card has
                List<EntityModel> cardEntityList = new ArrayList<>();
                for (List<EntityMapperItem> itemList : attributeMap.values()) {
                    for (EntityMapperItem entityMapperItem : itemList) {
                        cardEntityList.add(parsedEntityMap.get(entityMapperItem.getId()));
                    }
                }

                // finally, adding the list of entities in this card to a map corresponding where
                // the first story element in this card is in the flattenedStoryElements
                cardEntityMap.put(counter, cardEntityList);
            }
            counter++;

        }
        cardPositions = positionArray;
    }

    private void addLiveBlogStoryElements(Card card, JsonObject attributeJsonObj) {

        // Creating the new StoryElement of type TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP.
        StoryElement timeStampStoryElement = StoryElement.newStoryElement(StoryElement.TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP);
        timeStampStoryElement.setCardAddedAt(card.getCardAddedAt());

        if (attributeJsonObj != null && attributeJsonObj.has("key-event")) {
            timeStampStoryElement.setKeyEvent(attributeJsonObj.get("key-event").getAsBoolean());
        }

        int storyElementIndex = 0;
        List<StoryElement> storyElements = card.getUiStoryElements();
        for (StoryElement elem : storyElements) {
            // If the Story template type is LIVE BLOG, and if the StoryElement is in the 1st position of the card then Add(Hardcode) TimeStamp element
            // before adding the normal StoryElement
            if (!elem.isTypeSummary() && storyElementIndex == 0) {
                flattenedStoryElements.add(timeStampStoryElement);
            }
            flattenedStoryElements.add(elem);
            storyElementIndex++;
        }
    }

    /**
     * Create StoryPresenter
     * <p>
     * Setup StoryElements of the Story
     *
     * @param story {@link Story}
     * @return {@link StoryPresenter}
     */
    public static StoryPresenter create(Story story) {
        return new StoryPresenter(story);
    }

    /**
     * Create StoryPresenter
     * <p>
     * Setup StoryElements of the Story
     *
     * @param story       {@link Story}
     * @param newestFirst {Story cards sorting order, can be either OldestFirst 'FALSE' or NewestFirst'TRUE' }
     * @return {@link StoryPresenter}
     */
    public static StoryPresenter create(Story story, boolean newestFirst) {
        return new StoryPresenter(story, newestFirst);
    }

    /**
     * Set {@link BinderCallback} for unknown StoryElement types
     *
     * @param callback {@link BinderCallback}
     */
    public StoryPresenter binderCallback(BinderCallback callback) {
        this.callback = callback;
        return this;
    }

    /**
     * Insert a custom element into a Story.
     * <p>
     * for instance an Ad.
     * <p>
     * Class type is used in {@link BinderCallback} to get the Binder for the position.
     *
     * @param element    {@link StoryElement} to be inserted
     * @param atPosition position to insert the story element.
     * @param cls        {@link StoryElementBinder} class type of the Binder.
     */
    public void insertElement(StoryElement element, int atPosition, Class cls) {
        if (element != null && cls != null) {
            if (atPosition >= flattenedStoryElements.size()) {
                //add element at last if the position is greater than the size itself.
                atPosition = flattenedStoryElements.size();
                flattenedStoryElements.add(element);
                embedElementsMap.put(atPosition, cls);
            } else {
                //insert the element at a position
                flattenedStoryElements.add(atPosition, element);

                //increment all the keys after the position the element was added.
                LinkedHashMap<Integer, Class> tempMap = new
                        LinkedHashMap<>();
                tempMap.put(atPosition, cls);

                for (int key : embedElementsMap.keySet()) {
                    Class c = embedElementsMap.get(key);
                    if (key > atPosition) {
                        tempMap.put(key + 1, c);
                    } else {
                        tempMap.put(key, c);
                    }
                }
                embedElementsMap = tempMap;
            }

        } else {
            throw new IllegalArgumentException("StoryElement or StoryElementBinder type is null");
        }
    }

    /**
     * Insert custom StoryElement Binder with a dummy StoryElement
     *
     * @param atPosition position to insert the story element.
     * @param cls        {@link StoryElementBinder} class type of the Binder.
     */
    public void insertElementBinder(int atPosition, Class cls) {
        insertElement(StoryElement.dummyElement(), atPosition, cls);
    }

    /**
     * Get story elements count
     *
     * @return int total number of elements including custom inserted elements
     */
    public int getElementCount() {
        if (flattenedStoryElements == null) {
            return 0;
        }
        return flattenedStoryElements.size();
    }

    /**
     * Get element ViewType {@link ElementViewType}
     *
     * @param position int position of a StoryElement in the story
     * @return A custom viewType as int or {@link ElementViewType}
     */
    public int getElementViewType(int position) {
        int viewType;
        if (position >= flattenedStoryElements.size() || position < 0) {
            //No Elements
            viewType = ElementViewType.UNKNOWN;
        } else if (embedElementsMap.containsKey(position)) {
            //Contains custom elements
            viewType = ElementViewType.UNKNOWN + position + 1;
        } else {
            //Known element types.
            StoryElement element = flattenedStoryElements.get(position);
            if (element.isTypeImage()) {
                viewType = ElementViewType.IMAGE;
            } else if (element.isTypeYoutube()) {
                viewType = ElementViewType.YOUTUBE;
            } else if (element.isTypeBitGravity()) {
                viewType = ElementViewType.BIT_GRAVITY_VIDEO;
            } else if (element.isTypeBrightCoveVideo()) {
                viewType = ElementViewType.BRIGHTCOVE_VIDEO;
            } else if (element.isTypeSoundcloud()) {
                viewType = ElementViewType.SOUND_CLOUD;
            } else if (element.isTypeAudio()) {
                viewType = ElementViewType.AUDIO;
            } else if (element.isImageGallery()) {
                viewType = ElementViewType.GALLERY;
            } else if (element.isTypeBigFact()) {
                viewType = ElementViewType.BIG_FACT;
            } else if (element.isTypeBlurb()) {
                viewType = ElementViewType.BLURB;
            } else if (element.isTypeBlockQuote()) {
                viewType = ElementViewType.BLOCK_QUOTE;
            } else if (element.isTypeQuestionAnswer()) {
                viewType = ElementViewType.QUESTION_ANSWER;
            } else if (element.isTypeVideo()) {
                viewType = ElementViewType.VIDEO;
            } else if (element.isTypeQuote()) {
                viewType = ElementViewType.QUOTE;
            } else if (element.isImageSlideshow()) {
                viewType = ElementViewType.SLIDESHOW;
            } else if (element.isTypeImageGif()) {
                viewType = ElementViewType.GIF;
            } else if (element.isTypePollType()) {
                viewType = ElementViewType.POLLTYPE;
            } else if (element.isTypeJsEmbedWithTwitter()) {
                viewType = ElementViewType.TWEET;
            } else if (element.isTypeJsembed()) {
                viewType = ElementViewType.JS_EMBED;
            } else if (element.isTypeMedia()) {
                viewType = ElementViewType.MEDIA;
            } else if (element.isTypeSummary()) {
                viewType = ElementViewType.SUMMARY;
            } else if (element.isTypeTitle()) {
                viewType = ElementViewType.TITLE;
            } else if (element.isAlsoRead()) {
                viewType = ElementViewType.ALSO_READ;
            } else if (element.isTypeQuestion()) {
                viewType = ElementViewType.QUESTION;
            } else if (element.isTypeAnswer()) {
                viewType = ElementViewType.ANSWER;
            } else if (element.isTypeText()) {
                viewType = ElementViewType.TEXT;
            } else if (element.isTypeTable()) {
                viewType = ElementViewType.TABLE;
            } else if (element.isTypeTimeStamp()) {
                viewType = ElementViewType.TIME_STAMP;
            } else if (element.isTypeSortCards()) {
                viewType = ElementViewType.SORT_CARDS;
            } else if (element.isTypeNestedStory()) {
                viewType = ElementViewType.NESTED_STORY;
            } else {
                viewType = ElementViewType.UNKNOWN;
            }
        }

        if (recreateHolderList.contains(viewType)) {
            //If recreate view holders
            return (INCREMENTAL_BASE_VIEW_TYPE * viewType) + position;
        } else {
            return viewType;
        }

    }

    /**
     * Get Story element at position from the list of story elements in a story
     * see {@link #create(Story)}
     *
     * @param position int position for Story Element
     * @return {@link StoryElement}
     */
    public StoryElement getElement(int position) {
        return flattenedStoryElements.get(position);
    }

    private
    @ElementViewType
    int getInternalElementType(int type) {
        return ElementViewType.Process.getType(type);
    }

    /**
     * Get
     * {@link StoryElementBinder} for viewtype. It can be custom viewtype or {@link ElementViewType}
     *
     * @param parent   {@link ViewGroup}
     * @param viewType A custom viewtype or {@link ElementViewType}
     * @return {@link StoryElementBinder}
     */
    public StoryElementBinder getElementBinderForViewType(ViewGroup parent, int viewType) {
        if (callback != null) {
            int type = viewType;
            StoryElementBinder binder;
            if (viewType > ElementViewType.UNKNOWN && viewType < INCREMENTAL_BASE_VIEW_TYPE) {
                //get binder for custom element inserted
                int position = viewType - ElementViewType.UNKNOWN - 1;
                binder = callback.onCreateElementBinder(parent, embedElementsMap.get(position));
            } else if (viewType > INCREMENTAL_BASE_VIEW_TYPE) {
                type = viewType / INCREMENTAL_BASE_VIEW_TYPE;//(viewType - position) /
                // INCREMENTAL_BASE_VIEW_TYPE;
                binder = getElementBinderForViewType(parent, type);
            } else {
                //get binder for known view types.
                binder = callback.onCreateElementBinder(parent, callback.onRegisterViewTypeClass
                        (getInternalElementType(type)));
            }
            if (binder.recreate()) {
                recreateHolderList.add(type);
            }
            return binder;
        } else {
            throw new RuntimeException("Callback is not set");
        }
    }

    /**
     * Bind StoryElementBinder with the view.
     *
     * @param binder   {@link StoryElementBinder}
     * @param position int position on RecyclerView
     */
    public void bind(StoryElementBinder binder, int position) {
        binder.bind(flattenedStoryElements.get(position));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.story, 0);
        dest.writeTypedList(flattenedStoryElements);
    }

    protected StoryPresenter(Parcel in) {
        this.story = in.readParcelable(Story.class.getClassLoader());
        this.flattenedStoryElements = in.createTypedArrayList(StoryElement.CREATOR);
    }

    public static final Creator<StoryPresenter> CREATOR = new Creator<StoryPresenter>() {
        public StoryPresenter createFromParcel(Parcel source) {
            return new StoryPresenter(source);
        }

        public StoryPresenter[] newArray(int size) {
            return new StoryPresenter[size];
        }
    };

    public Story story() {
        return story;
    }

    public void updateVideoStoryElementList() {
        if (story != null && story.template().equalsIgnoreCase("VIDEO") && flattenedStoryElements
                != null && !flattenedStoryElements.isEmpty()) {
            for (StoryElement storyElement : flattenedStoryElements) {
                List<StoryElement> modifiedStoryElement = new ArrayList<>(flattenedStoryElements);

                if (storyElement.isTypeVideo() || storyElement.isTypeYoutube() || storyElement.isTypeBrightCoveVideo()) {

                    modifiedStoryElement.remove(flattenedStoryElements.lastIndexOf(storyElement));
                    modifiedStoryElement.add(0, storyElement);
                }
                flattenedStoryElements = modifiedStoryElement;
            }
        }
    }

    public List<StoryElement> FlattenedStoryElementList() {
        return flattenedStoryElements;
    }
}
