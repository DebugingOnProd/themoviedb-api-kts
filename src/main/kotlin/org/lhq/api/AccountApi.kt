package org.lhq.api

import org.lhq.entity.account.AccountDetails
import org.lhq.entity.account.FavoriteResult
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.param.AccountSortBy
import org.lhq.utils.GsonUtils

class AccountApi(private val httpClient: HttpClient) {

    fun getDetails(id: Int) : AccountDetails? {
        val url = "/3/account/$id"
        val urlBuilder = UrlBuilder(url)
        val json: String? = httpClient.request(urlBuilder, RequestType.GET)
        val accountDetails = GsonUtils.fromJson<AccountDetails>(json)
        return accountDetails
    }

    fun getFavoriteMovies(accountId: Int,page:Int,sortBy:AccountSortBy) : FavoriteResult? {
        val url = "/3/account/$accountId/favorite/movies"
        val paramUrl = UrlBuilder(url)
            .addParam("page", "$page")
            .addParam("sort_by", sortBy.getValue())
        val json: String? = httpClient.request(paramUrl, RequestType.GET)
        val result = GsonUtils.fromJson<FavoriteResult>(json)
        return result
    }

    fun getFavoriteTv(accountId: Int,page:Int,sortBy:AccountSortBy) : FavoriteResult? {
        val url = "/3/account/$accountId/favorite/tv"
        val paramUrl = UrlBuilder(url)
            .addParam("sort_by", sortBy.getValue())
            .addParam("page", "$page");
        val json: String? = httpClient.request(paramUrl, RequestType.GET)
        val result = GsonUtils.fromJson<FavoriteResult>(json)
        return result
    }
}