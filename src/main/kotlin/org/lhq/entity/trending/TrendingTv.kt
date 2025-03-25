package org.lhq.entity.trending

import com.google.gson.annotations.SerializedName

data class TrendingTv(
    val page: Int,
    val results: List<TrendingTvItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
data class TrendingTvItem(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val originalName: String,
    val overview: String,
    val posterPath: String,
    val mediaType: String,
    val adult: Boolean,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val firstAirDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>,

)