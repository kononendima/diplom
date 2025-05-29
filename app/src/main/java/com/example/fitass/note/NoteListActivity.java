package com.example.fitass.note;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitAss.R;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private NoteDatabaseHelper dbHelper;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        dbHelper = new NoteDatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter(dbHelper.getAllNotes());
        recyclerView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAddNote);
        btnAdd.setOnClickListener(v -> showAddNoteDialog());
        adapter.setOnItemClickListener(note -> showEditNoteDialog(note));

    }

    private void showAddNoteDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        EditText editTextNote = dialogView.findViewById(R.id.editTextNote);

        new AlertDialog.Builder(this)
                .setTitle("Новая заметка")
                .setView(dialogView)
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String text = editTextNote.getText().toString().trim();
                    if (!text.isEmpty()) {
                        dbHelper.addNote(text);
                        adapter.updateNotes(dbHelper.getAllNotes());
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void showEditNoteDialog(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Редактировать заметку");

        final EditText input = new EditText(this);
        input.setText(note.getText());
        builder.setView(input);

        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String newText = input.getText().toString().trim();
            if (!newText.isEmpty()) {
                dbHelper.updateNote(note.getId(), newText);
                loadNotes(); // обновим список
            }
        });

        builder.setNegativeButton("Отмена", null);
        builder.show();
    }

    private void loadNotes() {
        List<Note> notes = dbHelper.getAllNotes();
        adapter.updateNotes(notes);
    }
}
