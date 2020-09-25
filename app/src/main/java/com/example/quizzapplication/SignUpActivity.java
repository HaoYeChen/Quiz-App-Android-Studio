package com.example.quizzapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.quizzapplication.ConnectionClass.Classes;
import static com.example.quizzapplication.ConnectionClass.password;
import static com.example.quizzapplication.ConnectionClass.url;
import static com.example.quizzapplication.ConnectionClass.username;


public class SignUpActivity extends AppCompatActivity {

    //textView
    private TextView textView;
    EditText Fornavn,Efternavn,Brugernavn,Email,Kodeord;
    Button registerbtn;
    //TextView status;
    Statement stmt;
    private static String RolleId ="2";
    private Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //SQL connection
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);
        //Strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            con = DriverManager.getConnection(url, username,password);
            textView.setText("CONNECTED");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            textView.setText("FAILURE");
        }


        Fornavn = findViewById(R.id.editTextFornavn);
        Efternavn = findViewById(R.id.editTextEfternavn);
        Brugernavn = findViewById(R.id.editTextBrugernavn);
        Email = findViewById(R.id.editTextEmail);
        Kodeord = findViewById(R.id.editTextKodeord);
        registerbtn = findViewById(R.id.editTextRegisterbtn);
        //status = findViewById(R.id.editTextStatus);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new registeruser().execute("");
            }
        });
    }
    public class registeruser extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;



        /*@Override
        protected void onPreExecute() {
            status.setText("Sending Data to Database");
        }*/

        /*@Override
        protected void onPostExecute(String s) {
            //status.setText("Registration Successful");
            *//*Fornavn.setText("");
            Efternavn.setText("");
            Brugernavn.setText("");
            Email.setText("");
            Kodeord.setText("");*//*
            if (Fornavn.getText().toString().trim().isEmpty()){
                Fornavn.setError("FIELD CANNOT BE EMPTY");
                Fornavn.requestFocus();
                Fornavn.equals("");
            }
            if (Efternavn.getText().toString().trim().isEmpty()){
                Efternavn.setError("FIELD CANNOT BE EMPTY");
                Efternavn.requestFocus();
                Efternavn.equals("");
            }
            if (Brugernavn.getText().toString().trim().isEmpty()){
                Brugernavn.setError("FIELD CANNOT BE EMPTY");
                Brugernavn.requestFocus();
                Brugernavn.equals("");
            }
            if (Email.getText().toString().trim().isEmpty()){
                Email.setError("FIELD CANNOT BE EMPTY");
                Email.requestFocus();
                Email.equals("");
            }
            if (Kodeord.getText().toString().trim().isEmpty()){
                Kodeord.setError("FIELD CANNOT BE EMPTY");
                Kodeord.requestFocus();
                Kodeord.equals("");
            }

           *//* Fornavn.getText().toString().trim();
            Efternavn.getText().toString().trim();
            Brugernavn.getText().toString().trim();
            Email.getText().toString().trim();
            Kodeord.getText().toString().trim();
            if (Fornavn.length()==0){
                Fornavn.requestFocus();
                Fornavn.setError("FIELD CANNOT BE EMPTY");}
            if (Efternavn.length()==0){
                Efternavn.requestFocus();
                Efternavn.setError("FIELD CANNOT BE EMPTY");}
            if (Brugernavn.length()==0){
                Brugernavn.requestFocus();
                Brugernavn.setError("FIELD CANNOT BE EMPTY");}
            if (Email.length()==0){
                Email.requestFocus();
                Email.setError("FIELD CANNOT BE EMPTY");}
            if (Kodeord.length()==0){
                Kodeord.requestFocus();
                Kodeord.setError("FIELD CANNOT BE EMPTY");}
            if (Fornavn.isEmpty)*//*

        }*/

        @Override
        protected String doInBackground(String... strings) {
            try{

                if(con == null){
                    z = "Check Your Internet Connection";
                }
                else{
                    Class.forName(Classes);
                    con = DriverManager.getConnection(url, username,password);

                    String sql = "INSERT INTO Brugers (Fornavn,Efternavn,Brugernavn,Email,Kodeord,RolleId) VALUES ('"+Fornavn.getText()+"','"+Efternavn.getText()+"','"+Brugernavn.getText()+"','"+Email.getText()+"','"+Kodeord.getText()+"','"+RolleId+"')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);

                }

            }catch (Exception e){
                isSuccess = false;
                z = e.getMessage();

            }

            return z;
        }
    }
}
