package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<User> users = new ArrayList<>();
    EditText username,password;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        users.add(new User("John Doe","JD","johndoe@mail","password"));



}

    @Override
    public void onClick(View v) {

    }
}