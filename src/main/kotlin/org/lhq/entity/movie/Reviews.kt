package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class Reviews(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val totalPages: Int,
    val totalResults: Int
)
data class Review(
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)
data class AuthorDetails(
    val name: String,
    val username: String,
    val avatarPath: String,
    val rating: Double,
)