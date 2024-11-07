package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class RecommendationResult(
    val page: Long,
    val results: List<Recommendation>,
    val totalPages: Long,
    val totalResults: Long
)


data class Recommendation(
    val backdropPath: String,
    val id: Long,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("backdrop_path")
    val posterPath: String,
    @SerializedName("media_type")
    val mediaType: String,
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String,
    val genreIDS: List<Long>,
    val popularity: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long
)



