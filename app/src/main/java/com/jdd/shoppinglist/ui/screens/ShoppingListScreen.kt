package com.jdd.shoppinglist.ui.screens
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel
import com.jdd.shoppinglist.utils.formatMillisToDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingViewModel,
    onListClick: (ShoppingList) -> Unit,
    onAddListClick: () -> Unit // <-- Parametre adı küçük harfle!
) {
    val shoppingLists by viewModel.shoppingLists.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alışveriş Listeleri") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddListClick() }) { // <-- Burada parametreyi kullandık!
                Icon(Icons.Default.Add, contentDescription = "Yeni Liste Ekle")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(shoppingLists) { list ->
                ShoppingListCard(list, onListClick)
            }
        }
    }
}

@Composable
fun ShoppingListCard(
    shoppingList: ShoppingList,
    onClick: (ShoppingList) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(shoppingList) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = shoppingList.name, style = MaterialTheme.typography.titleLarge)
            Text("Oluşturulma: ${formatMillisToDate(shoppingList.createdAt)}")
        }
    }
}