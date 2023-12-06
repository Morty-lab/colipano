package com.example.loginandsignup;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends RecyclerView.ViewHolder {

    TextView book_name,book_author;
    ImageButton deleteButton;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        book_name = itemView.findViewById(R.id.book_name);
        book_author = itemView.findViewById(R.id.author_name);
        deleteButton = itemView.findViewById(R.id.delete_button);
    }
}
