package com.example.loginandsignup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    ImageButton deleteButton;
    Context context;

    public NoteViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title);
        deleteButton = itemView.findViewById(R.id.deleteIcon);

    }

    public void bind(Note note) {
        titleTextView.setText(note.getnoteTitle());
    }
}
