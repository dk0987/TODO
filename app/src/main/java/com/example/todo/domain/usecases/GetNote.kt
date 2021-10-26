package com.example.todo.domain.usecases

import com.example.todo.domain.modal.Note
import com.example.todo.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNote (private val noteRepository: NoteRepository){
     operator fun invoke() : Flow<List<Note>>{
        return noteRepository.getNote()
    }
}