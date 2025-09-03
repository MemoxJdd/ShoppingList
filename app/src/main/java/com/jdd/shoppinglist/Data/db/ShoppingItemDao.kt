package com.jdd.shoppinglist.Data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jdd.shoppinglist.Data.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_items WHERE listId = :listId")
    fun getItemsForListFlow(listId: Int): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_items WHERE listId = :listId")
    suspend fun getItemsForList(listId: Int): List<ShoppingItem> // <-- toplam tutar için eklenmeli

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItem): Long

    @Update
    suspend fun updateItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)
}