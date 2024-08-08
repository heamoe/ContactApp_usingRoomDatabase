package com.amos.roomdatabasepractice

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Contact (
    val firstname : String,
    val lastname : String,
    val phoneNumber : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
)
