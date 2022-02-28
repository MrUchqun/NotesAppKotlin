package com.example.notesappkotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappkotlin.R
import com.example.notesappkotlin.manager.PrefsManager
import com.example.notesappkotlin.model.Note

class NoteAdapter(context: Context, var list: ArrayList<Note>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val prefsManager = PrefsManager.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NoteViewHolder(view)
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tv_date)
        val tvText: TextView = view.findViewById(R.id.tv_text)
        val ivPoint: ImageView = view.findViewById(R.id.iv_point)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note: Note = list[position]
        if (holder is NoteViewHolder) {

            holder.tvDate.text = note.date
            holder.tvText.text = note.text
            if (note.isRead!!)
                holder.ivPoint.visibility = View.INVISIBLE


            holder.tvText.setOnClickListener {
                list.remove(note)
                note.isRead = true
                list.add(position, note)
                saveNewList(list)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addNote(note: Note) {
        list.add(note)
        saveNewList(list)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun saveNewList(list: ArrayList<Note>) {
        prefsManager!!.saveArrayList(PrefsManager.KEY_LIST, list)
        notifyDataSetChanged()
    }
}