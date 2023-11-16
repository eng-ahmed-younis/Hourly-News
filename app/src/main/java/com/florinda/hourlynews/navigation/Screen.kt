package com.florinda.hourlynews.navigation

sealed class Screen(val route : String){
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen/{index}") {
        const val NAV_ARG_KEY = "index"
        fun passNewsIndex(index: Int): String {
            return "detail_screen/${index}"
        }
    }
}

