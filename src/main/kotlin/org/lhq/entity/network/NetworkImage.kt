package org.lhq.entity.network

import com.google.gson.annotations.SerializedName

data class NetworkImage(
    val id: Int,
    val logos: List<Logo>
)

data class Logo (
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,
    @SerializedName("file_path")
    val filePath: String,
    val height: Int,
    val width: Int,
    val id: String,
    @SerializedName("file_type")
    val fileType: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
)
