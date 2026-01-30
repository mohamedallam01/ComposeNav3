package com.sample.composenav3.ui.main.home

import androidx.lifecycle.ViewModel
import com.sample.composenav3.data.FakeRepo
import com.sample.composenav3.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val repo: FakeRepo) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    init {
        _items.value = repo.getItems()
    }
}
