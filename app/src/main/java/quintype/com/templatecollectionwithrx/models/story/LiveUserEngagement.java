
package quintype.com.templatecollectionwithrx.models.story;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveUserEngagement {

    @SerializedName("facebook")
    @Expose
    private FacebookUserEngagement facebookUserEngagement;
    @SerializedName("shrubbery")
    @Expose
    private ShrubberyUserEngagement shrubberyUserEngagement;

    public FacebookUserEngagement getFacebook() {
        return facebookUserEngagement;
    }

    public void setFacebookUserEngagement(FacebookUserEngagement facebook) {
        this.facebookUserEngagement = facebook;
    }

    public ShrubberyUserEngagement getShrubbery() {
        return shrubberyUserEngagement;
    }

    public void setShrubberyUserEngagement(ShrubberyUserEngagement shrubbery) {
        this.shrubberyUserEngagement = shrubbery;
    }

}
