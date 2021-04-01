package com.elapp.newsapiclient.data.repository.datasource

import com.elapp.newsapiclient.data.model.news.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(apiKey: String, country: String, page: Int): Response<APIResponse>
    suspend fun getSearchedNews(apiKey: String, country: String, searchQuery: String, page: Int): Response<APIResponse>
}