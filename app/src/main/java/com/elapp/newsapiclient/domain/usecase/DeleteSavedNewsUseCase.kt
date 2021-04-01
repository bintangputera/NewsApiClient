package com.elapp.newsapiclient.domain.usecase

import com.elapp.newsapiclient.data.model.news.Article
import com.elapp.newsapiclient.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) {
        newsRepository.deleteNews(article)
    }
}