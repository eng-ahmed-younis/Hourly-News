package com.florinda.domain.repository

import com.florinda.common.Result
import com.florinda.domain.model.TopNewsResponse


interface TopNewsRepository {
    suspend fun getArticles(): Result<TopNewsResponse>
    suspend fun getSearchArticles(query: String): Result<TopNewsResponse>
}