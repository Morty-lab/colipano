package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {


    TextInputLayout fullNameTextInputLayout, usernameTextInputLayout, emailTextInputLayout, passwordTextInputLayout;
    EditText fullname, username, email, password;
    Button register;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.fullNameEditText);
        username = findViewById(R.id.usernameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String val_fullname = fullname.getText().toString();
        String val_username = username.getText().toString();
        String val_email = email.getText().toString();
        String val_password = password.getText().toString();


        if (validate() == true) {
            Toast.makeText(this,"registered",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Login.class);
            intent.putExtra("fullName", val_fullname);
            intent.putExtra("username", val_username);
            intent.putExtra("email", val_email);
            intent.putExtra("password", val_password);
            intent.putExtra("Intent","register");

            // Start the LoginActivity
            startActivity(intent);

        }else{
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
    }

        private boolean validate () {
            boolean state = true;
            fullNameTextInputLayout = findViewById(R.id.fullNameTextInputLayout);
            usernameTextInputLayout = findViewById(R.id.usernameTextInputLayout);
            emailTextInputLayout = findViewById(R.id.emailTextInputLayout);
            passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);

            String val_fullname = fullname.getText().toString();
            String val_username = username.getText().toString();
            String val_email = email.getText().toString();
            String val_password = password.getText().toString();

            if (val_fullname.isEmpty()) {
                fullNameTextInputLayout.setError("Full Name is required");
                state = false;
            } else {
                fullNameTextInputLayout.setError(null);

            }

            // Validate and highlight errors for Username
            if (val_username.isEmpty()) {
                usernameTextInputLayout.setError("Username is required");
                state = false;
            } else {
                usernameTextInputLayout.setError(null);

            }

            // You can add more validation for email and password fields as needed

            // Validate and highlight errors for Email
            if (val_email.isEmpty()) {
                emailTextInputLayout.setError("Email is required");
                state = false;
            } else if (!isValidEmail(val_email)) {
                emailTextInputLayout.setError("Invalid email address");
                state = false;
            } else {
                emailTextInputLayout.setError(null);

            }

            // Validate and highlight errors for Password
            if (val_password.isEmpty()) {
                passwordTextInputLayout.setError("Password is required");
                state = false;
            } else {
                passwordTextInputLayout.setError(null);


            }

            return state;
        }
        private boolean isValidEmail (String email){
            String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }
