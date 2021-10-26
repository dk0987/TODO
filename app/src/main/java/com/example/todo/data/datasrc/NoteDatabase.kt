package com.example.todo.data.datasrc

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.domain.modal.Note

@Database(
    entities = [Note :: class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase(){
    abstract val noteDao : NoteDao
}