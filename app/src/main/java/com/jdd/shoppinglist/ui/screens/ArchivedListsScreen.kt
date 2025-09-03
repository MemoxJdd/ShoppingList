package com.jdd.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType.Companion.Date
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jdd.shoppinglist.ui.screens.ArchivedListsScreen
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.model.ShoppingList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ArchivedListsScreen(
    archivedLists: List<ShoppingList>,
    onDelete: (ShoppingList) -> Unit
) {
    val totalSum = archivedLists.sumOf { it.totalAmount }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Arşivlenen Listeler",
            style = MaterialTheme.typography.body2, // Daha küçük başlık
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(vertical = 6.dp), // daha dar padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tarih", modifier = Modifier.weight(2f), style = MaterialTheme.typography.caption, fontWeight = FontWeight.Bold)
            Text("Liste Adı", modifier = Modifier.weight(3f), style = MaterialTheme.typography.caption, fontWeight = FontWeight.Bold)
            Text("Toplam Tutar", modifier = Modifier.weight(2f), style = MaterialTheme.typography.caption, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
        }
        Divider()
        LazyColumn {
            items(archivedLists, key = { it.id }) { list ->
                val formattedDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    .format(Date(list.createdAt))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp), // daha dar padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(formattedDate, modifier = Modifier.weight(2f), style = MaterialTheme.typography.caption)
                    Text(list.name, modifier = Modifier.weight(3f), style = MaterialTheme.typography.caption)
                    Text(
                        text = "₺${"%.2f".format(list.totalAmount)}",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF388E3C)
                    )
                    IconButton(
                        onClick = { onDelete(list) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Listeyi Sil")
                    }
                }
                Divider()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Alt Toplam: ₺${"%.2f".format(totalSum)}",
            style = MaterialTheme.typography.caption, // Alt toplam da küçük font
            modifier = Modifier.padding(16.dp)
        )
    }
}