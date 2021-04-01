package com.elapp.newsapiclient.domain.usecase

import com.elapp.newsapiclient.data.model.news.APIResponse
import com.elapp.newsapiclient.data.utils.Resource
import com.elapp.newsapiclient.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(apiKey: String, country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(apiKey, country, page)
    }

}