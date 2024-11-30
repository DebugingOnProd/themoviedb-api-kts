package org.lhq.entity.search

import com.google.gson.annotations.SerializedName

data class CompanyResult(
    val page: Int,
    val results: List<Company>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Company(
    val id: Int,
    val name: String,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("origin_country")
    val originCountry: String
)