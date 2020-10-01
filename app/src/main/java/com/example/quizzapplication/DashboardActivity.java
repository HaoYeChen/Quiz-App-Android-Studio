package com.example.quizzapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.quizzapplication.ConnectionClass.Classes;
import static com.example.quizzapplication.ConnectionClass.password;
import static com.example.quizzapplication.ConnectionClass.url;
import static com.example.quizzapplication.ConnectionClass.username;

public class DashboardActivity extends AppCompatActivity {

     TextView textView, textViewWelcome;
    private Connection con = null;
    Button Spilbtn, Indstillingerbtn, Profilbtn,Scorebtn;
    Statement statement = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        textView = findViewById(R.id.textView);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        //SQL connection
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        //Strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            con = DriverManager.getConnection(url, username,password);
            textView.setText("CONNECTED");
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM BRUGERS;");
            while(rs.next()){
                textViewWelcome.setText("Welcome"+" "+rs.getString("Brugernavn")+" "+(rs.getString("BrugerId")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            textView.setText("FAILURE");
        }



        Spilbtn = (Button)findViewById(R.id.Spilbtn);
        Indstillingerbtn = (Button)findViewById(R.id.Indstillingerbtn);
        Profilbtn = (Button)findViewById(R.id.Profilbtn);
        Scorebtn = (Button)findViewById(R.id.Scorebtn);

        Spilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,DifficultyActivity.class);
                startActivity(i);
            }
        });

        Indstillingerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,ProfilActivity.class);
                startActivity(i);
            }
        });

        Profilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,ProfilActivity.class);
                startActivity(i);
            }
        });

        Scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,ProfilActivity.class);
                startActivity(i);
            }
        });

    }
}