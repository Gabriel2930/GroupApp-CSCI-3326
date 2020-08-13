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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.transform.Result;

public class CustomerScreen extends AppCompatActivity {

    ConnectionClass connection;
    EditText Comment;
    TextView name;
    Button Running, Here, logout;
    ImageButton CommentButton;
    ProgressDialog progressDialog;
    String wholename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_screen);
        Running = (Button) findViewById(R.id.button);
        Here = (Button)findViewById(R.id.button2);
        logout = (Button)findViewById(R.id.button3);
        Comment = (EditText)findViewById(R.id.editTextName2);
        CommentButton = (ImageButton) findViewById(R.id.imageButton);

        Intent loginname = getIntent();
        String username = loginname.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView DisplayUser = findViewById(R.id.textView);
        DisplayUser.setText(username);
        name = (TextView)findViewById(R.id.textView);
        wholename = username;
        System.out.println(name);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        connection = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        //check on click


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
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout imhere = new logout();
                imhere.execute("");
                finish();
            }
        });
        CommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Commented docomment = new Commented();
                docomment.execute("");
            }
        });

    }

    public class runninglate extends AsyncTask<String, String, String> {

        String email, cname;
        int index;
        int sindex;

        String z = "";
        boolean isSuccess = false;

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
                    String query = "SELECT * FROM Users;";
                    Statement stmnt = con.createStatement();
                    ResultSet rs = stmnt.executeQuery(query);
                    int i = 1;
                    while(rs.next()){

                        cname = rs.getNString("name");
                        email = rs.getNString("email");
                        index = rs.getInt("index");
                        System.out.print(cname);

                        if(cname.equals(wholename) ){
                            sindex = i;
                            System.out.println(i);
                        }
                        i++;

                    }
                    String query2 = "UPDATE `appProject`.`Users` SET `LATE` = 0b1 WHERE (`index` = '"+sindex+"');";
                    Statement stmnt2 = con.createStatement();
                    System.out.println(query2);
                    stmnt2.executeUpdate(query2);
                    z = "register Successful";
                    isSuccess = true;

                }
            } catch (Exception e) {
                isSuccess = false;
                z = "Exception " + e;
                System.out.println(z);
            }
            return z;
        }
        @Override
        protected void onPostExecute(String s) {
            if(isSuccess) {
                Toast.makeText(getBaseContext(), ""+z, Toast.LENGTH_SHORT).show();
            }

            progressDialog.hide();
        }
    }

    public class arrived extends AsyncTask<String, String, String> {

        String email, cname;
        int index;
        int sindex;
        String username = name.getText().toString();
        String z = "";
        boolean isSuccess = false;

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
                    String query1 = "select * from Users";
                    Statement stmnt = con.createStatement();
                    ResultSet rs = stmnt.executeQuery(query1);

                    int i = 1;
                    while(rs.next()){

                        cname = rs.getNString("name");
                        email = rs.getNString("email");
                        index = rs.getInt("index");
                        System.out.print(cname);

                        if(cname.equals(wholename) ){
                            sindex = i;
                            System.out.println(i);
                        }
                        i++;

                    }
                    String query2 = "UPDATE `appProject`.`Users` SET `HERE` = 0b1 WHERE (`index` = '"+sindex+"');";
                    Statement stmnt2 = con.createStatement();
                    System.out.println(query2);
                    stmnt2.executeUpdate(query2);
                    z = "register Successful";
                    isSuccess = true;

                }
            } catch (Exception e) {
                isSuccess = false;
                z = "Exception " + e;
                System.out.println(z);
            }
            return z;
        }
        @Override
        protected void onPostExecute(String s) {
            if(isSuccess) {
                Toast.makeText(getBaseContext(), ""+z, Toast.LENGTH_SHORT).show();
            }
            progressDialog.hide();
        }
    }

    public class Commented extends AsyncTask<String, String, String>{
        String cname,email;
        int index, sindex;

       String Addcomment = Comment.getText().toString();
        String z = "";
        boolean isSuccess = false;

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
                    String query1 = "SELECT * FROM Users";
                    Statement stmnt = con.createStatement();
                    ResultSet rs = stmnt.executeQuery(query1);

                    int i = 1;
                    while(rs.next()){

                        cname = rs.getNString("name");
                        email = rs.getNString("email");
                        index = rs.getInt("index");
                        System.out.print(cname);

                        if(cname.equals(wholename) ){
                            sindex = i;
                            System.out.println(i);
                        }

                        i++;

                    }
                    String query2 = "UPDATE `appProject`.`Users` SET `Comment` = '"+Addcomment+"' WHERE (`index` = '"+sindex+"');";
                    Statement stmnt2 = con.createStatement();
                    System.out.println(query2);
                    stmnt2.executeUpdate(query2);
                    z = "register Successful";
                    isSuccess = true;

                }
            } catch (Exception e) {
                isSuccess = false;
                z = "Exception " + e;
                System.out.println(z);
            }
            return z;
        }
        @Override
        protected void onPostExecute(String s) {
            if(isSuccess) {
                Toast.makeText(getBaseContext(), ""+z, Toast.LENGTH_SHORT).show();
            }
            progressDialog.hide();
        }
    }


    public class logout extends AsyncTask<String, String, String> {


        String email, cname;
        int index;
        int sindex;
        String username = name.getText().toString();
        String z = "";
        boolean isSuccess = false;

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
                    String query1 = "select * from Users";
                    Statement stmnt = con.createStatement();
                    ResultSet rs = stmnt.executeQuery(query1);

                    int i = 1;
                    while(rs.next()){
                        cname = rs.getNString("name");
                        email = rs.getNString("email");
                        index = rs.getInt("index");
                        System.out.print(cname);

                        if(cname.equals(wholename) ){
                            sindex = i;
                            System.out.println(i);
                        }

                        i++;
                    }
                    String query2 = "UPDATE `appProject`.`Users` SET `LATE` = 0b0  WHERE (`index` = '"+sindex+"');";
                    String query3 = "UPDATE `appProject`.`Users` SET `HERE` = 0b0  WHERE (`index` = '"+sindex+"');";
                    String query4 = "UPDATE `appProject`.`Users` SET `Comment` = NULL  WHERE (`index` = '"+sindex+"');";
                    stmnt.executeUpdate(query2);
                    stmnt.executeUpdate(query3);
                    stmnt.executeUpdate(query4);
                    z = "register Successful";
                    isSuccess = true;

                }
            } catch (Exception e) {
                isSuccess = false;
                z = "Exception " + e;
                System.out.println(z);
            }
            return z;
        }
        @Override
        protected void onPostExecute(String s) {
            if(isSuccess) {
                Toast.makeText(getBaseContext(), ""+z, Toast.LENGTH_SHORT).show();
            }

            progressDialog.hide();
        }
    }




}