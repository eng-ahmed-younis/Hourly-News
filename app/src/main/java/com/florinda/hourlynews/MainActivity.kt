package com.florinda.hourlynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.florinda.hourlynews.navigation.Navigation
import com.florinda.hourlynews.ui.screen.viewmodel.MainViewModel
import com.florinda.hourlynews.ui.theme.HourlyNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HourlyNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   NewsApp(mainViewModel = mainViewModel)
                }
            }
        }
    }
}

@Composable
fun NewsApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    Navigation(navController = navController, viewModel = mainViewModel)
}



@Preview(showBackground = true , showSystemUi = true)
@Composable
fun DefaultPreview() {
    HourlyNewsTheme {
        NewsApp(viewModel())
    }
}