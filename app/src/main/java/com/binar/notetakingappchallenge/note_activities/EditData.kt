package com.binar.notetakingappchallenge.note_activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binar.notetakingappchallenge.databinding.EditDataBinding
import com.binar.notetakingappchallenge.note_data.Note
import com.binar.notetakingappchallenge.note_data.NoteDatabase

class EditData : AppCompatActivity() {
    var mDb : NoteDatabase? = null
    private lateinit var binding: EditDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = EditDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mDb = NoteDatabase.getInstance(this)

        val objectNote = intent.getParcelableExtra<Note>("note")

        binding.etHeading.setText(objectNote?.heading)
        binding.etNote.setText(objectNote?.note)

        binding.btnEdit.setOnClickListener {
            objectNote?.heading = binding.etHeading.text.toString()
            objectNote?.note = binding.etNote.text.toString()

            Thread(Runnable {
                val result = objectNote?.let { it1 -> mDb?.noteDao()?.updateNote(it1) }

                runOnUiThread {
                    if (result != 0) {
                        Toast.makeText(this@EditData, "Success editing ${objectNote?.heading}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@EditData, "Failed editing ${objectNote.heading}", Toast.LENGTH_LONG).show()
                    }

                    finish()
                }
            }).start()
        }
    }
}