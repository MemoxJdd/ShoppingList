package com.jdd.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.model.ShoppingList

@Composable
fun ShoppingListDetailScreen(
    shoppingList: ShoppingList,
    items: List<ShoppingItem>,
    onAddItem: (String, Double, Double) -> Unit,
    onDeleteItem: (ShoppingItem) -> Unit
) {
    var itemName by rememberSaveable { mutableStateOf("") }
    var quantityText by rememberSaveable { mutableStateOf("") }
    var priceText by rememberSaveable { mutableStateOf("") }

    val totalSum = items.sumOf { it.quantity * it.price }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = shoppingList.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Ürün", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold)
            Text("Miktar", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Fiyat", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Tutar", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(32.dp))
        }

        Divider()

        // Item Rows
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(item.name, modifier = Modifier.weight(2f))
                Text("${item.quantity}", modifier = Modifier.weight(1f))
                Text("₺${item.price}", modifier = Modifier.weight(1f))
                Text("₺${"%.2f".format(item.quantity * item.price)}", modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onDeleteItem(item) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Sil")
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp), thickness = 2.dp)

        // Total Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(4f))
            Text(
                text = "Toplam: ₺${"%.2f".format(totalSum)}",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add Item Section
        Text(
            text = "Yeni Ürün Ekle",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Ürün Adı") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = quantityText,
            onValueChange = { quantityText = it },
            label = { Text("Miktar") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = priceText,
            onValueChange = { priceText = it },
            label = { Text("Fiyat") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Button(
            onClick = {
                val quantity = quantityText.toDoubleOrNull() ?: 0.0
                val price = priceText.toDoubleOrNull() ?: 0.0
                if (itemName.isNotBlank() && quantity > 0 && price > 0) {
                    onAddItem(itemName, quantity, price)
                    itemName = ""
                    quantityText = ""
                    priceText = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ürün Ekle")
        }
    }
}