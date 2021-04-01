package com.elapp.newsapiclient.data.api

import com.elapp.newsapiclient.BuildConfig
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {

    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueeMockResponse(
        fileName: String
    ) {
        val inpuSystem = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inpuSystem.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueeMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines(BuildConfig.API_KEY,"us", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?apiKey=4eccbe1419b943438af7088e929344ba&country=us&page=1")
        }
    }

    @Test
    fun getTopHeadLines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueeMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines(BuildConfig.API_KEY, "us", 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueeMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines(BuildConfig.API_KEY, "us", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]
            assertThat(article.author).isEqualTo("Alex Seitz-Wald")
            assertThat(article.url).isEqualTo("https://www.nbcnews.com/politics/elections/polls-close-louisiana-special-congressional-elections-n1261680")
            assertThat(article.publishedAt).isEqualTo("2021-03-21T01:01:00Z")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}