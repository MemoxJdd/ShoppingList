package com.jdd.shoppinglist.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController
import com.jdd.shoppinglist.Data.model.ShoppingList

@Composable
fun ListsScreen(
    navController: NavController
) {
    var shoppingLists by remember { mutableStateOf(
        listOf(
            ShoppingList(id = 1, name = "Market", createdAt = System.currentTimeMillis()),
            ShoppingList(id = 2, name = "Bakkal", createdAt = System.currentTimeMillis())
        )
    ) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val newId = (shoppingLists.maxOfOrNull { it.id } ?: 0) + 1
                shoppingLists = shoppingLists + ShoppingList(
                    id = newId,
                    name = "Yeni Liste $newId",
                    createdAt = System.currentTimeMillis()
                )
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Liste Ekle")
            }
        }
    ) { paddingValues ->
        ShoppingListScreen(
            navController = navController,
            shoppingLists = shoppingLists,
            onDelete = { /* silme işlemi */ },
            onArchive = { /* arşivleme işlemi */ },
            onAddList = { /* yeni liste ekleme işlemi */ },
            modifier = Modifier.padding(paddingValues)
        )
    }
}