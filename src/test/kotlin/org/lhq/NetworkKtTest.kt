package org.lhq

import org.junit.jupiter.api.DisplayName
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.network.NetworkDetails
import org.lhq.utlis.ReadFile
import org.slf4j.LoggerFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkKtTest {

    private val logger = LoggerFactory.getLogger(MovieKtTest::class.java)

    private val readFile = ReadFile()
    private val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")

    @Test
    @DisplayName("getNetworkDetails")
    fun testGetNetworkDetails(){
        val networkDetails = TmdbApi(tmdbConfig).getNetworkApi().getNetworkDetails(11)
        logger.info("networkDetails:{}",networkDetails)
        val expectedNetworkDetails = readFile.readEntity<NetworkDetails>("api_test_result/network/details.json")
        assertEquals(expectedNetworkDetails, networkDetails,"实际网站结果与预期结果不一致")
    }
}