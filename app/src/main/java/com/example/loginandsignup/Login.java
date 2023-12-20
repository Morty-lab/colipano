package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {


    EditText username,password;
    TextInputLayout user_name,pass;
    Button login;
    private ArrayList<User> userList = new ArrayList<>();
    DatabaseReference reference;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        reference = FirebaseDatabase.getInstance().getReference().child("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();

                for(DataSnapshot userSnap: snapshot.getChildren()){
                    User user = userSnap.getValue(User.class);

                    userList.add(user);
                    Log.d("user",user.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public boolean isLoginValid(String username, String password) {
        // Check if username and password are not empty
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Check if the username exists in the user list
        User matchedUser = null;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                matchedUser = user;
                break;
            }
        }

        // If the username exists, verify if the password matches
        if (matchedUser != null && matchedUser.getPassword().equals(password)) {
            return true; // Valid login
        }

        return false; // Invalid login
    }

    @Override
    public void onClick(View v) {
        String uname = username.getText().toString();
        String pword = password.getText().toString();

        if (isLoginValid(uname, pword)) {
            //clear text utils
            username.setText("");
            password.setText("");
            // Valid login, proceed to a new activity or perform other actions
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        } else {
            // Invalid login, show an error message
            user_name = findViewById(R.id.unametextinputlayout);
            pass = findViewById(R.id.pwordtextinputlayout);
            user_name.setError("Invalid Username");
            pass.setError("Invalid Password");
//            Toast.makeText(this, "Invalid username or password. Please check and try again.", Toast.LENGTH_SHORT).show();
        }
    }
}