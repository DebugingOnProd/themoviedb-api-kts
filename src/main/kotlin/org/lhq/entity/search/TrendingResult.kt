package org.lhq.entity.search

import com.google.gson.annotations.SerializedName

data class TrendingResult(
    val page: Int,
    val results: List<Trending>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Trending(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
)