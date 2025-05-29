package com.example.fitass.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitAss.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void updateNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textNote.setText(note.getText());
        holder.textDate.setText(note.getDate());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(notes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textNote, textDate;

        NoteViewHolder(View itemView) {
            super(itemView);
            textNote = itemView.findViewById(com.example.fitAss.R.id.textNote);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}

