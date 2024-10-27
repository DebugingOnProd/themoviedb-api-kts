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
    fun request(url:String,requestType: RequestType) : String? {
        val bearerToken = tmdbConfig.token
        var requestUrl = url
        logger.info("requestUrl:$requestUrl")
        if (!url.contains("api_key")){
            requestUrl = requestUrl + "?api_key=" + tmdbConfig.apiKey
        }
        if (!url.contains("language")) {
            requestUrl = requestUrl + "&language=" + tmdbConfig.language
        }
        requestUrl = tmdbConfig.baseUrl + requestUrl
        val uri = URI.create(requestUrl)
        logger.info("requestUrl:$uri")
        val httpRequestBuilder = HttpRequest
            .newBuilder()
            .uri(uri)
            .header("Authorization", "Bearer $bearerToken")
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