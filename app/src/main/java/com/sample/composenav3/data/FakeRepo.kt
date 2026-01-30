package com.sample.composenav3.data

interface FakeRepo {
    fun getItems(): List<Item>
    fun getItemById(id: String): Item?
}
