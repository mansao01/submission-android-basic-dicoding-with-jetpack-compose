package com.example.genshinapp.ui.navigation

sealed class Screen(val route:String) {
    object Home:Screen("home")

    object Profile:Screen("home/profile"){
        fun createRoute()="home/profile"
    }
    object Detail:Screen("home/{characterId}"){
        fun createRoute(characterId:Int) = "home/$characterId"
    }
}