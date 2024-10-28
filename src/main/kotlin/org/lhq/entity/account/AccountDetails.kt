package org.lhq.entity.account

import com.google.gson.annotations.SerializedName

data class AccountDetails(
    val avatar: Avatar,
    val id: Int,
    @SerializedName("include_adult")
    val includeAdult: Boolean,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String,
    val username: String,

    )

data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb
)


data class Gravatar(
    val hash: String
)


data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: String
)