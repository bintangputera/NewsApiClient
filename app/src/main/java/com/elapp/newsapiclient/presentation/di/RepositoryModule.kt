package com.elapp.newsapiclient.presentation.di

import com.elapp.newsapiclient.data.repository.NewsRepositoryImpl
import com.elapp.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import com.elapp.newsapiclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }

}