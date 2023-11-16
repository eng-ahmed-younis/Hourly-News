package com.florinda.domain.model

import com.florinda.domain.model.TopNewsArticle


data class TopNewsResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<TopNewsArticle>? = null
)