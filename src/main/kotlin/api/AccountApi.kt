package org.lhq.api

import com.google.gson.Gson
import entity.account.AccountDetails
import org.lhq.http.HttpClient
import org.lhq.http.RequestType

class AccountApi(private val httpClient: HttpClient) {

    fun getDetails(id: Int) : AccountDetails {
        val url = "/3/account/$id"
        val json: String? = httpClient.request(url, RequestType.GET)
        val gson = Gson()
        return gson.fromJson(json, AccountDetails::class.java)
    }
}