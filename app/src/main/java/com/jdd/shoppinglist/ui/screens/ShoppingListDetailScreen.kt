package com.jdd.shoppinglist.ui.screens

import androidx.compose.ui.text.input.KeybordOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListDetailScreen(
    shoppingList: ShoppingList,
    viewModel: ShoppingViewModel
) {
    val items by viewModel.getItemsForList(shoppingList.id).observeAsState(emptyList())
    var itemName by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(shoppingList.name) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = itemName,
                onValueChange = {
                    itemName = it
                    showError = false
                },
                label = { Text("Ürün Adı") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = showError && itemName.isBlank()
            )
            OutlinedTextField(
                value = priceText,
                onValueChange = { priceText = it },
                label = { Text("Fiyat") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                keyboardOptions =androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = quantityText,
                onValueChange = { quantityText = it },
                label = { Text("Miktar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (showError) {
                Text(
                    text = "Tüm alanları doldurun!",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Button(
                onClick = {
                    val price = priceText.toDoubleOrNull()
                    val quantity = quantityText.toIntOrNull()
                    if (itemName.isBlank() || price == null || quantity == null) {
                        showError = true
                    } else {
                        viewModel.insertItem(
                            ShoppingItem(
                                name = itemName,
                                price = price,
                                quantity = quantity,
                                listId = shoppingList.id
                            )
                        )
                        itemName = ""
                        priceText = ""
                        quantityText = ""
                        showError = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text("Ürün Ekle")
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { item ->
                    ShoppingItemRow(
                        item = item,
                        onDelete = { viewModel.deleteItem(it) }
                    )
                }
            }
        }
    }
}