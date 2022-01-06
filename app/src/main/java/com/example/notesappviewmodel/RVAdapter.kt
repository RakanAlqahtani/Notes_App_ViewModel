package com.example.notesappviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_row.view.*

class RVAdapter(private val activity: MainActivity):RecyclerView.Adapter<RVAdapter.ItemsViewHolder>(){

  private  var notes = emptyList<Note>()
    class ItemsViewHolder(itemsView : View): RecyclerView.ViewHolder(itemsView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.items_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val text = notes[position]

        holder.itemView.apply {
            tvNote.text = "${text.id} - ${text.note}"

            btn_edit.setOnClickListener{
                activity.raiseDialog(text.id)
            }

            btn_delete.setOnClickListener {
                activity.deleteNote(text.id)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes : List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}