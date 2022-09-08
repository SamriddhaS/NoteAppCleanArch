package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.note_list

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteSortingType

sealed class NotesEvents {
    data class SortNote(val noteSortType:NoteSortingType): NotesEvents()
    data class DeleteNote(val note: Note): NotesEvents()
    object RestoreNote: NotesEvents()
    object ToggleSortNoteSection: NotesEvents()
}