package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    View LinearLayoutNotes;
    View ButtonAddNote;

    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note " + i, random.nextInt(3));
            notes.add(note);
            showNotes();
            ButtonAddNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                    startActivity(intent);
                }
            });
        }
    }

    private void initView() {
        LinearLayoutNotes = findViewById(R.id.LinearLayoutNotes);
        ButtonAddNote = findViewById(R.id.ButtonAddNote);
    }

    private void showNotes(){
        for (Note note : notes) {
            View view = getLayoutInflater().inflate(R.layout.note_item, (ViewGroup) LinearLayoutNotes, false);
            TextView TextViewNote = view.findViewById(R.id.TextViewNote);
            TextViewNote.setText(note.getText());
            int colorResId;
            switch (note.getPriority()) {
                case 0:
                    colorResId = android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_light;
                    break;
                default:
                    colorResId = android.R.color.holo_red_light;
            }
            ((ViewGroup) LinearLayoutNotes).addView(view);
            int color = ContextCompat.getColor(this, colorResId);
            TextViewNote.setBackgroundColor(color);
        }
    }



}