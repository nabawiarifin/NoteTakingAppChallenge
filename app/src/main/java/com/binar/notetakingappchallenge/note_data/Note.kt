package com.binar.notetakingappchallenge.note_data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "heading") var heading: String,
    @ColumnInfo(name = "note") var note: String
) : Parcelable

