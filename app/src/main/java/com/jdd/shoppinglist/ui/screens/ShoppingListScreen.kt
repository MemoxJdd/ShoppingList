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
import kotlinx.coroutines.launch
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
    var showDeleteDialog by remember { mutableStateOf(false) }
    var listToDelete by remember { mutableStateOf<ShoppingList?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alışveriş Listeleri") },
                actions = {
                    Button(
                        onClick = { navController.navigate("archived") },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
                    ) {
                        Icon(Icons.Default.Archive, contentDescription = "Arşivlenenler")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Arşivlenenler")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                        DismissValue.DismissedToEnd -> { // Sağa swipe: arşivle
                            onArchive(list)
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Liste arşivlendi!")
                            }
                            false
                        }
                        DismissValue.DismissedToStart -> { // Sola swipe: silmeden önce onayla
                            listToDelete = list
                            showDeleteDialog = true
                            false
                        }
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
                            Icon(imageVector = Icons.Default.Archive, contentDescription = "Arşivle")
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
        // Silme onay dialogu
        if (showDeleteDialog && listToDelete != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Silmek istediğinizden emin misiniz?") },
                text = { Text("Bu listeyi gerçekten silmek istiyor musunuz?") },
                confirmButton = {
                    Button(onClick = {
                        onDelete(listToDelete!!)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Liste silindi!")
                        }
                        showDeleteDialog = false
                        listToDelete = null
                    }) { Text("Sil") }
                },
                dismissButton = {
                    Button(onClick = {
                        showDeleteDialog = false
                        listToDelete = null
                    }) { Text("İptal") }
                }
            )
        }
    }
}