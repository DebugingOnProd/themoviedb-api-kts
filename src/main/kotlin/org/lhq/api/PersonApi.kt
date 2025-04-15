package org.lhq.api

import org.lhq.entity.search.PersonResult
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class PersonApi (private val httpClient: HttpClient){

    fun getPersonDetails(personId: Int, language: String?): PersonResult? {
        val url = "person/$personId"
        val param = UrlBuilder(url)
        language?.let { param.addParam("language", it) }
        val response = httpClient.request(param, RequestType.GET, true)
        val fromJson = GsonUtils.fromJson<PersonResult>(response)
        return fromJson

    }

}