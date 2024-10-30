package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class Image(
    val aspectRatio: Double,
    val height: Int,
    @SerializedName("iso_639_1")
    val iso16391: String,
    val filePath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)

data class ImageData(
    val id :Long,
    val backdrops: List<Image>,
    val logos: List<Image>,
    val posters: List<Image>
)