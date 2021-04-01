package com.elapp.newsapiclient.data.api

import com.elapp.newsapiclient.BuildConfig
import com.elapp.newsapiclient.data.model.news.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey")
        apiKey: String,
        @Query("country")
        country: String,
        @Query("page")
        page: Int
    ): Response<APIResponse>

    @GET("/v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("apiKey")
        apiKey: String,
        @Query("country")
        country: String,
        @Query("q")
        keyword: String,
        @Query("page")
        page: Int
    ): Response<APIResponse>

}