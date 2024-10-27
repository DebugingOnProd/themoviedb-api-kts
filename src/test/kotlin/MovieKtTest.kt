import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.slf4j.LoggerFactory
import java.io.File

class MainKtTest {

    private val logger = LoggerFactory.getLogger(MainKtTest::class.java)


    private fun readJsonFromFile(filePath:String ) :TmdbConfig{
        val gson = Gson()
        val readText = File(filePath).readText()
        return gson.fromJson(readText, TmdbConfig::class.java)
    }

    @Test
    @DisplayName("get movie detail")
    fun getMovieDetail() {
        val tmdbConfig = readJsonFromFile("src/test/resources/config.json")
        System.setProperty("java.net.useSystemProxies", "true");
        logger.info("tmdbConfig: $tmdbConfig")
        val movieApi = TmdbApi(tmdbConfig).getMovieApi()
        val movieDetail = movieApi.getDetails(11)
        logger.info("movieDetail: $movieDetail")
        assertNotNull(movieDetail)
    }
}