package com.florinda.hourlynews.di

import com.florinda.data.remote.NewsApiService
import com.florinda.data.repository.TopNewsRepositoryImpl
import com.florinda.domain.repository.TopNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTopNewsRepository(
        apiService: NewsApiService
    ) : TopNewsRepository  = TopNewsRepositoryImpl(apiService = apiService)

}