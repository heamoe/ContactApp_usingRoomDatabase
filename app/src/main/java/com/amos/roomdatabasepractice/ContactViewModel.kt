package com.amos.roomdatabasepractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ContactViewModel(
    private val  dao: ContactDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val state = MutableStateFlow(ContactState())
    fun onEvent(event:ContactEvent){
        when(event){
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch { dao.deleteContact(event.contact) }
            }
            ContactEvent.HideDialog -> TODO()
            ContactEvent.SaveContact -> TODO()
            is ContactEvent.SetFirstName -> TODO()
            is ContactEvent.SetLastName -> TODO()
            is ContactEvent.SetPhoneNumber -> TODO()
            ContactEvent.ShowDialog -> TODO()
            is ContactEvent.SortContacts -> TODO()
        }
    }
}