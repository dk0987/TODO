package com.example.todo.presentation.home_screen

import android.widget.SearchView
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.modal.Note
import com.example.todo.domain.usecases.NoteUseCases
import com.example.todo.presentation.home_screen.component.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class home_view_model @Inject constructor(
    private val noteUseCases: NoteUseCases
):ViewModel() , SearchView.OnQueryTextListener{

    init {
        getNote()
    }

    private val _state = mutableStateOf(States())
    val state : State<States> = _state

    fun getNote(){
        noteUseCases.getNote.invoke().onEach {
           _state.value = state.value.copy(
                note = it
            )
        }.launchIn(viewModelScope)
    }
    fun deleteNote(note : Note){
        viewModelScope.launch {
            noteUseCases.deleteNote(note)
        }
    }

    fun toggle(){
        _state.value = state.value.copy(
            visibility = !state.value.visibility,
            searchvisibility = !state.value.searchvisibility
        )
    }

    fun search(search : String?) {
        val searchQuery = "%$search%"
        viewModelScope.launch {
            noteUseCases.search.invoke(searchQuery).onEach {
               _state.value = state.value.copy(
                   note = it
                )
            }.launchIn(viewModelScope)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            search(newText)
        }
        return true
    }


}