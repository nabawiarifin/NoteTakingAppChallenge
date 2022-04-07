package com.binar.notetakingappchallenge

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.notetakingappchallenge.databinding.NoteItemBinding
import com.binar.notetakingappchallenge.note_activities.EditData
import com.binar.notetakingappchallenge.note_data.Note
import com.binar.notetakingappchallenge.note_data.NoteDao
import com.binar.notetakingappchallenge.note_data.NoteDatabase

class NoteAdapter(val listNote : List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = NoteItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvHeading.text = listNote[position].heading
            binding.tvNote.text = listNote[position].note

            binding.ivEdit.setOnClickListener {
                val intentToEditActivity = Intent(it.context, EditData::class.java)

                intentToEditActivity.putExtra("note", listNote[position])
                it.context.startActivity(intentToEditActivity)
            }

            binding.ivDelete.setOnClickListener {
                AlertDialog.Builder(it.context).setPositiveButton("Yes") {
                    po, p1 ->
                    val noteDb = NoteDatabase.getInstance(holder.itemView.context)
                }
            }
        }

    }
}