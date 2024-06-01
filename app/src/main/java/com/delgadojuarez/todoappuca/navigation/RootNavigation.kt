package com.delgadojuarez.todoappuca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.todoappuca.TasksViewmodel
import com.delgadojuarez.todoappuca.screens.MainScreen
import com.delgadojuarez.todoappuca.screens.DetailsTaskScreen
import com.delgadojuarez.todoappuca.ui.theme.TodoAppUcaTheme

sealed class GRAPH(val graph: String){
    data object MAIN: GRAPH(graph = "MAIN")
}
sealed class MainScreens(val route: String) {
    data object MainScreen: MainScreens(route = "main_screen")
    data object DetailsTaskScreen: MainScreens(route = "details_screen")
}

@Composable
fun RootNavigation(
    viewModel: TasksViewmodel
) {
    val navController = rememberNavController()
     TodoAppUcaTheme{
        NavHost(
            navController = navController,
            route = GRAPH.MAIN.graph,
            startDestination = MainScreens.MainScreen.route
        ) {
            composable(MainScreens.MainScreen.route) {
                MainScreen(
                    viewModel = viewModel,
                    onClick = { navController.navigate(MainScreens.DetailsScreen.route) }
                )
            }

            composable(MainScreens.DetailsTaskScreen.route) {
                DetailsTaskScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}