package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {




    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.loginButton){
            Toast.makeText(this,"pressed",Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(this, Login.class);
            intent.putExtra("Intent","Main");
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);        }
        if (view.getId() == R.id.registerButton){
            Toast.makeText(this,"pressed",Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(this, Register.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }
}