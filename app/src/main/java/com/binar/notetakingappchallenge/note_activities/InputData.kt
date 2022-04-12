package com.binar.notetakingappchallenge.note_activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.binar.notetakingappchallenge.MainActivity
import com.binar.notetakingappchallenge.databinding.InputDataBinding
import com.binar.notetakingappchallenge.note_data.Note
import com.binar.notetakingappchallenge.note_data.NoteDatabase
import com.binar.notetakingappchallenge.user_activities.login.Login
import java.util.concurrent.Executors

class InputDataFragment : DialogFragment() {

    private lateinit var binding: InputDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = InputDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    var noteDb: NoteDatabase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDb = NoteDatabase.getInstance(MainActivity())
        binding.btnInput.setOnClickListener {
            val objectNote = Note(
                null,
                binding.etHeading.text.toString(),
                binding.etNote.text.toString()
            )

            Thread(Runnable {
                val result = noteDb?.noteDao()?.insertNote(objectNote)
                activity?.runOnUiThread {
                    if(result != 0.toLong()) {
                    Toast.makeText(activity,"Success adding ${objectNote.heading}", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(activity, "Failed adding ${objectNote.heading}", Toast.LENGTH_LONG).show()
                }}
            }).start()



        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

}
