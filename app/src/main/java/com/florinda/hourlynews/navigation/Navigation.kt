package com.florinda.hourlynews.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.florinda.hourlynews.ui.screen.details.DetailScreen
import com.florinda.hourlynews.ui.screen.home.HomeScreen
import com.florinda.hourlynews.ui.screen.viewmodel.MainViewModel


@Composable
fun Navigation (
    navController: NavHostController,
    viewModel: MainViewModel
){
    NavHost(
        navController = navController ,
        startDestination =  Screen.Home.route,
    ){
        buildNavGraph(navController , viewModel)
    }

}



fun NavGraphBuilder.buildNavGraph(
    navController: NavController,
    viewModel: MainViewModel
) {

    composable(Screen.Home.route){
        HomeScreen(navController = navController, viewModel = viewModel)
    }

    composable(
        Screen.Detail.route,
        arguments = listOf(
            navArgument(Screen.Detail.NAV_ARG_KEY) {
                type = NavType.IntType
            },
        )
    ) { navBackStackEntry ->
        val index = navBackStackEntry.arguments?.getInt(Screen.Detail.NAV_ARG_KEY)
        DetailScreen(
            onBackPressed = { navController.popBackStack() },
            viewModel,
            index = index
        )
    }


}