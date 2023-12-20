package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
import android.widget.TextView;
=======
import android.widget.Button;
>>>>>>> origin/master
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    FloatingActionButton fab;

    ArrayList<Note> notes ;
    DatabaseReference notereference;
=======
>>>>>>> origin/master

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

<<<<<<< HEAD
        text = findViewById(R.id.text);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        notes = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.noteRecycler);
        NoteAdapter noteAdapter = new NoteAdapter(notes);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notereference = FirebaseDatabase.getInstance().getReference("notes");

        notereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    if (snapshot.exists()){
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Note note = dataSnapshot.getValue(Note.class);
                            notes.add(note);
                            String title = note.getnoteTitle();
                            Log.d("NoteManager", "Note title: " + (title != null ? title : "N/A"));

                        }
                        noteAdapter.notifyDataSetChanged();
                    }

                    else{
=======
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
>>>>>>> origin/master
                        Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Log.e("Fucking error", "Failed to fetch data", e);
                }

<<<<<<< HEAD
=======

>>>>>>> origin/master
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
<<<<<<< HEAD

        for (Note element : notes) {
            Log.d("ArrayListContents", element.getnoteContent());
        }
=======
>>>>>>> origin/master
    }

    @Override
    public void onClick(View v) {
<<<<<<< HEAD
        Intent intent = new Intent(this,AddNote.class);
        startActivity(intent);

        Log.d("NoteManager", "yawa");
        for (Note note : notes) {

            Log.d("NoteManager", "Note title: " + note.getnoteTitle());

        }
=======
        Intent intent = new Intent(this,AddBook.class);
        startActivity(intent);


>>>>>>> origin/master
    }
}