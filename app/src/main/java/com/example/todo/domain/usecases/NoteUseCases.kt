package com.example.todo.domain.usecases

data class NoteUseCases(
    val getNote: GetNote ,
    val insertNote: InsertNote,
    val deleteNote: DeleteNote,
    val search: Search,
    val getnotebyid : GetNoteByid
)
