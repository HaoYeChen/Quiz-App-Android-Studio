package com.example.quizzapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button Spilbtn, Indstillingerbtn, Profilbtn,Scorebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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