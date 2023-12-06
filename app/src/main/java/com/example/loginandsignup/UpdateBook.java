package com.example.loginandsignup;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateBook extends AppCompatActivity implements View.OnClickListener {

    EditText book_name, author;
    Button update_btn;

    DatabaseReference bookreference = FirebaseDatabase.getInstance().getReference();
    String bookKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        book_name = findViewById(R.id.BookName);
        author = findViewById(R.id.BookAuthor);
        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(this);

        //retrieve book key
        bookKey = getIntent().getStringExtra("bookKey");

        bookreference.child("books").child(bookKey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Book book = snapshot.getValue(Book.class);

                book_name.setText(book.getBookName());
                author.setText(book.getBookAuthor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Database Error", "loadBook:onCancelled", error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        String newBookName = book_name.getText().toString();
        String newBookAuthor = author.getText().toString();
        bookKey = getIntent().getStringExtra("bookKey");

        bookreference.child("books").child(bookKey).updateChildren(new HashMap<String, Object>() {{
            put("bookName", newBookName);
            put("bookAuthor", newBookAuthor);
        }});

        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
        finish();
    }
}