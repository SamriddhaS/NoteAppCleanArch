package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDb
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepoImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCasesWrapper
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
    fun providesNotesDb(app:Application):NoteDb{
        return Room.databaseBuilder(
            app,
            NoteDb::class.java,
            NoteDb.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepo(noteDb: NoteDb):NoteRepository{
        return NoteRepoImpl(noteDb.noteDao)
    }

    @Provides
    @Singleton
    fun providesNotesUseCaseWrapper(noteRepository: NoteRepository):NotesUseCasesWrapper{
        return NotesUseCasesWrapper(
            GetNotesUseCase(noteRepository),
            DeleteNoteUseCase(noteRepository),
            AddNoteUseCase(noteRepository)
        )
    }
}