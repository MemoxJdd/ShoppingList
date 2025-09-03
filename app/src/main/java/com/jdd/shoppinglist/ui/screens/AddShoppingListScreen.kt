package com.jdd.shoppinglist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.materialcore.Text
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel
import java.util.*

@Composable
fun AddShoppingListScreen(
    viewModel: ShoppingViewModel,
    onListAdded: () -> Unit,
    onAddListClick: () -> Unit // <-- EKLENEN PARAMETRE
) {
    var listName by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val renk = MaterialTheme.colors.primary
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Yeni Alışveriş Listesi Ekle",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = listName,
            onValueChange = {
                listName = it
                showError = false
            },
            label = { Text("Liste Adı") },
            isError = showError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showError) {
            Text(
                text = "Liste adı boş olamaz!",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (listName.isBlank()) {
                    showError = true
                } else {
                    val newList = ShoppingList(
                        name = listName,
                        createdAt = Date().time
                    )
                    viewModel.addList(listName)
                    onListAdded()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ekle")
        }
    }
}