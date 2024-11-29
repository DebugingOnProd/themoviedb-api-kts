package org.lhq

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.search.CollectionResult
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
        logger.info("searchResult:{}",searchResult)
        val expectedResult = readFile.readEntity<CollectionResult>("api_test_result/search/collection.json")
        assertEquals(expectedResult,searchResult,"预期的搜索结果和实际结果不一致")
    }
}