package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

public class OwnerScreen extends AppCompatActivity {

    ConnectionClass connnection;
    Button Refreash;
    Button Delete;
    Button Log_Out;
    ScrollView scroll;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Refreash = (Button)findViewById(R.id.button4);
        Delete = (Button)findViewById(R.id.button5);
        Log_Out = (Button)findViewById(R.id.button_LogOut);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_screen);
        /*
        Log_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log_Out imhere = new Log_Out();
                imhere.execute("");


                finish();
            }
        });*/

    }

    /*
    public class Log_Out(View view) {
    }

    /*
    class Connection extends AsyncTask<String, String, String> {

        @Override protected String doInBackground(String... params) {

            return null;
        }


        @Override
        protected void onPostExecute (Void result) {



        }
    }
    */

}