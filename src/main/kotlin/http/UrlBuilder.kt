package org.lhq.http

import java.net.URLEncoder


class UrlBuilder(private val baseUrl: String) {
    private val params = mutableListOf<Pair<String,String>>()
    fun addParam(key: String,value:String):UrlBuilder {
        params.add(key to value)
        return this
    }

    fun build() :String {
        val queryString = params
            .joinToString("&") {
            (key,value) ->
                "${encode(key)}=${encode(value)}"
        }
        return if (params.isEmpty()) {
             baseUrl
        }else {
             "$baseUrl?$queryString"
        }
    }

    private fun encode(str: String) : String {
        return URLEncoder.encode(str,"utf-8")
    }

    override fun toString(): String {
        val result :StringBuilder = StringBuilder()
        for (item in params){
            result.append(item.first)
                .append("=")
                .append(item.second)
                .append("&")
        }
        result.deleteCharAt(result.lastIndex)
        return this.baseUrl + "?" + result.toString()
    }
}