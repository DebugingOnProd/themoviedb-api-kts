package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class AlternativeTitle(
    val id: Int,
    val titles: List<title>
)

data class title(
    val title: String,
    val type: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
)