package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class Movie(
    // id
    var id: Int,
    // 是否是含有成人内容
    var adult: Boolean?,
    // imdb 编号
    @SerializedName("imdb_id")
    var imdbId: String?,
    // 标题
    var title : String?,
    // 原始标题
    @SerializedName("original_title")
    var originTitle : String?,
    // 概要
    var overview: String?,
    // 片长
    var runtime: Int?,
    @SerializedName("release_date")
    var releaseDate : String?,
    // 影片语言
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>?,
    // 原始语言
    @SerializedName("original_language")
    var originalLanguage : String?,
    // 国家
    @SerializedName("origin_country")
    var originCountry: List<String>?,
    // 海报
    @SerializedName("backdrop_path")
    var backdropPath : String?,
    // 归属系列
    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollection?,
    // 预算
    var budget: Long?,
    // 流派
    var genres: List<Genre>,


)
data class BelongsToCollection(
    val id: Long,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
)

data class Genre(
    val id: Long,
    val name: String,
)


data class ProductionCompany(
    val id: Long,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String,
)


data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String,
)