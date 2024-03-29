package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

import static com.example.notesapp.MainActivity.arrayAdapter;
import static com.example.notesapp.MainActivity.notes;

public class EditNoteActivity extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        if(noteId != -1){
            editText.setText(notes.get(noteId));
        }
        else{
                MainActivity.notes.add("");
                noteId = MainActivity.notes.size()-1;
                MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                HashSet<String> hashSet = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",hashSet).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
