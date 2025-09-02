package com.jdd.shoppinglist.Data.repository

import androidx.lifecycle.LiveData
import com.jdd.shoppinglist.Data.db.ShoppingItemDao
import com.jdd.shoppinglist.Data.db.ShoppingListDao
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.model.ShoppingList

class ShoppingRepository(
    private val listDao: ShoppingListDao,
    private val itemDao: ShoppingItemDao
) {

    // Shopping List işlemleri
    fun getAllLists(): LiveData<List<ShoppingList>> = listDao.getAllLists()
    suspend fun insertList(list: ShoppingList) = listDao.insertList(list)
    suspend fun updateList(list: ShoppingList) = listDao.updateList(list)
    suspend fun deleteList(list: ShoppingList) = listDao.deleteList(list)

    // Shopping Item işlemleri
    fun getItemsForList(listId: Int): LiveData<List<ShoppingItem>> = itemDao.getItemsForList(listId)
    suspend fun insertItem(item: ShoppingItem) = itemDao.insertItem(item)
    suspend fun updateItem(item: ShoppingItem) = itemDao.updateItem(item)
    suspend fun deleteItem(item: ShoppingItem) = itemDao.deleteItem(item)
}
