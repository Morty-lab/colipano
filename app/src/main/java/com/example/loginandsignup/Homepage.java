package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recycler;
    ArrayList<Book> books;
    DatabaseReference bookreference;
    BookAdapter adapter;
    FloatingActionButton floating_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recycler = findViewById(R.id.bookRecycler);

        bookreference = FirebaseDatabase.getInstance().getReference("books");
        books = new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this,books);
        recycler.setAdapter(adapter);

        floating_add = findViewById(R.id.FloatAddBook);
        floating_add.setOnClickListener(this);

        bookreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                books.clear();
                try {
                    if (snapshot.exists()) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Book book = dataSnapshot.getValue(Book.class);
                            books.add(book);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        // Handle the case where no data exists
                        // For example, you could show a message to the user
                        Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Log.e("Fucking error", "Failed to fetch data", e);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,AddBook.class);
        startActivity(intent);


    }
}