package com.example.madt_4_lab

import org.json.JSONObject

data class Note(val name: String, val content: String) {
    fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("content", content)
        return jsonObject
    }

    companion object {
        fun fromJSON(json: String): Note {
            val jsonObject = JSONObject(json)
            return Note(jsonObject.getString("name"), jsonObject.getString("content"))
        }
    }
}
