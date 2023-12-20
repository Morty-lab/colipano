package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    FloatingActionButton fab;

    ArrayList<Note> notes ;
    DatabaseReference notereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

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

        for (Note element : notes) {
            Log.d("ArrayListContents", element.getnoteContent());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,AddNote.class);
        startActivity(intent);

        Log.d("NoteManager", "yawa");
        for (Note note : notes) {

            Log.d("NoteManager", "Note title: " + note.getnoteTitle());

        }
    }
}