package org.lhq.http

import org.lhq.entity.TmdbConfig
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpClient(private val tmdbConfig: TmdbConfig) {


    private val logger = LoggerFactory.getLogger(HttpClient::class.java)
    private val httpClientTMDB = HttpClient.newHttpClient()

    fun request(url:UrlBuilder ,requestType: RequestType,needLanguage: Boolean) :String? {
        val ( _, apiKey:String, _ ,language:String) = tmdbConfig
        url.addParam("api_key", apiKey)
        if (needLanguage) {
            url.addParam("language", language)
        }
        logger.info("url:$url")
        return request(url.build(),requestType)
    }


    private fun request(url:String, requestType: RequestType) : String? {
        val (baseUrl : String, _ ,token : String, _ ) = tmdbConfig
        val requestUrl = baseUrl + url
        val uri = URI.create(requestUrl)
        logger.info("requestUrl:$uri")
        val httpRequestBuilder = HttpRequest
            .newBuilder()
            .uri(uri)
            .header("Authorization", "Bearer $token")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
        when (requestType) {
            RequestType.GET -> {
                httpRequestBuilder.GET()
            }
            RequestType.POST -> {
                httpRequestBuilder.POST(HttpRequest.BodyPublishers.noBody())
            }
            RequestType.DELETE -> {
                httpRequestBuilder.DELETE()
            }
        }
        val response = httpClientTMDB.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString())
        val responseBody = response.body()
        logger.debug("responseBody:$responseBody")
        return responseBody

    }
}