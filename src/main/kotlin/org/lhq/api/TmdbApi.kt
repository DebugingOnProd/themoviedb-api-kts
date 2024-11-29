package org.lhq.api

import org.lhq.entity.TmdbConfig
import org.lhq.http.HttpClient

class TmdbApi private constructor( private val tmdbConfig: TmdbConfig) {
    private val httpClient = HttpClient(tmdbConfig)

    private inline fun <reified T> createApi(apiClass: Class<T>) : T {
        return when (apiClass) {
            MovieApi::class.java -> MovieApi(httpClient) as T
            AccountApi::class.java -> AccountApi(httpClient)  as T
            NetworkApi::class.java -> NetworkApi(httpClient) as T
            SearchApi::class.java -> SearchApi(httpClient) as T
            else -> throw IllegalArgumentException("Unsupported API class: $apiClass")
        }
    }

    fun getMovieApi() = createApi(MovieApi::class.java)
    fun getAccountApi() = createApi(AccountApi::class.java)
    fun getNetworkApi() = createApi(NetworkApi::class.java)

    fun getSearchApi() = createApi(SearchApi::class.java)



    companion object {
        private lateinit var tmdbConfig: TmdbConfig
        private val instance: TmdbApi by lazy {
            TmdbApi(tmdbConfig)
        }
        fun initialize(config: TmdbConfig) {
            tmdbConfig = config
        }
        fun getInstanceApi(): TmdbApi {
            return instance
        }
    }

}