package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteSortingType
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(
        noteSortingType: NoteSortingType = NoteSortingType.Date(OrderIn.Descending)
    ):Flow<List<Note>>{
        return noteRepository.getNotesList().map { notes ->
            when(noteSortingType.orderIn){
                is OrderIn.Ascending -> {
                    when(noteSortingType){
                        is NoteSortingType.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteSortingType.Date -> notes.sortedBy { it.timeStamp }
                        is NoteSortingType.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderIn.Descending -> {
                    when(noteSortingType){
                        is NoteSortingType.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteSortingType.Date -> notes.sortedByDescending { it.timeStamp }
                        is NoteSortingType.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}