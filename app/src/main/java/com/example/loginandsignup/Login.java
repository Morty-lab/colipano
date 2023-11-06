package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {


    EditText username,password;
    Button login;
    private List<User> userList = new ArrayList<>();
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

        User newUser = new User("John Doe", "johndoe", "johndoe@example.com", "password123");

        // Add the User object to the userList
        userList.add(newUser);
        String Intent = getIntent().getStringExtra("Intent").toString();
        System.out.println(Intent);

        if ( "register".equals(Intent)) {
            String fullName = getIntent().getStringExtra("fullName");
            String username = getIntent().getStringExtra("username");
            String email = getIntent().getStringExtra("email");
            String password = getIntent().getStringExtra("password");
            // Create a new user
            User user = new User(fullName, username, email, password);

            // Add the user to the list
            userList.add(user);
            for (User i : userList) {
                Log.d("UserList", "Full Name: " + i.getFullName());
                Log.d("UserList", "Username: " + i.getUsername());
                Log.d("UserList", "Email: " + i.getEmail());
                Log.d("UserList", "Password: " + i.getPassword());
            }

        } else{
            Toast.makeText(this,"main",Toast.LENGTH_SHORT).show();
            Log.d("main",Intent);
            for (User i : userList) {
                Log.d("UserList", "Full Name: " + i.getFullName());
                Log.d("UserList", "Username: " + i.getUsername());
                Log.d("UserList", "Email: " + i.getEmail());
                Log.d("UserList", "Password: " + i.getPassword());
            }
        }


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
            // Valid login, proceed to a new activity or perform other actions
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        } else {
            // Invalid login, show an error message
            Toast.makeText(this, "Invalid username or password. Please check and try again.", Toast.LENGTH_SHORT).show();
        }
    }
}