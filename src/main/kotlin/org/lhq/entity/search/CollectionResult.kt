package org.lhq.entity.search

import com.google.gson.annotations.SerializedName

data class CollectionResult(
    val page: Int,
    val results: List<Collection>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Collection(
    val id: Int,
    val name: String,
    val adult: Boolean,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
)