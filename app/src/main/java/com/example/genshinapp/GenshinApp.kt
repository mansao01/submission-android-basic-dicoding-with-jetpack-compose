package com.example.genshinapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.genshinapp.ui.navigation.Screen
import com.example.genshinapp.ui.screen.detail.DetailScreen
import com.example.genshinapp.ui.screen.home.HomeScreen


@Composable
fun GenshinApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navigateToDetail = { characterId ->
                navController.navigate(Screen.Detail.createRoute(characterId))
            })
        }
        composable(Screen.Detail.route, arguments = listOf(navArgument("characterId") {
            type = NavType.IntType
        })) {
            val id = it.arguments?.getInt("characterId")?: -1
            DetailScreen(playerId = id, navigateBack = {
                navController.navigateUp()
            })
        }
    }

}