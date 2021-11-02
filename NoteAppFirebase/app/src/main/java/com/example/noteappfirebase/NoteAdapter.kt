package com.example.noteappfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_row.view.*

class NoteAdapter (private val activity: FragmentHome ):  RecyclerView.Adapter<NoteAdapter.ItemViewHolder>(){

    private var notes = emptyList<Note>()


    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var myNote = notes[position]

        holder.itemView.apply {
            textView.text = myNote.note
            editBtn.setOnClickListener {
                val bundle = bundleOf(
                    "Id" to myNote.id,
                )
                Navigation.findNavController(activity.requireView())
                    .navigate(R.id.action_fragmentHome_to_fragmentUpdate, bundle)

            }
            deleteBtn.setOnClickListener {
                var noteID = myNote.id
                activity.deleteAlert(noteID)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

}