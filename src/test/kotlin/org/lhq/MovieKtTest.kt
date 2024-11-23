package org.lhq

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
        val endDate = LocalDate.parse("2024-10-29")
        val startDate = LocalDate.parse("2024-10-28")
        val recentChangesMovie = TmdbApi(tmdbConfig)
            .getMovieApi()
            .getRecentChangesMovie(912649, 1, startDate, endDate)
        val expectedChangesList = readFile.readEntity<ChangeList>("api_test_result/movie/changes.json")
        logger.debug("recentChangesMovie: {}", recentChangesMovie)
        assertEquals(expectedChangesList, recentChangesMovie)
    }

    @Test
    @DisplayName("get_credits")
    fun getCreditsTest(){
        val credits = TmdbApi(tmdbConfig).getMovieApi().getCredits(11)
        val expectedCredits = readFile.readEntity<Credits>("api_test_result/movie/credits.json")
        logger.debug("credits: {}", credits)
        assertEquals(expectedCredits, credits)
    }

    @Test
    @DisplayName("get_external_ids")
    fun getExternalIdsTest(){
        val externalIds = TmdbApi(tmdbConfig).getMovieApi().getExternalIds(11)
        val expectedExternalIds = readFile.readEntity<ExternalId>("api_test_result/movie/external_ids.json")
        logger.debug("externalIds: {}", externalIds)
        assertEquals(expectedExternalIds, externalIds)
    }

    @Test
    @DisplayName("get_images")
    fun getImagesTest(){
        val images = TmdbApi(tmdbConfig).getMovieApi().getImages(842675)
        val expectedImages = readFile.readEntity<ImageData>("api_test_result/movie/images.json")
        logger.debug("images: {}", images)
        assertEquals(expectedImages, images)
    }

    @Test
    @DisplayName("get_keywords")
    fun getKeywordsTest(){
        val keywords = TmdbApi(tmdbConfig).getMovieApi().getKeywords(11)
        val expectedKeywords = readFile.readEntity<KeywordList>("api_test_result/movie/keywords.json")
        logger.debug("keywords: {}", keywords)
        assertEquals(expectedKeywords, keywords)
    }

    @Test
    @DisplayName("get_latest")
    fun getLatestTest(){
        val latest = TmdbApi(tmdbConfig).getMovieApi().getLatest()
        val expectedLatest = readFile.readEntity<Latest>("api_test_result/movie/latest.json")
        logger.debug("latest: {}", latest)
        assertEquals(expectedLatest, latest)
    }


    @Test
    @DisplayName("get_movie_lists")
    fun getMovieListsTest(){
        val movieLists = TmdbApi(tmdbConfig).getMovieApi().getList(11,1)
        val expectedMovieLists = readFile.readEntity<ListResult>("api_test_result/movie/lists.json")
        logger.debug("movieLists: {}", movieLists)
        assertEquals(expectedMovieLists, movieLists)
    }

    @Test
    @DisplayName("get_release_dates")
    fun getReleaseDatesTest(){
        val releaseDates = TmdbApi(tmdbConfig).getMovieApi().getReleaseDates(11)
        val expectedReleaseDates = readFile.readEntity<ReleaseDate>("api_test_result/movie/release_dates.json")
        logger.debug("releaseDates: {}", releaseDates)
        assertEquals(expectedReleaseDates, releaseDates)
    }

    @Test
    @DisplayName("get_reviews")
    fun getReviewsTest(){
        val reviews = TmdbApi(tmdbConfig).getMovieApi().getReviews(842675,1)
        val expectedReviews = readFile.readEntity<Reviews>("api_test_result/movie/reviews.json")
        logger.debug("reviews: {}", reviews)
        assertEquals(expectedReviews, reviews,"评论值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_similar_movies")
    fun getSimilarMoviesTest(){
        val similarMovies = TmdbApi(tmdbConfig).getMovieApi().getSimilarMovies(842675,1)
        val expectedSimilarMovies = readFile.readEntity<SimilarResult>("api_test_result/movie/similar_movies.json")
        logger.debug("similarMovies: {}", similarMovies)
        assertEquals(expectedSimilarMovies, similarMovies,"相似电影值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_translations")
    fun getTranslationsTest(){
        val translations = TmdbApi(tmdbConfig).getMovieApi().getTranslations(11)
        val expectedTranslations = readFile.readEntity<TranslationsResult>("api_test_result/movie/translations.json")
        logger.debug("translations: {}", translations)
        assertEquals(expectedTranslations, translations,"获取翻译值实际值与预期值不相等")
    }

    @Test
    @DisplayName("get_videos")
    fun getVideosTest(){
        val videos = TmdbApi(tmdbConfig).getMovieApi().getVideos(842675)
        val expectedVideo = readFile.readEntity<VideoResult>("api_test_result/movie/videos.json")
        logger.debug("videos: {}", videos)
        assertEquals(expectedVideo, videos,"视频值实际值与预期值不相等")
    }

    @Test
    @DisplayName("add_rating")
    fun addRatingTest() {
        val ratingResult = TmdbApi(tmdbConfig).getMovieApi().addRating(11, 8.0f)
        val expectedRatingResult = readFile.readEntity<RatingResult>("api_test_result/movie/rating.json")
        logger.debug("ratingResult: {}", ratingResult)
        assertEquals(expectedRatingResult, ratingResult)
    }
}