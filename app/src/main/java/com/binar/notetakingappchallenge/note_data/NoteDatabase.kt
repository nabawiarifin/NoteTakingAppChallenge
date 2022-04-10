package com.binar.notetakingappchallenge.note_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.binar.notetakingappchallenge.NoteAdapter
import com.binar.notetakingappchallenge.note_activities.InputDataFragment

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if(INSTANCE == null){
                synchronized(NoteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,"Note.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}