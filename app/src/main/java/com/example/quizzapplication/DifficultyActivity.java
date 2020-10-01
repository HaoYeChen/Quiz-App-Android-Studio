package com.example.quizzapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultyActivity extends AppCompatActivity {

    Button Easybtn, Hardbtn, Hellbtn, Tilbagebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);







        /*Easybtn = (Button)findViewById(R.id.Easybtn);
        Hardbtn = (Button)findViewById(R.id.Hardbtn);
        Hellbtn = (Button)findViewById(R.id.Hellbtn);*/
        Tilbagebtn = (Button)findViewById(R.id.Tilbagebtn);




        Tilbagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });
    }
}