package com.example.appproject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.*;

public class ConnectionClass {
    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://35.202.100.122:3306/appProject";
    String un = "root";
    String password = "1234";


    @SuppressLint("Newdpi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try {
            Class.forName(classs);

            conn = DriverManager.getConnection(url, un, password);

            //conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR2", e.getMessage());
        }
        return conn;
    }
}

