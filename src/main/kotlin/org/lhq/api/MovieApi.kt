package org.lhq.api

import org.lhq.entity.movie.MovieDetail
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class MovieApi(private val httpClient: HttpClient){
    fun getDetails(movieId:Int) : MovieDetail? {
        val url = "/3/movie/$movieId"
        val json = httpClient.request(UrlBuilder(url), RequestType.GET)
        val result = GsonUtils.fromJson<MovieDetail>(json.orEmpty())
        return result
    }
}