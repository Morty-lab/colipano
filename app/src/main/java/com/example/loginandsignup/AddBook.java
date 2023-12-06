package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddBook extends AppCompatActivity implements View.OnClickListener {

    EditText book_name, author;
    Button add_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //bind views
        book_name = findViewById(R.id.BookName);
        author = findViewById(R.id.BookAuthor);
        add_btn = findViewById(R.id.add_button);
        add_btn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        String bookName, bookAuthor;
        bookName = book_name.getText().toString();
        bookAuthor = author.getText().toString();

        bookName = book_name.getText().toString();
        bookAuthor = author.getText().toString();

        //check if values are empty
        if (bookName.isEmpty()) {
            // Display an error on the bookName EditText
            book_name.setError("Book name is required");

        } else if (bookAuthor.isEmpty()) {
            // Display an error on the bookAuthor EditText
            author.setError("Author name is required");
        } else {
            addbook(bookName,bookAuthor);
        }

    }

    private void addbook(String bookName, String bookAuthor) {
        //create hashmap for book
        HashMap<String,Object> booksmap = new HashMap<>();
        booksmap.put("bookName", bookName);
        booksmap.put("bookAuthor", bookAuthor);

        //instantiate database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference booksreference = database.getReference("books");

        String key = booksreference.push().getKey();
        booksmap.put("key",key);

        booksreference.child(key).setValue(booksmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddBook.this, "Added", Toast.LENGTH_SHORT).show();
                book_name.getText().clear();
                author.getText().clear();
                Intent intent = new Intent(getApplicationContext(),Homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }

}