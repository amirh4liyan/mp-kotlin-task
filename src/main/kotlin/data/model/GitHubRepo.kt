package edu.sharif.kotlin.data.model

import com.google.gson.annotations.SerializedName

data class GitHubRepo(
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String
)

