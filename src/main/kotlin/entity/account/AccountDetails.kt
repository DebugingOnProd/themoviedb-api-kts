package entity.account

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
    val username: String
)