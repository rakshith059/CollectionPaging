//package quintype.com.templatecollectionwithrx.models.story;
//
//
//import java.util.List;
//import java.util.Map;
//
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.http.Body;
//import retrofit2.http.GET;
//import retrofit2.http.Headers;
//import retrofit2.http.POST;
//import retrofit2.http.Path;
//import retrofit2.http.Query;
//import retrofit2.http.QueryMap;
//import rx.Observable;
//
///**
// * An object representing QuintypeStoryApi
// */
//public interface QuintypeStoryApi {
//
//    String QUERY_PARAM_KEY_STORY_GROUP = "story-group";
//    String QUERY_PARAM_KEY_LIMIT = "limit";
//    String QUERY_PARAM_KEY_OFFSET = "offset";
//    String QUERY_PARAM_SECTION = "section";
//    String QUERY_PARAM_SECTION_ID = "section-id";
//    String QUERY_PARAM_FIELDS = "fields";
//    String QUERY_PARAM_SORT = "sort";
//    String QUERY_PARAM_AUTHOR_ID = "author-id";
//    String QUERY_PARAM_STORY_ORDER = "story-order";
//    String QUERY_PARAM_TAG = "tag";
//    String QUERY_PARAM_SLUG = "slug";
//    String QUERY_PARAM_TEMPLATE = "template";
//    String QUERY_PARAM_STORY_ID = "story-id";
//    String SEARCH_QUERY_TERM_PARAM = "q";
//    String SEARCH_QUERY_TERM_ATTRIBUTE = "story-attributes";
//    String SEARCH_QUERY_FROM_PARAM = "from";
//    String SEARCH_QUERY_SIZE_PARAM = "size";
//
//
//    String ACCEPT_APPLICATION_JSON_CHARSET_UTF_8 = "Accept: application/json; charset=utf-8";
//    String CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " +
//            "charset=utf-8";
////    String APPLICATION_ACCEPT_ENCODING_GZIP = "Accept-Encoding: gzip, deflate";
//
//    /**
//     * Get list of stories as Rx Observable
//     *
//     * @param offset      offset from which stories should be fetched, can be null
//     * @param limit       limit the response, can be null
//     * @param sectionName section name for which stories need to be queried
//     * @param fields      the fields of the story that need to be queried
//     * @param tag         tag if any for listing
//     * @param sortType    type of sort, cannot be null
//     * @return an Observable for stories
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories")
//    Call<List<Story>> getStories(@Query(QUERY_PARAM_KEY_OFFSET) String offset,
//                                 @Query(QUERY_PARAM_KEY_LIMIT) String limit,
//                                 @Query(QUERY_PARAM_SECTION) String sectionName,
//                                 @Query(QUERY_PARAM_SECTION_ID) String sectionId,
//                                 @Query(QUERY_PARAM_FIELDS) String fields,
//                                 @Query(QUERY_PARAM_TAG) String tag,
//                                 @Query(QUERY_PARAM_KEY_STORY_GROUP) String sortType);
//
//    /**
//     * Get list of stories as Rx Observable
//     *
//     * @param offset      offset from which stories should be fetched, can be null
//     * @param limit       limit the response, can be null
//     * @param sectionName section name for which stories need to be queried
//     * @param fields      the fields of the story that need to be queried
//     * @param tag         tag if any for listing
//     * @param sortType    type of sort, cannot be null
//     * @return an Observable for stories
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories")
//    Observable<List<Story>> getStoriesRx(@Query(QUERY_PARAM_KEY_OFFSET) String offset,
//                                         @Query(QUERY_PARAM_KEY_LIMIT) String limit,
//                                         @Query(QUERY_PARAM_SECTION) String sectionName,
//                                         @Query(QUERY_PARAM_SECTION_ID) String sectionId,
//                                         @Query(QUERY_PARAM_FIELDS) String fields,
//                                         @Query(QUERY_PARAM_TAG) String tag,
//                                         @Query(QUERY_PARAM_KEY_STORY_GROUP) String sortType);
//
//
//    /**
//     * Get a story as Rx Observable
//     *
//     * @param id     the id of the story
//     * @param fields the fields of the story that need to be queried
//     * @return an Observable for story
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories/{story-id}")
//    Call<IdStory> getStoryFromId(@Path("story-id") String id,
//                                 @Query(QUERY_PARAM_FIELDS) String fields);
//
//
//    /**
//     * Get a story as Rx Observable
//     *
//     * @param id     the id of the story
//     * @param fields the fields of the story that need to be queried
//     * @return an Observable for story
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories/{story-id}")
//    Observable<IdStory> getStoryFromIdRx(@Path("story-id") String id,
//                                         @Query(QUERY_PARAM_FIELDS) String fields);
//
//
//    /**
//     * Get a story as Rx Observable
//     *
//     * @param template the template of the story
//     * @param limit    the limit on the number of stores
//     * @param fields   the fields of the story that need to be queried
//     * @return an Observable for story
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories")
//    Call<List<Story>> getStoriesByTemplate(@Query(QUERY_PARAM_TEMPLATE) String template,
//                                           @Query(QUERY_PARAM_KEY_LIMIT) int limit,
//                                           @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * Get a story as Rx Observable
//     *
//     * @param template the template of the story
//     * @param limit    the limit on the number of stores
//     * @param fields   the fields of the story that need to be queried
//     * @return an Observable for story
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories")
//    Observable<List<Story>> getStoriesByTemplateRx(@Query(QUERY_PARAM_TEMPLATE) String template,
//                                                   @Query(QUERY_PARAM_KEY_LIMIT) int limit,
//                                                   @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * Get a story as Rx Observable
//     *
//     * @param slug the slug of the story
//     * @return an Observable for story
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories-by-slug")
//    Observable<SlugStory> getStoriesFromSlugRx(@Query(QUERY_PARAM_SLUG) String slug,
//                                               @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * Get a story as Rx Observable
//     *
//     * @param slug the slug of the story
//     * @return an Observable for story
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories-by-slug")
//    Call<SlugStory> getStoriesFromSlug(@Query(QUERY_PARAM_SLUG) String slug,
//                                       @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * @param storyId story id
//     * @param fields  fields needed in response  @return an observable for related stories
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/related-stories")
//    Observable<RelatedStories> getRelatedStoriesRx(@Query(QUERY_PARAM_STORY_ID) String storyId,
//                                                   @Query
//                                                           (QUERY_PARAM_SECTION) String section, @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * @param storyId story id
//     * @param limit
//     * @param fields  fields needed in response  @return an observable for related stories
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/related-stories")
//    Call<RelatedStories> getRelatedStories(@Query(QUERY_PARAM_STORY_ID) String storyId, @Query
//            (QUERY_PARAM_SECTION) String section, int limit, @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * a get method to get the list of user engagements to a particular story content id
//     *
//     * @param storyContentID - the story content id of the story for which comments are being
//     *                       queried
//     * @return and observable of list of UserEngagements
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/comments-and-votes/{story-content-id}")
//    Observable<List<UserEngagement>> getUserEngagementRx(@Path("story-content-id") String
//                                                                 storyContentID);
//
//    /**
//     * a get method to get the list of user engagements to a particular story content id
//     *
//     * @param storyContentID - the story content id of the story for which comments are being
//     *                       queried
//     * @return and observable of list of UserEngagements
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/comments-and-votes/{story-content-id}")
//    Call<List<UserEngagement>> getUserEngagement(@Path("story-content-id") String storyContentID);
//
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/search")
//    Observable<StorySearchResult.Results> getSearchResultRx(@Query(SEARCH_QUERY_TERM_PARAM)
//                                                                    String searchTerm,
//                                                            @Query(SEARCH_QUERY_FROM_PARAM) int
//                                                                    from,
//                                                            @Query(SEARCH_QUERY_SIZE_PARAM) int
//                                                                    size,
//                                                            @Query(QUERY_PARAM_FIELDS) String
//                                                                    fields);
//
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/search")
//    Call<StorySearchResult.Results> getSearchResult(@Query(SEARCH_QUERY_TERM_PARAM) String
//                                                            searchTerm,
//                                                    @Query(SEARCH_QUERY_FROM_PARAM) int from,
//                                                    @Query(SEARCH_QUERY_SIZE_PARAM) int size,
//                                                    @Query(QUERY_PARAM_FIELDS) String fields);
//
//    /**
//     * Api call to search the stories with attribute and search term
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories")
//    Observable<List<Story>> getSearchAttributeResultRx(@QueryMap Map<String, String> map);
//
//    /**
//     * Api call to search the stories with attribute and search term
//     * map = passing the required values as key-value pair
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories")
//    Call<List<Story>> getSearchAttributeResult(@QueryMap Map<String, String> map);
//
//    @GET("api/search")
//    Observable<StorySearchResult.Results> getSearchAuthorResultRx(@Query(QUERY_PARAM_AUTHOR_ID)
//                                                                          String authorId,
//                                                                  @Query(SEARCH_QUERY_FROM_PARAM)
//                                                                          int from,
//                                                                  @Query(SEARCH_QUERY_SIZE_PARAM)
//                                                                          int size,
//                                                                  @Query(QUERY_PARAM_FIELDS)
//                                                                          String fields,
//                                                                  @Query(QUERY_PARAM_SORT) String
//                                                                          sort);
//
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/search")
//    Call<StorySearchResult.Results> getSearchAuthorResult(@Query(QUERY_PARAM_AUTHOR_ID) String
//                                                                  authorId,
//                                                          @Query(SEARCH_QUERY_FROM_PARAM) int from,
//                                                          @Query(SEARCH_QUERY_SIZE_PARAM) int size,
//                                                          @Query(QUERY_PARAM_FIELDS) String fields,
//                                                          @Query(QUERY_PARAM_SORT) String sort);
//
//    /**
//     * a post method to post a comment made by the user to the server
//     *
//     * @param postCommentRequest - the post comment request that encapsulates the data to be sent
//     *                           to the server
//     */
//    @Headers({
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @POST("api/comment")
//    Call<Response> postComment(@Body PostCommentRequest postCommentRequest);
//
//    /**
//     * a post method to post a comment made by the user to the server
//     *
//     * @param postCommentRequest - the post comment request that encapsulates the data to be sent
//     *                           to the server
//     */
//    @Headers({
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @POST("api/comment")
//    Observable<Response> postCommentRx(@Body PostCommentRequest postCommentRequest);
//
//    /**
//     * @param id the id of the story
//     * @return Facebook and Shrubbery user engagement results
//     */
//    @Headers({
//            ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
//            CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8,
////            APPLICATION_ACCEPT_ENCODING_GZIP
//    })
//    @GET("api/stories/{story-id}/engagement")
//    Call<LiveUserEngagement> getLiveUserEngagement(@Path("story-id") String id);
//
//}
