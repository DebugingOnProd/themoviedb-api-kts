package org.lhq.entity.movie

import com.google.gson.annotations.SerializedName

data class TranslationsResult (
    val id: Int,
    val translations: List<Translation>
)

data class Translation (
    @SerializedName("iso3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String,
    @SerializedName("english_name")
    val englishName: String,
    val data: MovieData
)

data class MovieData (
    val homepage: String,
    val overview: String,
    val tagline: String,
    val title: String
)