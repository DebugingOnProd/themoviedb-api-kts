package org.lhq.api

import org.lhq.entity.search.CollectionResult
import org.lhq.entity.search.CompanyResult
import org.lhq.entity.search.KeywordResult
import org.lhq.entity.search.MovieResult
import org.lhq.entity.search.param.MovieParam
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


    fun searchKeyword(query: String,page: Int?): KeywordResult?{
        val url = "search/keyword"
        val param = UrlBuilder(url).addParam("query", query)
        page?.let { param.addParam("page", it.toString()) }
        val request = httpClient.request(param, RequestType.GET, true)
        val keywordResult = GsonUtils.fromJson<KeywordResult>(request)
        return keywordResult
    }


    fun searchMovie(query: MovieParam): MovieResult?{
        val url = "search/movie"
        val queryStr = query.query
        val param = UrlBuilder(url).addParam("query",queryStr)
        query.includeAdult?.let{
            param.addParam("include_adult",it.toString())
        }
        query.primaryReleaseYear?.let{
            param.addParam("primary_release_year",it.toString())
        }
        query.page?.let{
            param.addParam("page",it.toString())
        }
        query.region?.let{
            param.addParam("region",it.toString())
        }
        query.year?.let{
            param.addParam("year", it.toString())
        }
        val rep = httpClient.request(param, RequestType.GET, true)
        val result = GsonUtils.fromJson<MovieResult>(rep)
        return result;
    }
}