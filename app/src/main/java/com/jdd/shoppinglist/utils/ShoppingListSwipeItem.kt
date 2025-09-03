package com.jdd.shoppinglist.utils

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Archive
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShoppingListSwipeItem(
    listName: String,
    onDelete: () -> Unit,
    onArchive: () -> Unit,
    onClick: () -> Unit
) {
    val dismissState = rememberDismissState(confirmStateChange = { value ->
        when (value) {
            DismissValue.DismissedToEnd -> {
                onArchive()
                false // item kaybolmasın
            }
            DismissValue.DismissedToStart -> {
                onDelete()
                false
            }
            else -> false
        }
    })

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(
            DismissDirection.EndToStart, // Sola sil
            DismissDirection.StartToEnd  // Sağa arşivle
        ),
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color = when (direction) {
                DismissDirection.EndToStart -> Color.Red
                DismissDirection.StartToEnd -> Color.Blue
            }
            Surface(color = color, modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = if (direction == DismissDirection.EndToStart) Icons.Default.Delete else Icons.Default.Archive,
                    contentDescription = null
                )
            }
        },
        dismissContent = {
            ListItem(
                modifier = Modifier.clickable { onClick() },
                text = { Text(listName) }
            )
        }
    )
}