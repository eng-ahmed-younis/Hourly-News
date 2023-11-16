package com.florinda.hourlynews.di

import com.florinda.domain.repository.TopNewsRepository
import com.florinda.domain.use_case.GetArticlesUseCase
import com.florinda.domain.use_case.GetSearchArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    @Singleton
    fun provideGetArticlesUseCase(
        newsRepository: TopNewsRepository
    ) : GetArticlesUseCase {
        return GetArticlesUseCase(repository = newsRepository)
    }


    @Provides
    @Singleton
    fun provideGetSearchArticlesUseCase(
        newsRepository: TopNewsRepository
    ) : GetSearchArticlesUseCase {
        return GetSearchArticlesUseCase(repository = newsRepository)
    }





}