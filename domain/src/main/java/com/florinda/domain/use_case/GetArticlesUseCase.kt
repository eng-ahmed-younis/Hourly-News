package com.florinda.domain.use_case

import com.florinda.common.Result
import com.florinda.domain.model.TopNewsResponse
import com.florinda.domain.repository.TopNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArticlesUseCase constructor(
    private val repository: TopNewsRepository,

){
    operator fun invoke() : Flow<Result<TopNewsResponse>> = flow {
        emit(repository.getArticles())
    }
}