package com.florinda.hourlynews.ui.screen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.common.fold
import com.florinda.domain.model.TopNewsArticle
import com.florinda.domain.use_case.GetArticlesUseCase
import com.florinda.domain.use_case.GetSearchArticlesUseCase
import com.florinda.hourlynews.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getSearchArticlesUseCase: GetSearchArticlesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

     var mainState = MutableStateFlow(NewsUiState())
         private set

     var selectedNews:MutableStateFlow<TopNewsArticle?> = MutableStateFlow(null)
         private set

    val searchQuery: MutableState<String> = mutableStateOf("")


    init {
        getArticles()
    }


    fun getArticles() {
        mainState.update { it.copy(isLoading = true) }
        viewModelScope.launch(dispatcher) {
            getArticlesUseCase().collect {
                it.fold(
                    onSuccess = {
                        mainState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                articles = it.articles ?: emptyList(),
                                error = null
                            )
                        }
                    },
                    onFailure = {
                        mainState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = it
                            )
                        }
                    }
                )
            }
        }
    }

    fun getSearchArticles(query: String) {
        mainState.update { currentState -> currentState.copy(isLoading = true) }
        viewModelScope.launch(dispatcher) {
            getSearchArticlesUseCase(query).collect {
                it.fold(
                    onSuccess = {
                        mainState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                articles = it.articles ?: emptyList(),
                                error = null
                            )
                        }
                    },
                    onFailure = {
                        mainState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = it
                            )
                        }
                    }
                )
            }

        }
    }


    fun getSelectedArticle(index: Int?) {
        index?.let {
            selectedNews.value = mainState.value.articles[index]
        }
    }

    fun updateQuery(query: String) {
        searchQuery.value = query
    }


}