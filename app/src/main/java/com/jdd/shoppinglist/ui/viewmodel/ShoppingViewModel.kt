package com.jdd.shoppinglist.ui.viewmodel


import androidx.lifecycle.*
import com.jdd.shoppinglist.Data.model.ShoppingList
import com.jdd.shoppinglist.Data.model.ShoppingItem
import com.jdd.shoppinglist.Data.repository.ShoppingRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    val shoppingLists = repository.getActiveListsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val archivedLists = repository.getArchivedListsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _shoppingLists = MutableStateFlow<List<ShoppingList>>(emptyList())
   // val shoppingLists: StateFlow<List<ShoppingList>> = _shoppingLists.asStateFlow()

    private val _itemsMap = MutableStateFlow<Map<Int, List<ShoppingItem>>>(emptyMap())
    val itemsMap: StateFlow<Map<Int, List<ShoppingItem>>> = _itemsMap.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllListsFlow().collect { lists ->
                _shoppingLists.value = lists
                // Her liste için item'ları çek
                val map = lists.associate { list ->
                    list.id to repository.getItemsForListFlow(list.id).first()
                }
                _itemsMap.value = map
            }
        }
    }
    fun itemsFlowForList(listId: Int): Flow<List<ShoppingItem>> = repository.getItemsForListFlow(listId)
    fun addList(name: String = "Yeni Liste") = viewModelScope.launch {
        repository.insertList(
            ShoppingList(name = name, createdAt = System.currentTimeMillis())
        )
    }

    fun deleteList(list: ShoppingList) = viewModelScope.launch {
        repository.deleteList(list)
    }

    fun archiveList(list: ShoppingList) = viewModelScope.launch {
        repository.archiveList(list.id)
    }

    fun addItem(listId: Int, name: String, quantity: Double, price: Double) = viewModelScope.launch {
        repository.insertItem(
            ShoppingItem(listId = listId, name = name, quantity = quantity, price = price)
        )
    }

    fun deleteItem(listId: Int, item: ShoppingItem) = viewModelScope.launch {
        repository.deleteItem(item)
    }
}