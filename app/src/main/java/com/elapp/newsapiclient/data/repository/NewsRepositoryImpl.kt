package com.elapp.newsapiclient.data.repository

import com.elapp.newsapiclient.data.model.news.APIResponse
import com.elapp.newsapiclient.data.model.news.Article
import com.elapp.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import com.elapp.newsapiclient.data.utils.Resource
import com.elapp.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsHeadlines(apiKey: String, country: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(apiKey, country, page))
    }

    override suspend fun getSearchedNews(
        apiKey: String,
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews(apiKey, country, searchQuery, page))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse>{
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}