package com.example.todo.domain.usecases

import com.example.todo.domain.modal.Note
import com.example.todo.domain.repository.NoteRepository

class GetNoteByid(
    private val repository: NoteRepository
) {
    suspend operator fun  invoke(id : Int) : Note? {
        return repository.getNoteByid(id)
    }
}