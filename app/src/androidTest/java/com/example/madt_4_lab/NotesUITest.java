package com.example.madt_4_lab;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
public class NotesUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNotesUI() {

        Espresso.onView(ViewMatchers.withId(R.id.editTextNoteName)).perform(ViewActions.typeText("Test Note"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextNoteContent)).perform(ViewActions.typeText("This is a test note content"));
        Espresso.onView(ViewMatchers.withId(R.id.buttonAddNote)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.listView)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
}
