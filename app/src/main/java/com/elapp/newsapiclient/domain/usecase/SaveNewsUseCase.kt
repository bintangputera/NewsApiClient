package com.elapp.newsapiclient.domain.usecase

import com.elapp.newsapiclient.data.model.news.Article
import com.elapp.newsapiclient.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) {
        newsRepository.saveNews(article)
    }

}