package com.florinda.hourlynews.ui.screen.viewmodel
import com.florinda.domain.model.TopNewsArticle

data class NewsUiState(
    val isLoading: Boolean = false,
    val articles: List<TopNewsArticle> = emptyList(),
    val error: Exception? = null,
)