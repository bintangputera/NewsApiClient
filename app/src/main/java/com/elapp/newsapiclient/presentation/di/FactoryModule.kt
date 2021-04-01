package com.elapp.newsapiclient.presentation.di

import android.app.Application
import com.elapp.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.elapp.newsapiclient.domain.usecase.GetSearchedNewsUseCase
import com.elapp.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        app:Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(app, getNewsHeadlinesUseCase, getSearchedNewsUseCase)
    }

}