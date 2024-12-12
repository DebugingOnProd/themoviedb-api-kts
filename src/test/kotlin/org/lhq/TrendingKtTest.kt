package org.lhq

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.utlis.ReadFile
import org.slf4j.LoggerFactory

class TrendingKtTest {
    private val logger = LoggerFactory.getLogger(TrendingKtTest::class.java)

    private val readFile = ReadFile()
    private val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")

    @BeforeEach
    fun init () {
        TmdbApi.initialize(tmdbConfig)
    }


    @Test
    @DisplayName("trending_all_test")
    fun testTrendingAll() {
        val trendingApi = TmdbApi.getInstanceApi().getTrendingApi()
        val trendingResult = trendingApi.getTrending("day")
        logger.info("trendingResult:{}",trendingResult)
    }

    @Test
    @DisplayName("trending_movie_test")
    fun testTrendingMovie() {
        val trendingApi = TmdbApi.getInstanceApi().getTrendingApi()
        val trendingResult = trendingApi.getTrendingMovie("day")
        logger.info("trendingMovieResult:{}",trendingResult)
    }
}