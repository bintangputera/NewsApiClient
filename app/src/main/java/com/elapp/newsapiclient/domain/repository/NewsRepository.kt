package com.elapp.newsapiclient.domain.repository

import com.elapp.newsapiclient.data.model.news.APIResponse
import com.elapp.newsapiclient.data.model.news.Article
import com.elapp.newsapiclient.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(apiKey: String, country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(apiKey: String, country: String, searchQuery: String, page: Int): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>

}