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
        val ( _, apiKey:String, _ ,language:String, apiVersion:String) = tmdbConfig
        url.addParam("api_key", apiKey)
        if (needLanguage) {
            url.addParam("language", language)
        }
        logger.info("url:$url")
        return request(url.build(),requestType, null)
    }

    fun post(url: UrlBuilder, body: String) : String? {
        return request(url.build(),RequestType.POST, body)
    }


    private fun request(url:String, requestType: RequestType, body: String?) : String? {
        val (baseUrl : String, _ ,token : String, _,apiVersion:String ) = tmdbConfig
        val requestUrl = "$baseUrl/$apiVersion$url"
        val uri = URI.create(requestUrl)
        logger.info("requestUrl:$uri")
        // 记录请求开始时间
        val startTime = System.currentTimeMillis()

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
                httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(body))
            }
            RequestType.DELETE -> {
                httpRequestBuilder.DELETE()
            }
        }
        val response = httpClientTMDB.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString())
        val responseBody = response.body()
        // 记录请求耗时
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        logger.debug("Request took $duration ms")
        logger.debug("responseBody:$responseBody")
        return responseBody

    }
}