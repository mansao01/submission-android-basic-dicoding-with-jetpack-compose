package com.example.genshinapp.ui.navigation

sealed class Screen(val route:String) {
    object Home:Screen("home")
    object Detail:Screen("home/{characterId}"){
        fun createRoute(characterId:Int) = "home/$characterId"
    }
}