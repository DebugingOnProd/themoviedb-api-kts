package org.lhq.entity.movie

data class Keyword(
    val id: Long,
    val name: String
)

data class KeywordList(
    val id: Long,
    val keywords: List<Keyword>
)