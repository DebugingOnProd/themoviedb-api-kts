package org.lhq.entity.account

import com.google.gson.annotations.SerializedName

data class FavoriteResult(
    val page: Long,
    val results: List<Result>,
    @SerializedName("total_results")
    val totalPage: Long,
    @SerializedName("total_pages")
    val totalPages: Long
)


data class Result (
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>,
    val id: Long,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long
)