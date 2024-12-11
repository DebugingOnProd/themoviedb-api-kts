package org.lhq.api

import org.lhq.entity.search.TrendingResult
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class TrendingApi(private val httpClient: HttpClient) {

    fun getTrending(mediaType: String,timeWindow: String,page: Int) : TrendingResult? {
        val url = "trending/all/$timeWindow"
        val urlBuilder = UrlBuilder(url)
        val response = httpClient.request(urlBuilder, RequestType.GET,true)
        return GsonUtils.fromJson<TrendingResult>(response)
    }

}