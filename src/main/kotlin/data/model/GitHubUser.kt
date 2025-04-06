package edu.sharif.kotlin.data.model

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    @SerializedName("login") val username: String,
    @SerializedName("public_repos") val publicReposCount: Int,
    @SerializedName("created_at") val joinedDate: String,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("following") val followingCount: Int
)