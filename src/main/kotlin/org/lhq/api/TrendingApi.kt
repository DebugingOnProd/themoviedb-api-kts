package org.lhq.api

import org.lhq.entity.search.TrendingResult
import org.lhq.entity.trending.TrendingMovie
import org.lhq.entity.trending.TrendingPerson
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class TrendingApi(private val httpClient: HttpClient) {

    fun getTrending(timeWindow: String) : TrendingResult? {
        val url = "trending/all/$timeWindow"
        val urlBuilder = UrlBuilder(url)
        val response = httpClient.request(urlBuilder, RequestType.GET,true)
        return GsonUtils.fromJson<TrendingResult>(response)
    }
    fun getTrendingMovie(timeWindow: String) : TrendingMovie? {
        val url = "trending/movie/$timeWindow"
        val urlBuilder = UrlBuilder(url)
        val response = httpClient.request(urlBuilder, RequestType.GET,true)
        return GsonUtils.fromJson<TrendingMovie>(response)
    }

    fun getTrendingPerson(timeWindow: String): TrendingPerson? {
        val url = "trending/person/$timeWindow"
        val urlBuilder = UrlBuilder(url)
        val response = httpClient.request(urlBuilder, RequestType.GET,true)
        return GsonUtils.fromJson<TrendingPerson>(response)
    }

}