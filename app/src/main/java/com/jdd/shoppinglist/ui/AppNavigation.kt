package com.jdd.shoppinglist.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.ui.screens.ShoppingListScreen
import com.jdd.shoppinglist.ui.screens.ShoppingListDetailScreen
import com.jdd.shoppinglist.ui.screens.AddShoppingListScreen
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel

@Composable
fun AppNavigation(viewModel: ShoppingViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lists") {
        composable("lists") {
            ShoppingListScreen(
                viewModel = viewModel,
                onListClick = { list ->
                    navController.navigate("detail/${list.id}")
                },
                onAddListClick = {
                    navController.navigate("add")
                }
            )
        }
        composable(
            route = "detail/{listId}",
            arguments = listOf(navArgument("listId") { type = NavType.IntType })
        ) { backStackEntry ->
            val listId = backStackEntry.arguments?.getInt("listId") ?: return@composable
            val list = viewModel.shoppingLists.value?.find { it.id == listId }
            if (list != null) {
                ShoppingListDetailScreen(
                    shoppingList = list,
                    viewModel = viewModel
                )
            }
        }
        composable("add") {
            AddShoppingListScreen(
                viewModel = viewModel,
                onListAdded = {
                    navController.popBackStack()
                }
            )
        }
    }
}