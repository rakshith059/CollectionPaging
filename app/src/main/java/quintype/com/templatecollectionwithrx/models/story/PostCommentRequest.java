//package quintype.com.templatecollectionwithrx.models.story;
//
//import com.google.gson.annotations.SerializedName;
//import com.quintype.commons.StringUtils;
//import com.quintype.core.data.Request;
//
//import okhttp3.MediaType;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import rx.Observable;
//
///**
// * An object representing post comment request
// *
// * @author Imran imran@quintype.com
// * @author Madhu madhu@quintype.com
// */
//public final class PostCommentRequest extends Request<Response> {
//
//    QuintypeStoryApi quintypeStoryApi;
//
//    @SerializedName("story-content-id")
//    private String storyContentId;
//    @SerializedName("card-content-id")
//    private String cardContentId;
//    @SerializedName("text")
//    private String commentText;
//
//    public PostCommentRequest cardContentId(String cardContentId) {
//        this.cardContentId = cardContentId;
//        return this;
//    }
//
//    public PostCommentRequest commentText(String commentText) {
//        this.commentText = commentText;
//        return this;
//    }
//
//    @Override
//    public String toString() {
//        return "PostCommentRequest{" +
//                "storyContentId='" + storyContentId + '\'' +
//                ", cardContentId='" + cardContentId + '\'' +
//                ", commentText='" + commentText + '\'' +
//                '}';
//    }
//
//    PostCommentRequest(String storyContentId, QuintypeStoryApi quintypeStoryApi) {
//        this.storyContentId = storyContentId;
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    @Override
//    protected boolean validate() {
//        if (StringUtils.isEmpty(storyContentId)) {
//            throw new IllegalArgumentException("StoryContentId cannot be null");
//        }
//        if (StringUtils.isEmpty(cardContentId)) {
//            throw new IllegalArgumentException("cardContentId cannot be null");
//        }
//        if (StringUtils.isEmpty(commentText)) {
//            throw new IllegalArgumentException("Comment text cannot be null");
//        }
//        return true;
//    }
//
//    @Override
//    public Observable<Response> getObservable() {
//        return quintypeStoryApi.postCommentRx(this);
//    }
//
//    @Override
//    protected Call getRetrofitCall() {
//        return quintypeStoryApi.postComment(this);
//    }
//
//    @Override
//    protected Callback getRetrofitCallback(com.quintype.core.data.Callback<Response> callback) {
//        return getGenericRetrofitCallback(callback);
//    }
//
//    @Override
//    protected Response getEmptyResponse(int code) {
//        return Response.error(501, ResponseBody.create(MediaType.parse("plain/text"), "No response"));
//    }
//
//}
