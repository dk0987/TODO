package com.example.todo.data.repository

import com.example.todo.data.datasrc.NoteDao
import com.example.todo.domain.modal.Note
import com.example.todo.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao : NoteDao
) : NoteRepository {
    override fun getNote(): Flow<List<Note>> {
        return dao.getNote()
    }

     override suspend fun getNoteByid(id: Int): Note? {
        return dao.getNoteByid(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }

    override suspend fun search(search: String): Flow<List<Note>> {
        return dao.search(search)
    }
}