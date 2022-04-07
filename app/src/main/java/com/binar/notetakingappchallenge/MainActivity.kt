package com.binar.notetakingappchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.notetakingappchallenge.databinding.ActivityMainBinding
import com.binar.notetakingappchallenge.note_activities.InputData
import com.binar.notetakingappchallenge.note_data.NoteDatabase

class MainActivity : AppCompatActivity() {

    private var noteDb: NoteDatabase? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        noteDb = NoteDatabase.getInstance(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fetchData()

        binding.addButton.setOnClickListener{
            val toActivityAdd = Intent(this, InputData::class.java)
            startActivity(toActivityAdd)
        }

    }

    private fun fetchData(){
        Thread(Runnable{
            val listNote = noteDb?.noteDao()?.getAllNote()

            runOnUiThread {
                listNote?.let {
                    val adapter = NoteAdapter(it)
                    binding.recyclerView.adapter = adapter
                }
            }
        }).start()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        NoteDatabase.destroyInstance()
    }

}