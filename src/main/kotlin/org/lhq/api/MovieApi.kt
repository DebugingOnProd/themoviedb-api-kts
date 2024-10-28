package org.lhq.api

import org.lhq.entity.movie.AccountStates
import org.lhq.entity.movie.MovieDetail
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class MovieApi(private val httpClient: HttpClient){

    /**
     * 获取电影详情
     */

    fun getDetails(movieId:Int) : MovieDetail? {
        val url = "/3/movie/$movieId"
        val json = httpClient.request(UrlBuilder(url), RequestType.GET)
        val result = GsonUtils.fromJson<MovieDetail>(json.orEmpty())
        return result
    }

    /**
     * Get the rating, watchlist and favourite status of an account.
     *  获取帐户的评分、关注列表和收藏夹状态。
     *
     */

    fun getAccountStates(movieId:Int) : AccountStates?{
        val url = "/3/movie/${movieId}/account_states"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET)
        val result = GsonUtils.fromJson<AccountStates>(response)
        return result
    }
}