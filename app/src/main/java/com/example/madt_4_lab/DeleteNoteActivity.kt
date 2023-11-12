package com.example.madt_4_lab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class DeleteNoteActivity : AppCompatActivity() {
    private val notes = mutableListOf<Note>()
    private lateinit var adapter: ArrayAdapter<Note>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_note)

        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
        listView.adapter = adapter

        loadNotes()

        listView.setOnItemClickListener { _, _, position, _ ->
            notes.removeAt(position)
            saveNotes()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshNotes()
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
        menu.findItem(R.id.action_add_note).isVisible = true
        menu.findItem(R.id.action_return).isVisible = true
        menu.findItem(R.id.action_delete_note).isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }

    private fun loadNotes() {
        val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("notes", "[]")
        val jsonArray = JSONArray(json)
        for (i in 0 until jsonArray.length()) {
            notes.add(Note.fromJSON(jsonArray.getString(i)))
        }
    }

    private fun refreshNotes() {
        notes.clear()
        loadNotes()
        adapter.notifyDataSetChanged()
    }

    private fun saveNotes() {
        val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()
        for (note in notes) {
            jsonArray.put(note.toJSON().toString())
        }
        editor.putString("notes", jsonArray.toString())
        editor.apply()
    }
}