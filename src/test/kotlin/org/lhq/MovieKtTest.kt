package org.lhq

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.movie.AccountStates
import org.lhq.entity.movie.AlternativeTitle
import org.lhq.entity.movie.Credits
import org.lhq.entity.movie.MovieDetail
import org.lhq.utlis.ReadFile
import org.slf4j.LoggerFactory
import java.time.LocalDate
import kotlin.test.assertEquals

class MovieKtTest {

    private val logger = LoggerFactory.getLogger(MovieKtTest::class.java)

    private val readFile = ReadFile()
    private val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")



    @Test
    @DisplayName("get_movie_detail")
    fun getMovieDetailTest() {
        System.setProperty("java.net.useSystemProxies", "true");
        logger.debug("config: {}", tmdbConfig)
        val movieApi = TmdbApi(tmdbConfig).getMovieApi()
        val movieDetail = movieApi.getDetails(11)
        logger.debug("movieDetail: {}", movieDetail)
        val expectedDetails = readFile.readEntity<MovieDetail>("api_test_result/movie/details.json")
        assertEquals(expectedDetails, movieDetail,"电影请求结果实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_account_states")
    fun getAccountStatesTest() {
        val accountStates = TmdbApi(tmdbConfig).getMovieApi().getAccountStates(11)
        val expectedAccountStates = readFile.readEntity<AccountStates>("api_test_result/movie/account_states.json")
        assertEquals(expectedAccountStates, accountStates,"用户电影状态实际值与预期值不相等")
        logger.debug("accountStates: {}", accountStates)
    }

    @Test
    @DisplayName("get_alternative_titles")
    fun getAlternativeTitlesTest(){
        val alternativeTitles = TmdbApi(tmdbConfig).getMovieApi().getAlternativeTitles(11)
        val expectedAlternativeTitle = readFile.readEntity<AlternativeTitle>("api_test_result/movie/alternative_titles.json")
        assertEquals(expectedAlternativeTitle, alternativeTitles,"电影替代标题实际值与预期值不相等")
        logger.debug("alternativeTitles: {}", alternativeTitles)
    }

    @Test
    @DisplayName("get_recent_changes_movie")
    fun getRecentChangesMovieTest(){
        val nowDate = LocalDate.now()
        val aWeedAge = nowDate.plusDays(-7)
        val recentChangesMovie = TmdbApi(tmdbConfig).getMovieApi().getRecentChangesMovie(912649, 1, aWeedAge, nowDate)
        logger.debug("recentChangesMovie: {}", recentChangesMovie)
    }

    @Test
    @DisplayName("get_credits")
    fun getCreditsTest(){
        val credits = TmdbApi(tmdbConfig).getMovieApi().getCredits(11)
        val expectedCredits = readFile.readEntity<Credits>("api_test_result/movie/credits.json")
        logger.debug("credits: {}", credits)
        assertEquals(expectedCredits, credits)
    }
}