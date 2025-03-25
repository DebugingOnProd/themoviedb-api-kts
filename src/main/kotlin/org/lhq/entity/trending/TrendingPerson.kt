package org.lhq.entity.trending

data class TrendingPerson(
    val page: Int,
    val results: List<TrendingPersonItem>,
    val totalPages: Int,
    val totalResults: Int
)

data class TrendingPersonItem(
    val id: Int,
    val name: String,
    val originalName: String,
    val mediaType: String,
    val adult: Boolean,
    val popularity: Double,
    val gender: Int,
    val knownForDepartment: String,
    val profilePath: String,
)