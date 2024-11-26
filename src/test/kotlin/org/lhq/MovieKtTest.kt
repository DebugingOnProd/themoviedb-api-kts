package org.lhq

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.lhq.api.TmdbApi
import org.lhq.entity.TmdbConfig
import org.lhq.entity.movie.*
import org.lhq.utlis.ReadFile
import org.slf4j.LoggerFactory
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MovieKtTest {

    private val logger = LoggerFactory.getLogger(MovieKtTest::class.java)

    private val readFile = ReadFile()
    private val tmdbConfig = readFile.readEntity<TmdbConfig>("config.json")


    @BeforeEach
    fun init() {
        val  readFile = ReadFile()
        val configStr = readFile.readJsonFileAsString("config.json")
        val tmdbConfig = readFile.strToEntity<TmdbConfig>(configStr)
        TmdbApi.initialize(tmdbConfig)
    }

    @Test
    @DisplayName("get_movie_detail")
    fun getMovieDetailTest() {
        System.setProperty("java.net.useSystemProxies", "true");
        logger.debug("config: {}", tmdbConfig)
        val movieApi = TmdbApi.getInstanceApi().getMovieApi()
        val movieDetail = movieApi.getDetails(11)
        logger.debug("movieDetail: {}", movieDetail)
        val expectedDetails = readFile.readEntity<MovieDetail>("api_test_result/movie/details.json")
        assertEquals(expectedDetails, movieDetail,"电影请求结果实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_account_states")
    fun getAccountStatesTest() {
        val accountStates = TmdbApi.getInstanceApi().getMovieApi().getAccountStates(11)
        val expectedAccountStates = readFile.readEntity<AccountStates>("api_test_result/movie/account_states.json")
        assertEquals(expectedAccountStates, accountStates,"用户电影状态实际值与预期值不相等")
        logger.debug("accountStates: {}", accountStates)
    }

    @Test
    @DisplayName("get_alternative_titles")
    fun getAlternativeTitlesTest(){
        val alternativeTitles = TmdbApi.getInstanceApi().getMovieApi().getAlternativeTitles(11)
        val expectedAlternativeTitle = readFile.readEntity<AlternativeTitle>("api_test_result/movie/alternative_titles.json")
        assertEquals(expectedAlternativeTitle, alternativeTitles,"电影替代标题实际值与预期值不相等")
        logger.debug("alternativeTitles: {}", alternativeTitles)
    }

    @Test
    @DisplayName("get_recent_changes_movie")
    fun getRecentChangesMovieTest(){
        val endDate = LocalDate.parse("2024-10-29")
        val startDate = LocalDate.parse("2024-10-28")
        val recentChangesMovie =TmdbApi.getInstanceApi()
            .getMovieApi()
            .getRecentChangesMovie(912649, 1, startDate, endDate)
        val expectedChangesList = readFile.readEntity<ChangeList>("api_test_result/movie/changes.json")
        logger.debug("recentChangesMovie: {}", recentChangesMovie)
        assertEquals(expectedChangesList, recentChangesMovie)
    }

    @Test
    @DisplayName("get_credits")
    fun getCreditsTest(){
        val credits = TmdbApi.getInstanceApi().getMovieApi().getCredits(11)
        val expectedCredits = readFile.readEntity<Credits>("api_test_result/movie/credits.json")
        logger.debug("credits: {}", credits)
        assertEquals(expectedCredits, credits)
    }

    @Test
    @DisplayName("get_external_ids")
    fun getExternalIdsTest(){
        val externalIds = TmdbApi.getInstanceApi().getMovieApi().getExternalIds(11)
        val expectedExternalIds = readFile.readEntity<ExternalId>("api_test_result/movie/external_ids.json")
        logger.debug("externalIds: {}", externalIds)
        assertEquals(expectedExternalIds, externalIds)
    }

    @Test
    @DisplayName("get_images")
    fun getImagesTest(){
        val images = TmdbApi.getInstanceApi().getMovieApi().getImages(842675)
        val expectedImages = readFile.readEntity<ImageData>("api_test_result/movie/images.json")
        logger.debug("images: {}", images)
        assertEquals(expectedImages, images)
    }

    @Test
    @DisplayName("get_keywords")
    fun getKeywordsTest(){
        val keywords = TmdbApi.getInstanceApi().getMovieApi().getKeywords(11)
        val expectedKeywords = readFile.readEntity<KeywordList>("api_test_result/movie/keywords.json")
        logger.debug("keywords: {}", keywords)
        assertEquals(expectedKeywords, keywords)
    }

    @Test
    @DisplayName("get_latest")
    fun getLatestTest(){
        val latest = TmdbApi.getInstanceApi().getMovieApi().getLatest()
        val expectedLatest = readFile.readEntity<Latest>("api_test_result/movie/latest.json")
        logger.debug("latest: {}", latest)
        assertEquals(expectedLatest, latest)
    }


    @Test
    @DisplayName("get_movie_lists")
    fun getMovieListsTest(){
        val movieLists = TmdbApi.getInstanceApi().getMovieApi().getList(11,1)
        val expectedMovieLists = readFile.readEntity<ListResult>("api_test_result/movie/lists.json")
        logger.debug("movieLists: {}", movieLists)
        assertEquals(expectedMovieLists, movieLists)
    }

    @Test
    @DisplayName("get_release_dates")
    fun getReleaseDatesTest(){
        val releaseDates = TmdbApi.getInstanceApi().getMovieApi().getReleaseDates(11)
        val expectedReleaseDates = readFile.readEntity<ReleaseDate>("api_test_result/movie/release_dates.json")
        logger.debug("releaseDates: {}", releaseDates)
        assertEquals(expectedReleaseDates, releaseDates)
    }

    @Test
    @DisplayName("get_reviews")
    fun getReviewsTest(){
        val reviews = TmdbApi.getInstanceApi().getMovieApi().getReviews(842675,1)
        val expectedReviews = readFile.readEntity<Reviews>("api_test_result/movie/reviews.json")
        logger.debug("reviews: {}", reviews)
        assertEquals(expectedReviews, reviews,"评论值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_similar_movies")
    fun getSimilarMoviesTest(){
        val similarMovies = TmdbApi.getInstanceApi().getMovieApi().getSimilarMovies(842675,1)
        val expectedSimilarMovies = readFile.readEntity<SimilarResult>("api_test_result/movie/similar_movies.json")
        logger.debug("similarMovies: {}", similarMovies)
        assertEquals(expectedSimilarMovies, similarMovies,"相似电影值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_translations")
    fun getTranslationsTest(){
        val translations = TmdbApi.getInstanceApi().getMovieApi().getTranslations(11)
        val expectedTranslations = readFile.readEntity<TranslationsResult>("api_test_result/movie/translations.json")
        logger.debug("translations: {}", translations)
        assertEquals(expectedTranslations, translations,"获取翻译值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_videos")
    fun getVideosTest(){
        val videos = TmdbApi.getInstanceApi().getMovieApi().getVideos(842675)
        val expectedVideo = readFile.readEntity<VideoResult>("api_test_result/movie/videos.json")
        logger.debug("videos: {}", videos)
        assertEquals(expectedVideo, videos,"视频值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_watch_providers")
    fun getWatchProvidersTest(){
        val watchProviders = TmdbApi.getInstanceApi().getMovieApi().getWatchProviders(842675)
        val expectedWatchProviders = readFile.readEntity<WatchProvider>("api_test_result/movie/watch_providers.json")
        logger.debug("watchProviders: {}", watchProviders)
        assertEquals(expectedWatchProviders, watchProviders,"获取watchProviders值实际值与预期值不相等")
    }

    @Test
    @DisplayName("add_rating")
    fun addRatingTest() {
        val ratingResult = TmdbApi.getInstanceApi().getMovieApi().addRating(11, 8.0f)
        val expectedRatingResult = readFile.readEntity<RatingResult>("api_test_result/movie/rating.json")
        logger.debug("ratingResult: {}", ratingResult)
        assertEquals(expectedRatingResult, ratingResult, "评分结果值实际值与预期值不相等")
    }

    @Test
    @DisplayName("delete_rating")
    fun deleteRatingTest(){
        val deleteRatingResult = TmdbApi.getInstanceApi().getMovieApi().deleteRating(11)
        val expectedRatingResult = readFile.readEntity<RatingResult>("api_test_result/movie/delete_rating.json")
        logger.debug("deleteRatingResult: {}", deleteRatingResult)
        assertEquals(expectedRatingResult, deleteRatingResult,"删除评分结果值实际值与预期值不相等")
    }
}