package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

public class OwnerScreen extends AppCompatActivity {

    ConnectionClass connnection;
    Button Refreash;
    Button Delete;
    ScrollView scroll;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Refreash = (Button)findViewById(R.id.button4);
        Delete = (Button)findViewById(R.id.button5);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_screen);

    }
}