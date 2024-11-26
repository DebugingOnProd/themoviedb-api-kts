package org.lhq

import org.junit.jupiter.api.BeforeEach
import org.lhq.entity.account.AccountDetails
import org.junit.jupiter.api.DisplayName
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.account.FavoriteResult
import org.lhq.entity.movie.RecommendationResult
import org.lhq.param.AccountSortBy
import org.slf4j.LoggerFactory
import org.lhq.utlis.ReadFile
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountKtTest {


    private val logger = LoggerFactory.getLogger(AccountKtTest::class.java)
    private val  readFile = ReadFile()


    @BeforeEach
    fun init() {
        val configStr = readFile.readJsonFileAsString("config.json")
        val tmdbConfig = readFile.strToEntity<TmdbConfig>(configStr)
        TmdbApi.initialize(tmdbConfig)
    }

    @Test
    @DisplayName("get_account_details")
    fun getAccountDetailsTest() {
        System.setProperty("java.net.useSystemProxies", "true");
        val accountApi = TmdbApi.getInstanceApi().getAccountApi()
        val actualDetails = accountApi.getDetails(20874374)
        logger.info("actualDetails:{}",actualDetails)
        val expectedDetails = ReadFile().readEntity<AccountDetails>("api_test_result/account/details.json")
        /**
         * expected：期望的结果。
         * actual：实际的结果。
         * message：可选参数，测试失败时显示的错误消息。
         */
        assertEquals(expectedDetails,actualDetails,"accountApi.getDetails 请求结果与预期不一致")
    }


    @Test
    @DisplayName("get_favorite_movies")
    fun getFavoriteMoviesTest(){
        val favoriteMovies = TmdbApi.getInstanceApi().getAccountApi()
            .getFavoriteMovies(
                20874374,
                1,
                AccountSortBy.CREATED_AT_DESC)
        logger.info("favoriteMovies:{}",favoriteMovies)
        val expectedFavorite  = readFile.readEntity<FavoriteResult>("api_test_result/account/favorite_movies.json")
        assertEquals(expectedFavorite,favoriteMovies,"TmdbApi.getFovriteMovies 请求结果与预期不一致")
    }


    @Test
    @DisplayName("get_favorite_tv")
    fun getFavoriteTvTest(){
        AccountSortBy.CREATED_AT_ASC
        val favoriteTv = TmdbApi.getInstanceApi().getAccountApi()
            .getFavoriteTv(
                20874374,
                1,
                AccountSortBy.CREATED_AT_DESC
            )
        val expectedFavorite  = readFile.readEntity<FavoriteResult>("api_test_result/account/favorite_tvs.json")
        logger.info("favoriteTv:{}",favoriteTv)
        assertEquals(expectedFavorite,favoriteTv,"TmdbApi.getFovriteTv 请求结果与预期不一致")
    }


    @Test
    @DisplayName("get_recommendations")
    fun getRecommendationsTest(){
        val recommendations = TmdbApi.getInstanceApi().getMovieApi().getRecommendations(11,1)
        val expectedRecommendations  = readFile.readEntity<RecommendationResult>("api_test_result/movie/recommendations.json")
        logger.info("recommendations:{}",recommendations)
        assertEquals(expectedRecommendations,recommendations,"TmdbApi.getRecommendations 请求结果与预期不一致")
    }
}