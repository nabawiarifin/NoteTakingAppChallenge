package com.binar.notetakingappchallenge.note_activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binar.notetakingappchallenge.databinding.InputDataBinding
import com.binar.notetakingappchallenge.note_data.Note
import com.binar.notetakingappchallenge.note_data.NoteDatabase

class InputData : AppCompatActivity() {

    var noteDb: NoteDatabase? = null
    private lateinit var binding: InputDataBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = InputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInput.setOnClickListener {
            val objectNote = Note(
                null,
                binding.etHeading.text.toString(),
                binding.etNote.text.toString()
            )

            Thread(Runnable {
                val result = noteDb?.noteDao()?.insertNote(objectNote)
                runOnUiThread {
                    if(result != 0.toLong()) {
                        Toast.makeText(this@InputData,"Success adding ${objectNote.heading}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@InputData, "Failed adding ${objectNote.heading}", Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }).start()
        }
    }
}