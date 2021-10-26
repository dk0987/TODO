package com.example.todo.domain.usecases

import com.example.todo.domain.modal.Note
import com.example.todo.domain.repository.NoteRepository

class DeleteNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note : Note) {
        noteRepository.deleteNote(note)
    }
}