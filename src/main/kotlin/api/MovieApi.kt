package org.lhq.api

import com.google.gson.Gson
import org.lhq.entity.movie.MovieDetail
import org.lhq.http.HttpClient
import org.lhq.http.RequestType

class MovieApi(private val httpClient: HttpClient){
    fun getDetails(movieId:Int) : MovieDetail {
        val url = "/3/movie/$movieId"
        val json = httpClient.request(url, RequestType.GET)
        val gson = Gson()
        val movieResult = gson.fromJson(json, MovieDetail::class.java)
        return movieResult
    }
}