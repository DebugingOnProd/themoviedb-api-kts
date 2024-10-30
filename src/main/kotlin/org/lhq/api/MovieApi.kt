package org.lhq.api

import org.lhq.entity.movie.*
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate

class MovieApi(private val httpClient: HttpClient){

    private val logger : Logger = LoggerFactory.getLogger(MovieApi::class.java)

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


    /**
     * Get the list of alternative titles for a movie.
     * 获取电影其他名称列表。
     * @param movieId the movie id
     * @return AlternativeTitle
     */

    fun getAlternativeTitles(movieId:Int) : AlternativeTitle?{
        val url = "/3/movie/${movieId}/alternative_titles"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET)
        val result = GsonUtils.fromJson<AlternativeTitle>(response)
        return result
    }




    /**
     * Get the changes for a movie.  default only the last 24 hours are returned.
     *
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     * @param movieId
     * @param page
     * @param startDate
     * @param endDate
     * @param movieId the movie id
     * @return AlternativeTitle
     */
    fun getRecentChangesMovie(movieId: Int,
                              page:Int,
                              startDate: LocalDate?,
                              endDate: LocalDate?) : ChangeList? {
        val url = "/3/movie/${movieId}/changes"
        val urlBuilder = UrlBuilder(url)
        startDate?.let {
            urlBuilder.addParam("start_date",it.toString())
        }
        endDate?.let {
            urlBuilder.addParam("end_date",it.toString())
        }
        urlBuilder.addParam("page",page.toString())
        val result = httpClient.request(urlBuilder, RequestType.GET)
        val changeItem = GsonUtils.fromJson<ChangeList>(result)
        return changeItem
    }


    /**
     * Get the credits for a movie.
     * 获取电影 credits。
     * @param movieId the movie id
     * @return Credits
     */

    fun getCredits(movieId:Int) : Credits?{
        val url = "/3/movie/${movieId}/credits"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET)
        val result = GsonUtils.fromJson<Credits>(response)
        return result
    }

    /**
     *
     * Get the external ids that we have stored for a movie.
     */

    fun getExternalIds(movieId:Int) : ExternalId?{
        val url = "/3/movie/${movieId}/external_ids"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET)
        val result = GsonUtils.fromJson<ExternalId>(response)
        return result
    }
}