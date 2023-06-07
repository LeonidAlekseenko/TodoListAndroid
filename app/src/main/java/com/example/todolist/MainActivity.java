package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View LinearLayoutNotes;
    View ButtonAddNote;
    Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ButtonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }
    private void initView() {
        LinearLayoutNotes = findViewById(R.id.LinearLayoutNotes);
        ButtonAddNote = findViewById(R.id.ButtonAddNote);
    }

    private void showNotes(){
        ((ViewGroup)LinearLayoutNotes).removeAllViews();
        for (Note note : database.getNotes()) {
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