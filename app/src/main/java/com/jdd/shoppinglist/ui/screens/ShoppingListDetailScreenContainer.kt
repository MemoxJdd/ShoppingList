package com.jdd.shoppinglist.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel

@Composable
fun ShoppingListDetailScreenContainer(
    shoppingList: ShoppingList,
    viewModel: ShoppingViewModel
) {
    val items by viewModel.itemsFlowForList(shoppingList.id).collectAsState(initial = emptyList())

    ShoppingListDetailScreen(
        shoppingList = shoppingList,
        items = items,
        onAddItem = { name, quantity, price ->
            viewModel.addItem(shoppingList.id, name, quantity, price)
        },
        onDeleteItem = { item ->
            // HATALI: viewModel.deleteItem(item)
            // DOĞRU:
            viewModel.deleteItem(shoppingList.id, item)
        }
    )
}