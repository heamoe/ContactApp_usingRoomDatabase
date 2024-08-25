package com.amos.roomdatabasepractice

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("First Name", "Last Name", "Phone Number")
    var selectedItem by remember { mutableStateOf(items[0]) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        modifier = Modifier.padding(16.dp)
    ) {
        if (state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sorted By",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { expanded = true },
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = selectedItem, modifier = Modifier.clickable { expanded = true })

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEach { item ->
                        val sortType = when (item) {
                            "First Name" -> SortType.FIRST_NAME
                            "Last Name" -> SortType.LAST_NAME
                            "Phone Number" -> SortType.PHONE_NUMBER
                            else -> SortType.FIRST_NAME
                        }
                        DropdownMenuItem(
                            onClick = {
                                Log.d("ContactScreen", "Selected SortType: $sortType")
                                onEvent(ContactEvent.SortContacts(sortType))
                                selectedItem = item
                                expanded = false
                            },
                            text = { Text(item) }
                        )
                    }
                }
            }
            LazyColumn(
                contentPadding = it,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(state.contacts) { contact ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${contact.firstname} ${contact.lastname}",
                                fontSize = 20.sp
                            )
                            Text(text = contact.phoneNumber, fontSize = 12.sp)
                        }
                        IconButton(onClick = { onEvent(ContactEvent.DeleteContact(contact)) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}
