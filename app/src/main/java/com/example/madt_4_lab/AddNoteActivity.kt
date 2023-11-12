package com.example.madt_4_lab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class AddNoteActivity : AppCompatActivity() {
    private lateinit var editTextNoteName: EditText
    private lateinit var editTextNoteContent: EditText
    private lateinit var buttonAddNote: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextNoteName = findViewById(R.id.editTextNoteName)
        editTextNoteContent = findViewById(R.id.editTextNoteContent)
        buttonAddNote = findViewById(R.id.buttonAddNote)

        buttonAddNote.setOnClickListener {
            val noteName = editTextNoteName.text.toString()
            val noteContent = editTextNoteContent.text.toString()

            if (noteName.isEmpty() || noteContent.isEmpty()) {
                Toast.makeText(this, R.string.empty_warning, Toast.LENGTH_SHORT).show()
            } else {
                val note = Note(noteName, noteContent)
                saveNote(note)
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_return -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.action_add_note).isVisible = false
        menu.findItem(R.id.action_return).isVisible = true
        menu.findItem(R.id.action_delete_note).isVisible = true
        return super.onPrepareOptionsMenu(menu)
    }

    private fun saveNote(note: Note) {
        val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = sharedPreferences.getString("notes", "[]")
        val jsonArray = JSONArray(json)
        jsonArray.put(note.toJSON().toString())
        editor.putString("notes", jsonArray.toString())
        editor.apply()
    }
}

