
package quintype.com.templatecollectionwithrx.models.story;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShrubberyUserEngagement {

    @SerializedName("views")
    @Expose
    private Integer views;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

}
