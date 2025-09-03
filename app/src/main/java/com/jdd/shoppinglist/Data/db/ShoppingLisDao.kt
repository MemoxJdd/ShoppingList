package com.jdd.shoppinglist.Data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.model.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists WHERE isArchived = 0")
    fun getActiveListsFlow(): Flow<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists WHERE isArchived = 1")
    fun getArchivedListsFlow(): Flow<List<ShoppingList>>

    @Insert
    suspend fun insertList(list: ShoppingList): Long

    @Delete
    suspend fun deleteList(list: ShoppingList)

    @Query("UPDATE shopping_lists SET isArchived = 1 WHERE id = :listId")
    suspend fun archiveList(listId: Int)
}