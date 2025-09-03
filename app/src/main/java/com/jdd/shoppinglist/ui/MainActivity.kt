package com.jdd.shoppinglist.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.jdd.shoppinglist.Data.db.AppDatabase
import com.jdd.shoppinglist.Data.repository.ShoppingRepository
import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModel

import com.jdd.shoppinglist.ui.viewmodel.ShoppingViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shopping-db"
        ).fallbackToDestructiveMigration() // BUNU EKLE!
            .build()
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