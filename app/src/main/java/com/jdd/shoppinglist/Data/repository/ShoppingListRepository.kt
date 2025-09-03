package com.jdd.shoppinglist.Data.repository

import com.jdd.shoppinglist.Data.db.ShoppingListDao
import com.jdd.shoppinglist.Data.model.ShoppingList
import kotlinx.coroutines.flow.Flow

class ShoppingListRepository(private val dao: ShoppingListDao) {
    fun getArchivedListsFlow(): Flow<List<ShoppingList>> = dao.getArchivedListsFlow()
    fun getActiveListsFlow(): Flow<List<ShoppingList>> = dao.getActiveListsFlow()
    // Diğer CRUD fonksiyonları...
}