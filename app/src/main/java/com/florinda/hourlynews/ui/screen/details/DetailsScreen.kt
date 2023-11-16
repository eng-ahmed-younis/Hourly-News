package com.florinda.hourlynews.ui.screen.details

import android.annotation.SuppressLint
import android.text.Layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.florinda.hourlynews.R
import com.florinda.hourlynews.ui.screen.viewmodel.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    onBackPressed: () -> Unit,
    mainViewModel: MainViewModel,
    index: Int?
) {
    LaunchedEffect(key1 = true) {
        mainViewModel.getSelectedArticle(index)
    }

    val article by mainViewModel.selectedNews.collectAsState()

    Scaffold(
        topBar = {
            DetailTopBar(onBackPressed = { onBackPressed() })
        }
    ) {
        article?.let { article ->
            DetailContent(article = article)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.detail_screen_title),
                textAlign = TextAlign.Center
            )
                },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        })
}

@Composable
fun InfoWithIcon(modifier: Modifier = Modifier, icon: ImageVector, info: String) {
    Row(modifier = modifier) {
        Icon(
            icon,
            contentDescription = stringResource(id = R.string.author),
            modifier = Modifier
                .padding(end = 8.dp),
            colorResource(id = R.color.purple_500)
        )
        Text(text = info, modifier = Modifier.testTag("TextInfoIcon"))
    }
}