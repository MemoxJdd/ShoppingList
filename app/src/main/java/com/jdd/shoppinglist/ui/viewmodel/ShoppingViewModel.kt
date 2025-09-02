package com.jdd.shoppinglist.ui.viewmodel


import androidx.lifecycle.*
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.repository.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    // Tüm alışveriş listelerini gözlemler
    val shoppingLists: LiveData<List<ShoppingList>> = repository.getAllLists()

    // Listeye ait ürünleri gözlemler
    fun getItemsForList(listId: Int): LiveData<List<ShoppingItem>> =
        repository.getItemsForList(listId)

    // Liste ekle
    fun insertList(list: ShoppingList) = viewModelScope.launch {
        repository.insertList(list)
    }

    // Liste güncelle
    fun updateList(list: ShoppingList) = viewModelScope.launch {
        repository.updateList(list)
    }

    // Liste sil
    fun deleteList(list: ShoppingList) = viewModelScope.launch {
        repository.deleteList(list)
    }

    // Ürün ekle
    fun insertItem(item: ShoppingItem) = viewModelScope.launch {
        repository.insertItem(item)
    }

    // Ürün güncelle
    fun updateItem(item: ShoppingItem) = viewModelScope.launch {
        repository.updateItem(item)
    }

    // Ürün sil
    fun deleteItem(item: ShoppingItem) = viewModelScope.launch {
        repository.deleteItem(item)
    }
}