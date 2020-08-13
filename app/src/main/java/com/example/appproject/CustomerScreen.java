package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CustomerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_screen);

        Intent logIn = getIntent();

        String userName = logIn.getStringExtra(MainActivity.NAME_MESSAGE);
        String userEmail = logIn.getStringExtra(MainActivity.EMAIL_MESSAGE);

        TextView DisplayUser = findViewById(R.id.textView_User);
        TextView DisplayEmail = findViewById(R.id.textView_Email);

        DisplayUser.setText("Hello " + userName);
        DisplayEmail.setText("Your email is " + userEmail);
    }


}