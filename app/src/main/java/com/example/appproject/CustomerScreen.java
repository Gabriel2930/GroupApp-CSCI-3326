package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Statement;

public class CustomerScreen extends AppCompatActivity {

    ConnectionClass connection;
    TextView name;
    Button Running, Here;
    ProgressDialog progressDialog;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_screen);
        Running = (Button) findViewById(R.id.button);
        Here = (Button)findViewById(R.id.button2);

        Intent loginname = getIntent();
        String username = loginname.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView DisplayUser = findViewById(R.id.textView);
        DisplayUser.setText(username);
        name = (TextView)findViewById(R.id.textView);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        connection = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        Running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runninglate dorun = new runninglate();
                dorun.execute("");
            }
        });
        Here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrived imhere = new arrived();
                imhere.execute("");
            }
        });

    }

    public class runninglate extends AsyncTask<String, String, String>
    {
        String username = name.getText().toString();
        String z = "";

        @Override
        protected void onPreExecute(){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        protected String doInBackground(String... strings) {

            try {
                Connection con = connection.CONN();
                if (con == null) {
                    z = "Please check your internet connection";
                } else {
                    String query = "UPDATE `appProject`.`USER` SET `LATE` = '1' WHERE (`name` = '" + username + "');";
                    System.out.println(query);
                    Statement stmnt = con.createStatement();
                    stmnt.executeUpdate(query);

                }
            } catch (Exception e) {
                z = "Exception " + e;
                System.out.println(z);
            }
            return z;
        }
    }


    public class arrived extends AsyncTask<String, String, String> {
        String username = name.getText().toString();
        String z = "";

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        protected String doInBackground(String... strings) {

            try {
                Connection con = connection.CONN();
                if (con == null) {
                    z = "Please check your internet connection";
                } else {
                    String query = "UPDATE `appProject`.`USER` SET `HERE` = '1' WHERE (`name` = '" + username + "');";
                    System.out.println(query);
                    Statement stmnt = con.createStatement();
                    stmnt.executeUpdate(query);

                }
            } catch (Exception e) {
                z = "Exception " + e;
                System.out.println(z);
            }
            return z;
        }
    }




}