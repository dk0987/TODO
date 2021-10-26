package com.example.todo.domain.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var title : String,
    var content : String,
    @PrimaryKey var id : Int? = null
)
