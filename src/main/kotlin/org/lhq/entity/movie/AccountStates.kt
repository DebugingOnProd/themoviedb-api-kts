package org.lhq.entity.movie


import com.google.gson.annotations.SerializedName

data class AccountStates(
    @SerializedName("favorite")
    val favorite: Boolean, // false
    @SerializedName("id")
    val id: Int, // 11
    @SerializedName("rated")
    val rated: Boolean, // false
    @SerializedName("watchlist")
    val watchlist: Boolean // false
)