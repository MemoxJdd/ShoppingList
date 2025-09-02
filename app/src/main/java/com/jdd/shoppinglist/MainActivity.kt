package com.jdd.shoppinglist
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.jdd.shoppinglist.Data.db.AppDatabase
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.repository.ShoppingRepository
import com.jdd.shoppinglist.ui.AppNavigation
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel

import com.jdd.shoppinglist.ui.screens.ShoppingListScreen
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModelFactory
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shopping-db"
        ).build()

        val repository = ShoppingRepository(
            db.shoppingListDao(),
            db.shoppingItemDao()
        )

        setContent {
            val viewModel: ShoppingViewModel = viewModel(
                factory = ShoppingViewModelFactory(repository)
            )
            AppNavigation(viewModel = viewModel)
        }
    }
}