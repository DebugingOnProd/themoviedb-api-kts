package org.lhq.entity.network

data class AlternativeName(
    val id: Int,
    val results: List<NameResult>
)

data class NameResult(
    val name: String,
    val type: String
)