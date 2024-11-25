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
        val url = "/movie/$movieId"
        val json = httpClient.request(UrlBuilder(url), RequestType.GET,true)
        val result = GsonUtils.fromJson<MovieDetail>(json.orEmpty())
        return result
    }

    /**
     * Get the rating, watchlist and favourite status of an account.
     *  获取帐户的评分、关注列表和收藏夹状态。
     *
     */

    fun getAccountStates(movieId:Int) : AccountStates?{
        val url = "/movie/${movieId}/account_states"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET, true)
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
        val url = "/movie/${movieId}/alternative_titles"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET, true)
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
        val url = "/movie/${movieId}/changes"
        val urlBuilder = UrlBuilder(url)
        startDate?.let {
            urlBuilder.addParam("start_date",it.toString())
        }
        endDate?.let {
            urlBuilder.addParam("end_date",it.toString())
        }
        urlBuilder.addParam("page",page.toString())
        val result = httpClient.request(urlBuilder, RequestType.GET, true)
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
        val url = "/movie/${movieId}/credits"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val result = GsonUtils.fromJson<Credits>(response)
        return result
    }

    /**
     *
     * Get the external ids that we have stored for a movie.
     */

    fun getExternalIds(movieId:Int) : ExternalId?{
        val url = "/movie/${movieId}/external_ids"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET,true)
        val result = GsonUtils.fromJson<ExternalId>(response)
        return result
    }

    fun getImages(movieId:Int) : ImageData?{
        val url = "/movie/${movieId}/images"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET, false)
        val result = GsonUtils.fromJson<ImageData>(response)
        return result
    }


    fun getKeywords(movieId:Int) : KeywordList?{
        val url = "/movie/${movieId}/keywords"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET, false)
        val result = GsonUtils.fromJson<KeywordList>(response)
        return result
    }

    /**
     * Get the latest movie.
     */

    fun getLatest(): Latest? {
        val url = "/movie/latest"
        val response = httpClient.request(UrlBuilder(url), RequestType.GET, false)
        val latest = GsonUtils.fromJson<Latest>(response)
        return latest
    }

    /**
     * Get the lists that a movie has been added to.
     *
     */

    fun getList(movieId: Int, page: Int): ListResult? {
        val url = "/movie/${movieId}/lists"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val listResult = GsonUtils.fromJson<ListResult>(request)
        return listResult
    }

    fun getRecommendations(movieId: Int, page: Int): RecommendationResult? {
        val url = "/movie/${movieId}/recommendations"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val listResult = GsonUtils.fromJson<RecommendationResult>(request)
        return listResult
    }

    fun getReleaseDates(movieId: Int): ReleaseDate? {
        val url = "/movie/${movieId}/release_dates"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val releaseDate = GsonUtils.fromJson<ReleaseDate>(request)
        return releaseDate
    }

    /**
     * Get the user reviews for a movie.
     */
    fun getReviews(movieId: Int, page: Int) : Reviews? {
        val url = "/movie/${movieId}/reviews"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val reviews = GsonUtils.fromJson<Reviews>(request)
        return reviews
    }

    /**
     * Get the similar movies based on genres and keywords.
     */
    fun getSimilarMovies(movieId: Int, page: Int): SimilarResult? {
        val url = "/movie/${movieId}/similar"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val similarResult = GsonUtils.fromJson<SimilarResult>(request)
        return similarResult
    }

    /**
     * Get the translations for a movie.
     */
    fun getTranslations(movieId: Int): TranslationsResult? {
        val url = "/movie/${movieId}/translations"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val translationsResult = GsonUtils.fromJson<TranslationsResult>(request)
        return translationsResult
    }

    fun getVideos(movieId: Int): VideoResult? {
        val url = "/movie/${movieId}/videos"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val videoList = GsonUtils.fromJson<VideoResult>(request)
        return videoList
    }

    /**
     * Get the list of watch providers (flatrate) for a movie with a given id.
     */

    fun getWatchProviders(movieId: Int): WatchProvider? {
        val url = "/movie/${movieId}/watch/providers"
        val request = httpClient.request(UrlBuilder(url), RequestType.GET, true)
        val watchProvider = GsonUtils.fromJson<WatchProvider>(request)
        return watchProvider
    }

    /**
     * Rate a movie and save it to your rated list.
     */
    fun addRating(movieId: Int, rating: Float): RatingResult? {
        val url = "/movie/${movieId}/rating"
        val requestBody = "{\"value\":$rating}"
        val request = httpClient.post(UrlBuilder(url), requestBody)
        val ratingResult = GsonUtils.fromJson<RatingResult>(request)
        return ratingResult
    }


    fun deleteRating(movieId: Int): RatingResult? {
        val url = "/movie/${movieId}/rating"
        val request = httpClient.request(UrlBuilder(url), RequestType.DELETE, false)
        val ratingResult = GsonUtils.fromJson<RatingResult>(request)
        return ratingResult
    }
}