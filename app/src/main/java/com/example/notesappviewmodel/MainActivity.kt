package com.example.notesappviewmodel

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {


//    private lateinit var notes: List<Note>
    private lateinit var adapter: RVAdapter

    lateinit var mainViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        notes = arrayListOf()
        adapter = RVAdapter(this)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainViewModel.getNotes().observe(this, {
                notos -> adapter.update(notos)
        })

        btSave.setOnClickListener {
            if (etNote.text.toString().isNotEmpty()) {

                mainViewModel.addNote(etNote.text.toString())
                etNote.text.clear()
                etNote.clearFocus()
            } else {
                Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()

            }

        }

    }


    fun raiseDialog(id: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
                editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }


    private fun editNote(noteID: Int, noteText: String) {

        lateinit var mainViewModel: MainActivityViewModel
        mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainViewModel.editNote(noteID, noteText)

    }

    fun deleteNote(noteID: Int) {
        lateinit var mainViewModel: MainActivityViewModel
        mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        mainViewModel.deleteNote(noteID)


    }


}