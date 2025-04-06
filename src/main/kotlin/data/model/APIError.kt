package edu.sharif.kotlin.data.model

import com.google.gson.annotations.SerializedName

data class APIError(
    @SerializedName("message") val message: String,
    @SerializedName("documentation_url") val url: String,
)