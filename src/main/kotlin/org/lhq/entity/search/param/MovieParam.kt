package org.lhq.entity.search.param

data class MovieParam(
    val query: String,
    val includeAdult: Boolean?,
    val primaryReleaseYear: String?,
    val page: Int?,
    val region: String?,
    val year: String?,
)
