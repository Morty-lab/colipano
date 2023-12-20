package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AddNote extends AppCompatActivity implements View.OnClickListener {
    ImageButton cancelBTN,addBTN;
    EditText contentET,titleET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        cancelBTN = findViewById(R.id.cancel);
        cancelBTN.setOnClickListener(this);
        addBTN = findViewById(R.id.save);
        addBTN.setOnClickListener(this);

        titleET = findViewById(R.id.note_title);
        contentET = findViewById(R.id.note_content);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel){
            finish();
        } else if (v.getId() == R.id.save) {
            String title,content,key;
            title = titleET.getText().toString();
            content = contentET.getText().toString();
            key = generateRandomKey();

            Note new_note = new Note(key,title,content);
            Intent intent = new Intent(getApplicationContext(), Homepage.class);
            addnote(title,content,key);


            startActivity(intent);



        }
    }

    private void addnote(String noteTitle, String notecontent, String key) {
        //create hashmap for book

        HashMap<String,Object> booksmap = new HashMap<>();
        booksmap.put("key", key);
        booksmap.put("noteTitle", noteTitle);
        booksmap.put("noteContent", notecontent);


        //instantiate database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notereference = database.getReference("notes");



        notereference.child(key).setValue(booksmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddNote.this, "Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Homepage.class);
                startActivity(intent);
                finish();
            }


        });
    }



    public String generateRandomKey() {
        Random random = new Random();
        String key = "";
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(10); // Generate a random number between 0 and 9
            key += randomNumber; // Append the random number to the key
        }
        return key;
    }

}