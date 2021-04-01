package com.elapp.newsapiclient.presentation.di

import com.elapp.newsapiclient.data.api.NewsAPIService
import com.elapp.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import com.elapp.newsapiclient.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}