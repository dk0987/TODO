package com.example.todo.data.datasrc

import androidx.room.*
import com.example.todo.domain.modal.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getNote() : Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE ID = :id")
    suspend fun getNoteByid(id : Int) : Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * FROM NOTE WHERE title LIKE :search")
     fun search(search : String) : Flow<List<Note>>

}