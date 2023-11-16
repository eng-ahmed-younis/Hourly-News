package com.florinda.hourlynews.ui.screen.viewmodel

import android.util.Log
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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getSearchArticlesUseCase: GetSearchArticlesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {


    private val _mainState = MutableStateFlow(NewsUiState())
    val mainState = _mainState.asStateFlow()


    private val _selectedNews: MutableStateFlow<TopNewsArticle?> = MutableStateFlow(null)
    val selectedNews: StateFlow<TopNewsArticle?> = _selectedNews.asStateFlow()

    val searchQuery: MutableState<String> = mutableStateOf("")




    init {
        getArticles()
    }



     fun getArticles(){
        _mainState.update {it.copy(isLoading = true)}
        viewModelScope.launch (dispatcher){
            getArticlesUseCase().collect{
                it.fold(
                    onSuccess = {
                        Log.i("mainStatemainState",it.articles?.size.toString())

                        _mainState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                articles = it.articles ?: emptyList(),
                                error = null
                            )
                        }
                    },
                    onFailure = {
                        _mainState.update { currentState->
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
        _mainState.update { currentState -> currentState.copy(isLoading = true) }
        viewModelScope.launch(dispatcher) {
            getSearchArticlesUseCase(query).collect{
                it.fold(
                    onSuccess = {
                        _mainState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                articles = it.articles ?: emptyList(),
                                error = null
                            )
                        }
                    },
                    onFailure = {
                        _mainState.update { currentState ->
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
            _selectedNews.value = _mainState.value.articles[index]
        }
    }

    fun updateQuery(query: String) {
        searchQuery.value = query
    }


}