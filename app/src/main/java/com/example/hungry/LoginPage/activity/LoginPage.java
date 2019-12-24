package com.example.hungry.LoginPage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hungry.HomePage;
import com.example.hungry.R;

public class LoginPage extends AppCompatActivity {

    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

         findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(LoginPage.this, HomePage.class);
                 startActivity(i);
             }
         });
    }
}
