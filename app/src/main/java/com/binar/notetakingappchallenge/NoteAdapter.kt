package com.binar.notetakingappchallenge

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.binar.notetakingappchallenge.databinding.NoteItemBinding
import com.binar.notetakingappchallenge.note_activities.EditData
import com.binar.notetakingappchallenge.note_data.Note
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
                AlertDialog.Builder(it.context).setPositiveButton("Yes") {p0, p1->
                val noteDb = NoteDatabase.getInstance(holder.itemView.context)

                Thread(Runnable {
                    val result = noteDb?.noteDao()?.deleteNote(listNote[position])

                    (holder.itemView.context as MainActivity).runOnUiThread {
                        if (result != 0) {
                            Toast.makeText(it.context,"Data ${listNote[position].heading} has been deleted",
                                Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(it.context,"Data ${listNote[position].heading} has failed to be deleted",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                    (holder.itemView.context as MainActivity).fetchData()
                }).start()

            }.setNegativeButton("No"){
                    p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Are you sure you want to delete ${listNote[position].heading}").setTitle("Confirm Delete").create().show()
            }
        }
    }


}