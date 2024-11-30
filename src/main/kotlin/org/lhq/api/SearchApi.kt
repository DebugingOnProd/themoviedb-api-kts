package org.lhq.api

import org.lhq.entity.search.CollectionResult
import org.lhq.entity.search.CompanyResult
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class SearchApi(private val httpClient: HttpClient) {


    /**
     * Search for collections by their original, translated and alternative names.
     *
     */
    fun searchCollection(query: String,includeAdult : Boolean?, page: Int?,region: String?) : CollectionResult? {
        val url = "search/collection"
        val param = UrlBuilder(url).addParam("query", query)
        includeAdult?.let { param.addParam("include_adult", it.toString()) }
        page?.let { param.addParam("page", it.toString()) }
        region?.let { param.addParam("region", it) }
        val result = httpClient.request(param, RequestType.GET,true)
        val collectionResult = GsonUtils.fromJson<CollectionResult>(result)
        return collectionResult
    }

    fun searchCompany(query: String,page: Int?): CompanyResult?{
        val url = "search/company"
        val param = UrlBuilder(url).addParam("query", query)
        page?.let { param.addParam("page", it.toString()) }
        val request = httpClient.request(param, RequestType.GET, true)
        val companyResult = GsonUtils.fromJson<CompanyResult>(request)
        return companyResult
    }
}