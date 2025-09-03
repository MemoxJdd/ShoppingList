package com.jdd.shoppinglist.ui

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.ui.screens.ShoppingListScreen
import com.jdd.shoppinglist.ui.screens.ShoppingListDetailScreen
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    var shoppingLists by remember {
        mutableStateOf(
            listOf(
                ShoppingList(id = 1, name = "Market", createdAt = System.currentTimeMillis()),
                ShoppingList(id = 2, name = "Bakkal", createdAt = System.currentTimeMillis())
            )
        )
    }
    // Eğer item'lar da yönetilecekse:
    var itemsMap by remember { mutableStateOf(mapOf<Int, List<ShoppingItem>>()) }

    NavHost(navController = navController, startDestination = "lists") {
        composable("lists") {
            ShoppingListScreen(
                navController = navController,
                shoppingLists = shoppingLists,
                onDelete = { list -> shoppingLists = shoppingLists - list },
                onArchive = { /* arşivleme işlemi */ },
                onAddList = {
                    val newId = (shoppingLists.maxOfOrNull { it.id } ?: 0) + 1
                    shoppingLists = shoppingLists + ShoppingList(
                        id = newId,
                        name = "Yeni Liste $newId",
                        createdAt = System.currentTimeMillis()
                    )
                },
                modifier = Modifier
            )
        }
        // detail/{listId} gibi diğer ekranlar burada
    }
}