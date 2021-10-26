package com.example.todo.presentation.edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.modal.Note
import com.example.todo.domain.usecases.NoteUseCases
import com.example.todo.presentation.edit_note.component.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class edit_note_viewmodel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _state = mutableStateOf(States())
    val state : State<States> = _state

    init {
        savedStateHandle.get<Int>("id")?.let {id->
            if (id != -1){
                viewModelScope.launch {
                    noteUseCases.getnotebyid(id)?.also { note ->
                        _state.value = state.value.copy(
                            id = note.id,
                            title = note.title,
                            content = note.content
                        )
                    }
                }
            }
        }
    }

     fun insert(note : Note){
        viewModelScope.launch {
            noteUseCases.insertNote(note)
        }
    }

    fun ontitlevaluechange(string : String){
        _state.value = state.value.copy(
            title = string
        )
    }
    fun oncontentvaluechange(string : String){
        _state.value = state.value.copy(
            content = string
        )
    }

}