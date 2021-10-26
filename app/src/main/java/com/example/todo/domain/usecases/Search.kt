package com.example.todo.domain.usecases

import com.example.todo.domain.modal.Note
import com.example.todo.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class Search(
    private val noteRepository: NoteRepository
) {
      suspend operator fun invoke(search : String) : Flow<List<Note>> {
        return noteRepository.search(search)
    }
}