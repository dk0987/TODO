package com.example.todo.domain.repository
import com.example.todo.domain.modal.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository
{

 fun getNote() : Flow<List<Note>>

 suspend fun getNoteByid(id : Int) : Note?

 suspend fun insertNote(note : Note)

 suspend fun deleteNote(note : Note)

 suspend fun search(search : String ) : Flow<List<Note>>

}