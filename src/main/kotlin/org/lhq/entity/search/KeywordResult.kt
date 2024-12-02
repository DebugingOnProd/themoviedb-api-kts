package org.lhq.entity.search

import com.google.gson.annotations.SerializedName

data class KeywordResult(
    val page: Int,
    val results: List<Keyword>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Keyword(
    val id: Int,
    val name: String,
)