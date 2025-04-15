package org.lhq

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.utlis.ReadFile
import org.slf4j.LoggerFactory
import kotlin.test.Test

class PersonTest {
    private val logger = LoggerFactory.getLogger(PersonTest::class.java)

    private val readFile = ReadFile()
    private val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")

    @BeforeEach
    fun init () {
        TmdbApi.initialize(tmdbConfig)
    }



    @Test
    @DisplayName("get_person_details")
    fun testGetPersonDetails(){
        val networkDetails = TmdbApi.getApiInstance().getPersonApi().getPersonDetails(414,"zh-CN")
        logger.info("networkDetails:{}",networkDetails)
        // val expectedNetworkDetails = readFile.readEntity<NetworkDetails>("api_test_result/network/details.json")
        // assertEquals(expectedNetworkDetails, networkDetails,"实际网站结果与预期结果不一致")
    }

}