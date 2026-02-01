package com.sample.composenav3.ui.main.details

import androidx.lifecycle.ViewModel
import com.sample.composenav3.data.FakeRepo
import com.sample.composenav3.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val repo: FakeRepo,
) : ViewModel() {
    private val _item = MutableStateFlow<Item?>(null)
    val item: StateFlow<Item?> = _item.asStateFlow()

    fun getItemById(itemId: String) {
        _item.update { repo.getItemById(itemId) }
    }
}
