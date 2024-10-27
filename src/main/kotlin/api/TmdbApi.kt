package org.lhq.api

import org.lhq.entity.TmdbConfig
import org.lhq.http.HttpClient

class TmdbApi (tmdbConfig: TmdbConfig) {
    private val httpClient = HttpClient(tmdbConfig)
    fun getMovieApi() : MovieApi {
        return MovieApi(httpClient)
    }

    fun getAccountApi() : AccountApi {
        return AccountApi(httpClient)
    }
}