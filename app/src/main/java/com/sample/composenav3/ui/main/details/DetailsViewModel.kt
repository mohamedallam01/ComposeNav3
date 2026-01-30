package com.sample.composenav3.ui.main.details

import androidx.lifecycle.ViewModel
import com.sample.composenav3.data.FakeRepo
import com.sample.composenav3.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel(
    repo: FakeRepo,
    itemId: String
) : ViewModel() {

    private val _item = MutableStateFlow<Item?>(null)
    val item: StateFlow<Item?> = _item.asStateFlow()

    init {
        _item.value = repo.getItemById(itemId)
    }
}
