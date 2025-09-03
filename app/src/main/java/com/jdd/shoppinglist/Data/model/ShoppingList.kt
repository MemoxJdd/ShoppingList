package com.jdd.shoppinglist.Data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val createdAt: Long,
   val isArchived: Boolean = false,
    val totalAmount: Double = 0.0 // <-- yeni alan

)