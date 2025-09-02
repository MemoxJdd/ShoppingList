package com.jdd.shoppinglist.Data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jdd.shoppinglist.Data.model.ShoppingItem

@Dao
interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_items WHERE listId = :listId")
    fun getItemsForList(listId: Int): LiveData<List<ShoppingItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItem)

    @Update
    suspend fun updateItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)
}