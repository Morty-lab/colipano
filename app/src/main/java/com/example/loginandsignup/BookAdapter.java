package com.example.loginandsignup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {


    Context context;
    ArrayList<Book> booklist;


    public BookAdapter(Context context, ArrayList<Book> booklist) {
        this.context = context;
        this.booklist = booklist;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
    Book book = booklist.get(position);
    holder.book_name.setText(book.getBookName());
    holder.book_author.setText(book.getBookAuthor());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the book from Firebase Realtime Database
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

                booklist.remove(position);
                notifyItemRemoved(position);
                mDatabase.child("books").child(book.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Remove the book from the list and notify the adapter




                        }
                    }
                });
            }
        });
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), UpdateBook.class);
            //pass book key
            intent.putExtra("bookKey", book.getKey());

            // Start UpdateActivity
            v.getContext().startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }
}
