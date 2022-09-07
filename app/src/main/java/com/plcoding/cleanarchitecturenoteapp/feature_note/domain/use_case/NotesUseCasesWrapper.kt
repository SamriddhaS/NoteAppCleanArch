package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

data class NotesUseCasesWrapper(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)
