package com.example.madt_4_lab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    public val notes = mutableListOf<Note>()
    public lateinit var adapter: ArrayAdapter<Note>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
        listView.adapter = adapter

        loadNotes()
    }

    override fun onResume() {
        super.onResume()
        refreshNotes()
    }

    public fun loadNotes() {
        val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("notes", "[]")
        val jsonArray = JSONArray(json)
        for (i in 0 until jsonArray.length()) {
            notes.add(Note.fromJSON(jsonArray.getString(i)))
        }
    }

    public fun refreshNotes() {
        notes.clear()
        loadNotes()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_note -> {
                startActivity(Intent(this, AddNoteActivity::class.java))
                true
            }
            R.id.action_delete_note -> {
                startActivity(Intent(this, DeleteNoteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.action_add_note).isVisible = true
        menu.findItem(R.id.action_delete_note).isVisible = true
        return super.onPrepareOptionsMenu(menu)
    }
}

