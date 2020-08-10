package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.*;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.*;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.appproject";

    //initialize Variables
    ConnectionClass connection;
    EditText email, password, name;
    Button register;
    Button logIn;
    Switch ownerSwitch;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        name = (EditText) findViewById(R.id.editTextName);
        register = (Button) findViewById(R.id.button_Register);
        logIn = (Button) findViewById(R.id.button_LogIn);
        ownerSwitch = (Switch) findViewById(R.id.trigger);

        connection = new ConnectionClass();
        progressDialog = new ProgressDialog(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtn doregister = new registerBtn();
                doregister.execute("");
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInBtn dologin = new logInBtn();
                dologin.execute("");
            }
        });

    }

    public class registerBtn extends AsyncTask<String, String, String>
    {
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();
        String nameStr = name.getText().toString();
        Boolean switchState = ownerSwitch.isChecked();
        String z ="";
        boolean isSuccess = false;
        @Override
        protected void onPreExecute(){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        protected String doInBackground(String... strings) {
            if(emailStr.trim().equals("") || passwordStr.trim().equals("") || nameStr.trim().equals(""))
                z = "Please enter all fields";
            else {
                try {
                    Connection con = connection.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        if(!switchState) {
                            String query = "INSERT INTO Users (`email`, `password`, `name`, `isOwner`) VALUES ('"+emailStr+"', '"+passwordStr+"', '"+nameStr+"',  "+0+");";
                            System.out.println(query);
                            Statement stmnt = con.createStatement();
                            stmnt.executeUpdate(query);
                        }

                        else
                        {
                            String query = "INSERT INTO Users (`email`, `password`, `name`, `isOwner`) VALUES ('"+emailStr+"', '"+passwordStr+"', '"+nameStr+"', "+1+");";
                            System.out.println(query);
                            Statement stmnt = con.createStatement();
                            stmnt.executeUpdate(query);
                        }

                        z = "Register Successful";
                        isSuccess = true;
                    }
                }catch(Exception e) {
                    isSuccess = false;
                    z = "Exception " +e;
                    System.out.println(z);
                }
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


    public class logInBtn extends AsyncTask<String, String, String>
    {
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();
        String nameStr = name.getText().toString();
        Boolean switchState = ownerSwitch.isChecked();
        String z ="";

        String emailCheck, passCheck, nameCheck;
        boolean loginSuccess = false;

        boolean isOwner;
        boolean isSuccess = false;

        @Override
        protected void onPreExecute(){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        protected String doInBackground(String... strings) {
            if(emailStr.trim().equals("") || passwordStr.trim().equals(""))
                z = "Please enter all fields";
            else {
                try {
                    Connection con = connection.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        String query = "SELECT * FROM Users;";
                        System.out.println(query);
                        Statement stmnt = con.createStatement();
                        ResultSet rs = stmnt.executeQuery(query);

                        while(rs.next() && loginSuccess == false)
                        {
                            emailCheck = rs.getNString("email");
                            passCheck = rs.getNString("password");
                            nameCheck = rs.getNString("name");
                            isOwner = rs.getBoolean("isOwner");

                            if (emailCheck.equals(emailStr)  && passCheck.equals(passwordStr) && nameCheck.equals(nameStr) && switchState == isOwner)
                            {
                                loginSuccess = true;
                            }

                        }

                        z = "Login Successful";
                        isSuccess = true;
                    }
                }catch(Exception e) {
                    isSuccess = false;
                    z = "Exception " +e;
                    System.out.println(z);
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            if(isSuccess) {
                Toast.makeText(getBaseContext(), ""+z, Toast.LENGTH_SHORT).show();

                if(!isOwner) {
                    Intent customerIntent = new Intent(MainActivity.this, CustomerScreen.class);
                    startActivity(customerIntent);
                }

                else if(isOwner)
                {
                    Intent ownerIntent = new Intent(MainActivity.this, OwnerScreen.class);
                    startActivity(ownerIntent);

                }
            }

            progressDialog.hide();
        }

    }


}