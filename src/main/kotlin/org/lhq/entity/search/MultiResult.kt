package org.lhq.entity.search

import com.google.gson.annotations.SerializedName

data class MultiResult(
    val page: Int,
    val results: List<Multi>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)


data class Multi(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id: Int,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("media_type")
    val mediaType: String,
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String,
    val genreIds: List<Int>,
    val popularity: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    )
