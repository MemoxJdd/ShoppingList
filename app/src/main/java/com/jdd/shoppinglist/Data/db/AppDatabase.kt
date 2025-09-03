package com.jdd.shoppinglist.Data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem

@Database(
    entities = [ShoppingList::class, ShoppingItem::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingItemDao(): ShoppingItemDao
}