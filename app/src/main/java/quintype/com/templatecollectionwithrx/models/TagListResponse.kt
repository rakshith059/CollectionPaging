package quintype.com.templatecollectionwithrx.models

import quintype.com.templatecollectionwithrx.models.story.Story
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TagListResponse {
    @SerializedName("stories")
    @Expose
    var stories: List<Story>? = null
}
