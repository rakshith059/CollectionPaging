
package quintype.com.templatecollectionwithrx.models.story;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookUserEngagement {

    @SerializedName("engagement")
    @Expose
    private Integer engagement;
    @SerializedName("shares")
    @Expose
    private Integer shares;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("reactions")
    @Expose
    private Integer reactions;

    public Integer getEngagement() {
        return engagement;
    }

    public void setEngagement(Integer engagement) {
        this.engagement = engagement;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getReactions() {
        return reactions;
    }

    public void setReactions(Integer reactions) {
        this.reactions = reactions;
    }

}
