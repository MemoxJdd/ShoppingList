package com.jdd.shoppinglist.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jdd.shoppinglist.Data.model.ShoppingItem

@Composable
fun ShoppingItemRow(
    item: ShoppingItem,
    onDelete: (ShoppingItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(item.name)
        Text("Miktar: ${item.quantity}  Fiyat: ₺${item.price}")
        if (item.price > 0.0 && item.quantity > 0.0) {
            Text("Tutar: ₺${"%.2f".format(item.quantity * item.price)}")
        } else {
            Text("Bekleniyor")
        }
        IconButton(onClick = { onDelete(item) }) {
            Icon(Icons.Default.Delete, contentDescription = "Sil")
        }
    }
}