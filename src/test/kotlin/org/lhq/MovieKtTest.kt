package org.lhq

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.movie.MovieDetail
import org.slf4j.LoggerFactory
import org.lhq.utlis.ReadFile
import kotlin.test.assertEquals

class MovieKtTest {

    private val logger = LoggerFactory.getLogger(MovieKtTest::class.java)


    @Test
    @DisplayName("get_movie_detail")
    fun getMovieDetail() {
        val readFile = ReadFile()
        val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")
        System.setProperty("java.net.useSystemProxies", "true");
        logger.info("config: $tmdbConfig")
        val movieApi = TmdbApi(tmdbConfig).getMovieApi()
        val movieDetail = movieApi.getDetails(11)
        logger.info("movieDetail: $movieDetail")
        val expectedDetails = readFile.readEntity<MovieDetail>("api_test_result/movie/details.json")
        assertEquals(expectedDetails, movieDetail,"电影请求结果实际值与预期值不相等")

    }
}