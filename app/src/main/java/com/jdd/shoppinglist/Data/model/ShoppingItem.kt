package com.jdd.shoppinglist.Data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val listId: Int, // Hangi alışveriş listesine ait
    val name: String,
    val quantity:Double,
    val price:Double,
    var totalAmount: Double = 0.0,
    val isPurchased: Boolean = false
)