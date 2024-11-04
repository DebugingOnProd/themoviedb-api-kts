package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class ListResult(
    val id : Int,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Result (
    val description: String,
    val favoriteCount: Int,
    val id: String,
    val itemCount: Int,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("list_type")
    val listType: String,
    val name: String,
    val posterPath: String?
)