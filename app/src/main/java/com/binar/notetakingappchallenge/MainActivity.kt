package com.binar.notetakingappchallenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.notetakingappchallenge.databinding.ActivityMainBinding
import com.binar.notetakingappchallenge.databinding.InputDataBinding
import com.binar.notetakingappchallenge.note_activities.InputDataFragment
import com.binar.notetakingappchallenge.note_data.NoteDatabase
import com.binar.notetakingappchallenge.user_activities.login.Login as Login1

class MainActivity : AppCompatActivity() {

    private var noteDb: NoteDatabase? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        //Show name
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val sharedName = sharedPreferences.getString("username", "")
        binding.tvName.text = sharedName

        noteDb = NoteDatabase.getInstance(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fetchData()

//        binding.addButton.setOnClickListener{
//            val toActivityAdd = Intent(this, InputData::class.java)
//            startActivity(toActivityAdd)
//        }
        //TODO: Add button opens input data as dialog
        binding.addButton.setOnClickListener {
            val inputDataFragment = InputDataFragment()
            inputDataFragment.show(supportFragmentManager,"tag")

        }

        binding.tvLogout.setOnClickListener {
            finish()
        }

    }


    fun fetchData(){
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

