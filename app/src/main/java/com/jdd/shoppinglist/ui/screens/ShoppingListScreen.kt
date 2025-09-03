package com.jdd.shoppinglist.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.autofill.ContentDataType.Companion.Date
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jdd.shoppinglist.Data.model.ShoppingList
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShoppingListScreen(
    navController: NavController,
    shoppingLists: List<ShoppingList>,
    onDelete: (ShoppingList) -> Unit,
    onArchive: (ShoppingList) -> Unit,
    onAddList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddList) {
                Icon(Icons.Filled.Add, contentDescription = "Liste Ekle")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(shoppingLists, key = { it.id }) { list ->
                val dismissState = rememberDismissState(confirmStateChange = { value ->
                    when (value) {
                        DismissValue.DismissedToEnd -> { onArchive(list); false }
                        DismissValue.DismissedToStart -> { onDelete(list); false }
                        else -> false
                    }
                })
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(
                        DismissDirection.EndToStart,
                        DismissDirection.StartToEnd
                    ),
                    background = { Box(modifier = Modifier.fillMaxSize()) },
                    dismissContent = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate("detail/${list.id}") }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Archive,
                                contentDescription = "Arşivle"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(list.name)
                                val formattedDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                                    .format(Date(list.createdAt))
                                Text(
                                    text = formattedDate,
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}