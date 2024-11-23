package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class RatingResult(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    val success: Boolean
)
