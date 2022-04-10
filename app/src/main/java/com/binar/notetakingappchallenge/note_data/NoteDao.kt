package com.binar.notetakingappchallenge.note_data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAllNote(): List<Note>

    @Insert(onConflict = REPLACE)
    fun insertNote(note: Note): Long

    @Update
    fun updateNote(note: Note): Int

    @Delete
    fun deleteNote(note: Note): Int

}