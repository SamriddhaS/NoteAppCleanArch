package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.note_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCasesWrapper
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteSortingType
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel
@Inject
constructor(
    private val notesUseCasesWrapper: NotesUseCasesWrapper
) :ViewModel() {

    private val _state = mutableStateOf(NotesScreenStates())
    val state:State<NotesScreenStates> = _state

    private var recentlyDeletedNote:Note?=null
    private var getNotesListJob:Job?=null

    init {
        getSortedNote(NoteSortingType.Date(OrderIn.Descending))
    }

    fun onEvent(events: NotesEvents){
        when(events){
            is NotesEvents.SortNote -> {
                if (state.value.noteSortingType::class==events.noteSortType::class
                    && state.value.noteSortingType.orderIn::class==events.noteSortType.orderIn::class
                ){
                    return
                }
                getSortedNote(events.noteSortType)
            }
            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCasesWrapper.deleteNoteUseCase(events.note)
                    recentlyDeletedNote = events.note
                }
            }
            is NotesEvents.ToggleSortNoteSection -> {
                _state.value = state.value.copy(
                    isSortingSectionVisible = !_state.value.isSortingSectionVisible
                )
            }
            is NotesEvents.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCasesWrapper.addNoteUseCase(recentlyDeletedNote?:return@launch)
                    recentlyDeletedNote=null
                }
            }
        }
    }

    private fun getSortedNote(noteSortType: NoteSortingType) {
        getNotesListJob?.cancel()
        getNotesListJob = notesUseCasesWrapper.getNotesUseCase(noteSortType).onEach { note ->
            _state.value = state.value.copy(
                notesList = note,
                noteSortingType = noteSortType
            )
        }.launchIn(viewModelScope)
    }
}