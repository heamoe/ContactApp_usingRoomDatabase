package com.amos.roomdatabasepractice

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstname:String = "",
    val lastname:String = "",
    val phoneNumber:String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
