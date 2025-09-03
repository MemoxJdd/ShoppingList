package com.jdd.shoppinglist.Data.repository

import androidx.lifecycle.LiveData
import com.jdd.shoppinglist.Data.db.ShoppingItemDao
import com.jdd.shoppinglist.Data.db.ShoppingListDao
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.model.ShoppingList


class ShoppingRepository(
    private val shoppingListDao: ShoppingListDao,
    private val shoppingItemDao: ShoppingItemDao
) {
    // Listeler
    fun getAllListsFlow() = shoppingListDao.getActiveListsFlow()
    fun getArchivedListsFlow() = shoppingListDao.getArchivedListsFlow()
    suspend fun insertList(list: ShoppingList) = shoppingListDao.insertList(list)
    suspend fun deleteList(list: ShoppingList) = shoppingListDao.deleteList(list)
    suspend fun archiveList(listId: Int) = shoppingListDao.archiveList(listId)

    // Item'lar
    fun getItemsForListFlow(listId: Int) = shoppingItemDao.getItemsForListFlow(listId)
    suspend fun insertItem(item: ShoppingItem) = shoppingItemDao.insertItem(item)
    suspend fun deleteItem(item: ShoppingItem) = shoppingItemDao.deleteItem(item)
}