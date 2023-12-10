package com.example.madt_4_lab

import android.content.Context
import android.support.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class NotesUnitTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testOnCreate() {
        val activity = activityRule.activity
        assertNotNull(activity.findViewById(R.id.listView))
    }

    @Test
    fun testLoadNotes() {
        val activity = activityRule.activity
        val sharedPreferences = mockContext.getSharedPreferences("notes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("notes", "[{\"title\":\"Test Note\",\"content\":\"This is a test note.\"}]")
        editor.apply()

        activity.loadNotes()

        assertEquals(1, activity.notes.size)
        assertEquals("Test Note", activity.notes[0].name)
        assertEquals("This is a test note.", activity.notes[0].content)
    }

    @Test
    fun testRefreshNotes() {
        val activity = activityRule.activity
        activity.notes.add(Note("Test Note", "This is a test note."))
        assertEquals(1, activity.notes.size)

        activity.refreshNotes()

        assertEquals(0, activity.notes.size)
    }
}
