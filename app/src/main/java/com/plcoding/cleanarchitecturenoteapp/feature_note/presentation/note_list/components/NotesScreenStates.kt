package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.note_list.components

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteSortingType
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderIn

data class NotesScreenStates(
    val notesList:List<Note> = emptyList(),
    val noteSortingType: NoteSortingType = NoteSortingType.Date(OrderIn.Descending),
    val isSortingSectionVisible:Boolean=false
)
