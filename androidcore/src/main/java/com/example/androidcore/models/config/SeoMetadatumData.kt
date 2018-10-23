package com.example.androidcore.models.config

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SeoMetadatumData {
    @SerializedName("page-title")
    @Expose
    var pageTitle: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("keywords")
    @Expose
    var keywords: String? = null
}