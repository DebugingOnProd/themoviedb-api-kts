package org.lhq

import org.lhq.entity.account.AccountDetails
import org.junit.jupiter.api.DisplayName
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.account.FavoriteResult
import org.lhq.param.AccountSortBy
import org.slf4j.LoggerFactory
import org.lhq.utlis.ReadFile
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountKtTest {


    private val logger = LoggerFactory.getLogger(AccountKtTest::class.java)

    @Test
    @DisplayName("get_account_details")
    fun getAccountDetailsTest() {
        val  readFile = ReadFile();
        val configStr = readFile.readJsonFileAsString("config.json")
        val tmdbConfig = readFile.strToEntity<TmdbConfig>(configStr)
        System.setProperty("java.net.useSystemProxies", "true");
        val accountApi = TmdbApi(tmdbConfig).getAccountApi()
        val actualDetails = accountApi.getDetails(20874374)
        logger.info("actualDetails:{}",actualDetails)
        val expectedDetails = readFile.readEntity<AccountDetails>("api_test_result/account/details.json")
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
        val  readFile = ReadFile();
        System.setProperty("java.net.useSystemProxies", "true");
        val configStr = readFile.readJsonFileAsString("config.json")
        val tmdbConfig = readFile.strToEntity<TmdbConfig>(configStr)
        val favoriteMovies = TmdbApi(tmdbConfig).getAccountApi()
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
        val  readFile = ReadFile();
        val configStr = readFile.readJsonFileAsString("config.json")
        val tmdbConfig = readFile.strToEntity<TmdbConfig>(configStr)
        AccountSortBy.CREATED_AT_ASC
        val favoriteTv = TmdbApi(tmdbConfig).getAccountApi()
            .getFavoriteTv(
                20874374,
                1,
                AccountSortBy.CREATED_AT_DESC
            )
        val expectedFavorite  = readFile.readEntity<FavoriteResult>("api_test_result/account/favorite_tvs.json")
        logger.info("favoriteTv:{}",favoriteTv)
        assertEquals(expectedFavorite,favoriteTv,"TmdbApi.getFovriteTv 请求结果与预期不一致")
    }
}