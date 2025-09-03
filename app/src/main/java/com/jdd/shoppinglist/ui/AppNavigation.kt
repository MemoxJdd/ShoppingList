package com.jdd.shoppinglist.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.ui.screens.ArchivedListsScreen
import com.jdd.shoppinglist.ui.screens.ShoppingListDetailScreenContainer
import com.jdd.shoppinglist.ui.screens.ShoppingListScreen
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel
@Composable
fun AppNavigation(
    viewModel: ShoppingViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val shoppingLists by viewModel.shoppingLists.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var newListName by remember { mutableStateOf("") }
    val itemsMap by viewModel.itemsMap.collectAsState()
    val archivedLists by viewModel.archivedLists.collectAsState() // <-- Doğru kullanım!
    // ...
    NavHost(
        navController = navController,
        startDestination = "lists",
        modifier = modifier
    ) {
        composable("archived") {
            ArchivedListsScreen(
                archivedLists = archivedLists,
                onDelete = { viewModel.deleteList(it) }
            )
        }
        composable("lists") {
            ShoppingListScreen(
                navController = navController,
                shoppingLists = shoppingLists,
                onDelete = { list -> viewModel.deleteList(list) },
                onArchive = { list -> viewModel.archiveList(list) },
                onAddList = { showAddDialog = true }
            )
            // Liste adı soran dialog
            if (showAddDialog) {
                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { Text("Yeni Liste Oluştur") },
                    text = {
                        OutlinedTextField(
                            value = newListName,
                            onValueChange = { newListName = it },
                            label = { Text("Liste Adı") }
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            if (newListName.isNotBlank()) {
                                viewModel.addList(newListName)
                                newListName = ""
                                showAddDialog = false
                            }
                        }) {
                            Text("Oluştur")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showAddDialog = false }) {
                            Text("İptal")
                        }
                    }
                )
            }
        }
        composable("detail/{listId}") { backStackEntry ->
            val listId = backStackEntry.arguments?.getString("listId")?.toIntOrNull()
            val currentList = shoppingLists.find { it.id == listId }
            if (currentList != null && listId != null) {
                ShoppingListDetailScreenContainer(
                    shoppingList = currentList,
                    viewModel = viewModel
                )
            }
        }

    }
}