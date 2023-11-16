package com.florinda.hourlynews.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.common.toError
import com.florinda.domain.model.TopNewsArticle
import com.florinda.hourlynews.navigation.Screen
import com.florinda.hourlynews.ui.component.ErrorScreen
import com.florinda.hourlynews.ui.component.LoadingScreen
import com.florinda.hourlynews.ui.screen.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val mainState by viewModel.mainState.collectAsState()
    val articles = mainState.articles

    val query by viewModel.searchQuery

    if (mainState.isLoading) {
        LoadingScreen()
    }

    mainState.error?.let {
        ErrorScreen(error = it.toError())
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "ColumnArticlesHome"
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            query = query,
            onQueryChange = {
                viewModel.updateQuery(it)

                if (it.isEmpty())
                    viewModel.getArticles()
            },
            onSearch = {
                viewModel.getSearchArticles(it)
            },
            active = true, onActiveChange = {},
            placeholder = { Text(text = "Search News") },
            tonalElevation = 0.dp,
        ) {


            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(articles.count()) {
                    TopNewsItem(
                        article = articles[it],
                        onItemClick = {
                            navController.navigate(Screen.Detail.passNewsIndex(it))
                        }
                    )

                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    HomeScreen(rememberNavController(), viewModel())
}
