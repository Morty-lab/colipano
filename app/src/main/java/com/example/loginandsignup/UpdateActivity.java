package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton cancelBTN,addBTN;
    EditText contentET,titleET;
    DatabaseReference notereference = FirebaseDatabase.getInstance().getReference();
    String notekey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        cancelBTN = findViewById(R.id.cancel);
        cancelBTN.setOnClickListener(this);
        addBTN = findViewById(R.id.save);
        addBTN.setOnClickListener(this);

        titleET = findViewById(R.id.note_title);
        contentET = findViewById(R.id.note_content);


        notekey = getIntent().getStringExtra("notekey");

        notereference.child("notes").child(notekey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Note note = snapshot.getValue(Note.class);

                titleET.setText(note.getnoteTitle());
                contentET.setText(note.getnoteContent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Database Error", "loadBook:onCancelled", error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel){
            finish();
        } else if (v.getId() == R.id.save) {
            String newTitle = titleET.getText().toString();
            String newContent = contentET.getText().toString();
            notekey = getIntent().getStringExtra("notekey");

            notereference.child("notes").child(notekey).updateChildren(new HashMap<String, Object>() {{
                put("noteTitle", newTitle);
                put("noteContent", newContent);
            }});

            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
            finish();



        }
    }
}