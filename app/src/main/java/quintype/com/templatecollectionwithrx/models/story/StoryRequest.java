//package quintype.com.templatecollectionwithrx.models.story;
//
//import android.support.annotation.NonNull;
//
//import com.quintype.commons.ArrayUtils;
//import com.quintype.commons.StringUtils;
//import com.quintype.core.cache.RelatedStoriesDiskLruCache;
//import com.quintype.core.cache.StoryDiskLruCache;
//
///**
// * An object representing Story Request.
// * Any request related to {@link Story} from the cache or network will be done using a StoryRequest.
// *
// * @author Imran imran@quintype.com
// * @author Madhu madhu@quintype.com
// */
//public class StoryRequest {
//
//    private final StoryDiskLruCache storyDiskLruCache;
//    private final RelatedStoriesDiskLruCache relatedStoriesDiskLruCache;
//    QuintypeStoryApi quintypeStoryApi;
//
//    public StoryRequest(QuintypeStoryApi quintypeStoryApi,
//                        StoryDiskLruCache storyDiskLruCache,
//                        RelatedStoriesDiskLruCache relatedStoriesDiskLruCache) {
//        this.quintypeStoryApi = quintypeStoryApi;
//        this.storyDiskLruCache = storyDiskLruCache;
//        this.relatedStoriesDiskLruCache = relatedStoriesDiskLruCache;
//    }
//
//    /**
//     * Get List of stories.
//     *
//     * @return {@link StoryQueryList}
//     */
//    public StoryQueryList getStories() {
//        return new StoryQueryList(quintypeStoryApi);
//    }
//
//    /**
//     * Get story by searching for a term
//     *
//     * @return {@link StoryQuerySearch}
//     */
//    public StoryQuerySearch getStoriesBySearch() {
//        return new StoryQuerySearch(quintypeStoryApi);
//    }
//
//    /**
//     * Get story by searching for a term and story attribute
//     *
//     * @return {@link StoryQueryAttributeSearch}
//     */
//    public StoryQueryAttributeSearch getStoriesByAttributeSearch() {
//        return new StoryQueryAttributeSearch(quintypeStoryApi);
//    }
//
//    /**
//     * Get story by searching for an Authors Stories
//     *
//     * @return {@link StoryAuthorQuerySearch}
//     */
//    public StoryAuthorQuerySearch getAuthorStoriesBySearch() {
//        return new StoryAuthorQuerySearch(quintypeStoryApi);
//    }
//
//    /**
//     * Get story by id.
//     *
//     * @return {@link StoryQueryId}
//     */
//    public StoryQueryId getStoryById() {
//        return new StoryQueryId(quintypeStoryApi, storyDiskLruCache);
//    }
//
//    /**
//     * Get story by template.
//     *
//     * @return {@link StoryQueryTemplate}
//     */
//    public StoryQueryTemplate getStoryByTemplate() {
//        return new StoryQueryTemplate(quintypeStoryApi);
//    }
//
//    /**
//     * Get story by slug.
//     *
//     * @return {@link StoryQuerySlug}
//     */
//    public StoryQuerySlug getStoryBySlug() {
//        return new StoryQuerySlug(quintypeStoryApi);
//    }
//
//    /**
//     * Get related stories
//     *
//     * @return {@link StoryQueryRelated}
//     */
//    public StoryQueryRelated getStoryRelated() {
//        return new StoryQueryRelated(quintypeStoryApi, relatedStoriesDiskLruCache);
//    }
//
//    /**
//     * Get user engagement on a story
//     * Comments and votes
//     *
//     * @param storyContentId {@link Story#contentId()}
//     * @return {@link UserEngagementQuery}
//     */
//    public UserEngagementQuery userEngagement(@NonNull String storyContentId) {
//        if (StringUtils.isEmpty(storyContentId)) {
//            throw new IllegalArgumentException("Story Id cannot be null");
//        }
//        return new UserEngagementQuery(storyContentId, quintypeStoryApi);
//    }
//
//    /**
//     * Post a comment on story
//     *
//     * @param storyContentId {@link Story#contentId()}
//     * @return {@link PostCommentRequest}
//     */
//    public PostCommentRequest postCommentRequest(@NonNull String storyContentId) {
//        return new PostCommentRequest(storyContentId, quintypeStoryApi);
//    }
//
//    /**
//     * Get fields separated by ','. if fields are empty. default fields are sent.
//     *
//     * @param fields Array of string fields
//     * @return String separated fields by ','
//     */
//    static String getFields(String... fields) {
//        String fls;
//        if (ArrayUtils.isNotEmpty(fields)) {
//            fls = StringUtils.join(fields, ",");
//        } else {
//            fls = StringUtils.join(Story.DEFAULT_FIELDS, ",");
//        }
//        return fls;
//    }
//
//    /**
//     * Get user engagement on a story
//     * Facebook and shrubbery
//     *
//     * @param storyContentId {@link Story#contentId()}
//     * @return {@link LiveUserEngagementQuery}
//     */
//    public LiveUserEngagementQuery liveUserEngagementQuery(@NonNull String storyContentId) {
//        if (StringUtils.isEmpty(storyContentId)) {
//            throw new IllegalArgumentException("Story Id cannot be null");
//        }
//        return new LiveUserEngagementQuery(storyContentId, quintypeStoryApi);
//    }
//}
