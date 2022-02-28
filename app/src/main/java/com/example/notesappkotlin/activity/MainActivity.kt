package com.example.notesappkotlin.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappkotlin.R
import com.example.notesappkotlin.adapter.NoteAdapter
import com.example.notesappkotlin.fragment.DialogFragment
import com.example.notesappkotlin.manager.PrefsManager
import com.example.notesappkotlin.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        prefsManager = PrefsManager.getInstance(this)!!
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        adapter = NoteAdapter(this, getNotes())
        recyclerView.adapter = adapter

        val btnAdd: FloatingActionButton = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            callDialog()
        }

    }

    private fun callDialog() {
        val dialogFragment = DialogFragment()
        dialogFragment.show(supportFragmentManager, null)

        dialogFragment.saveClick = {
            val note = Note(getDate(), it)
            adapter.addNote(note)
        }
    }

    private fun getNotes(): ArrayList<Note> {
        val type: Type = object : TypeToken<ArrayList<Note>>() {}.type
        return prefsManager.getArrayList(PrefsManager.KEY_LIST, type)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String? {
        val date: Date = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        return simpleDateFormat.format(date)
    }

}