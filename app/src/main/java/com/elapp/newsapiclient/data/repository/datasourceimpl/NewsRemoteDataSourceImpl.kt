package com.elapp.newsapiclient.data.repository.datasourceimpl

import com.elapp.newsapiclient.BuildConfig
import com.elapp.newsapiclient.data.api.NewsAPIService
import com.elapp.newsapiclient.data.model.news.APIResponse
import com.elapp.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
) : NewsRemoteDataSource {

    override suspend fun getTopHeadlines(
        apiKey: String,
        country: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(BuildConfig.API_KEY, country, page)
    }

    override suspend fun getSearchedNews(
        apiKey: String,
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadlines(BuildConfig.API_KEY, country, searchQuery, page)
    }

}