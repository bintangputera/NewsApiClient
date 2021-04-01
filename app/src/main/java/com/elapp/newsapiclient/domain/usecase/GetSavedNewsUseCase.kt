package com.elapp.newsapiclient.domain.usecase

import com.elapp.newsapiclient.data.model.news.Article
import com.elapp.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }

}