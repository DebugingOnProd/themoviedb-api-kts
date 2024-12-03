package org.lhq

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.search.CollectionResult
import org.lhq.entity.search.CompanyResult
import org.lhq.entity.search.KeywordResult
import org.lhq.entity.search.MovieResult
import org.lhq.entity.search.param.MovieParam
import org.lhq.utlis.ReadFile
import org.slf4j.LoggerFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchKtTest {


    private val logger = LoggerFactory.getLogger(SearchKtTest::class.java)

    private val readFile = ReadFile()
    private val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")

    @BeforeEach
    fun init () {
        TmdbApi.initialize(tmdbConfig)
    }

    @Test
    @DisplayName("Search_Collection_Test")
    fun testSearchCollection() {
        val searchApi = TmdbApi.getInstanceApi().getSearchApi()
        val searchResult = searchApi.searchCollection("流浪地球",false,1,"CN")
        logger.info("searchCollectionResult:{}",searchResult)
        val expectedResult = readFile.readEntity<CollectionResult>("api_test_result/search/collection.json")
        assertEquals(expectedResult,searchResult,"预期的搜索结果和实际结果不一致")
    }

    @Test
    @DisplayName("Search_Company_Test")
    fun testSearchCompany() {
        val searchApi = TmdbApi.getInstanceApi().getSearchApi()
        val searchResult = searchApi.searchCompany("Sony",1)
        logger.info("searchCompanyResult:{}",searchResult)
        val expectedResult = readFile.readEntity<CompanyResult>("api_test_result/search/company.json")
        assertEquals(expectedResult,searchResult,"预期的搜索结果和实际结果不一致")
    }


    @Test
    @DisplayName("Search_Keyword_Test")
    fun testSearchKeyword() {
        val searchApi = TmdbApi.getInstanceApi().getSearchApi()
        val searchResult = searchApi.searchKeyword("Arcane",1)
        logger.info("searchKeywordResult:{}",searchResult)
        val expectedResult = readFile.readEntity<KeywordResult>("api_test_result/search/keyword.json")
        assertEquals(expectedResult,searchResult,"预期的搜索结果和实际结果不一致")
    }
    @Test
    @DisplayName("Search_Movie_Test")
    fun testSearchMovie() {
        val searchApi = TmdbApi.getInstanceApi().getSearchApi()
        val param =MovieParam("流浪地球")
        val searchResult = searchApi.searchMovie(param)
        logger.info("searchMovieResult:{}",searchResult)
        val expectedResult = readFile.readEntity<MovieResult>("api_test_result/search/movie.json")
        assertEquals(expectedResult,searchResult,"预期的搜索结果和实际结果不一致")
    }
}