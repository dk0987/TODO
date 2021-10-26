package com.example.todo.presentation.home_screen.component

import com.example.todo.domain.modal.Note

data class States (
    var visibility : Boolean = true,
    var searchvisibility : Boolean = false,
    var note : List<Note> = emptyList()
)