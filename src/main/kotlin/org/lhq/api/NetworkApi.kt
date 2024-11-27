package org.lhq.api

import org.lhq.entity.network.AlternativeName
import org.lhq.entity.network.NetworkDetails
import org.lhq.entity.network.NetworkImage
import org.lhq.http.HttpClient
import org.lhq.http.RequestType
import org.lhq.http.UrlBuilder
import org.lhq.utils.GsonUtils

class NetworkApi(private val httpClient: HttpClient) {


    fun getNetworkDetails(networkId:Int) : NetworkDetails? {
        val url = "/network/${networkId}"
        val request = UrlBuilder(url)
        val response = httpClient.request(request, RequestType.GET, true)
        return GsonUtils.fromJson<NetworkDetails>(response)
    }

    /**
     * Get the alternative names of a network.
     * @param networkId Int
     */

    fun getNetworkAlternativeNames(networkId:Int) : AlternativeName? {
        val url = "/network/${networkId}/alternative_names"
        val request = UrlBuilder(url)
        val response = httpClient.request(request, RequestType.GET, true)
        return GsonUtils.fromJson<AlternativeName>(response)
    }


    fun getTvNetworkImages(networkId: Int) : NetworkImage?{
        val url = "/network/${networkId}/images"
        val request = UrlBuilder(url)
        val response = httpClient.request(request, RequestType.GET, true)
        return GsonUtils.fromJson<NetworkImage>(response)
     }
}