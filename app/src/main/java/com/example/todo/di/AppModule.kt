package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.data.datasrc.NoteDatabase
import com.example.todo.data.repository.NoteRepositoryImpl
import com.example.todo.domain.repository.NoteRepository
import com.example.todo.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app : Application) : NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "Note_Database"
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db : NoteDatabase) : NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository) : NoteUseCases{
        return NoteUseCases(
           getNote = GetNote(repository) ,
            insertNote = InsertNote(repository),
            deleteNote = DeleteNote(repository),
            search = Search(repository),
            getnotebyid = GetNoteByid(repository)
        )
    }


}