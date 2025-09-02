package com.jdd.shoppinglist.ui.viewmodel


import androidx.lifecycle.*
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.repository.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    val shoppingLists: LiveData<List<ShoppingList>> = repository.getAllLists()
    fun getItemsForList(listId: Int): LiveData<List<ShoppingItem>> = repository.getItemsForList(listId)

    fun insertList(list: ShoppingList, onInserted: (Long) -> Unit = {}) = viewModelScope.launch {
        val newId = repository.insertList(list)
        onInserted(newId)
    }
    fun updateList(list: ShoppingList) = viewModelScope.launch { repository.updateList(list) }
    fun deleteList(list: ShoppingList) = viewModelScope.launch { repository.deleteList(list) }
    fun insertItem(item: ShoppingItem) = viewModelScope.launch { repository.insertItem(item) }
    fun updateItem(item: ShoppingItem) = viewModelScope.launch { repository.updateItem(item) }
    fun deleteItem(item: ShoppingItem) = viewModelScope.launch { repository.deleteItem(item) }
}