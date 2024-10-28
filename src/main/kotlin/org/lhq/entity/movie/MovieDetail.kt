package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class MovieDetail(
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
    // 官网
    var homepage: String?,
    // 人气；知名度
    var popularity: Double?,
    // 海报
    @SerializedName("poster_path")
    var posterPath: String?,
    // 制作公司
    @SerializedName("production_companies")
    var productionCompanies : List<ProductionCompany>?,
    // 国家
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry>?,
    // 收益
    var revenue: Long?,
    // 状态
    var status: String?,
    // 标签
    var tagline: String?,
    // 视频
    var video:Boolean?,
    // 评分
    @SerializedName("vote_average")
    var voteAverage: Double?,
    // 评价数量
    @SerializedName("vote_count")
    var voteCount: Long


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

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String,
)