package com.amos.roomdatabasepractice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ContactViewModel(
    private val  dao: ContactDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val state = MutableStateFlow(ContactState())
    fun onEvent(event:ContactEvent){

    }
}