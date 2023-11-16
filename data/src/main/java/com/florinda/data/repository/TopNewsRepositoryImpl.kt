package com.florinda.data.repository

import com.florinda.common.makeSafeRequest
import com.florinda.data.remote.NewsApiService
import com.florinda.domain.repository.TopNewsRepository
import com.florinda.common.Result
import com.florinda.domain.model.TopNewsResponse

class TopNewsRepositoryImpl constructor (
    private val apiService: NewsApiService
): TopNewsRepository {

    override suspend fun getArticles(): Result<TopNewsResponse> {
        return makeSafeRequest {
            apiService.getArticles(query = "us")
        }
    }


    override suspend fun getSearchArticles(query: String): Result<TopNewsResponse> {
        return makeSafeRequest {
            apiService.getArticles(query)
        }
    }


}