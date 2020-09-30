package com.example.quizzapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.quizzapplication.ConnectionClass.Classes;
import static com.example.quizzapplication.ConnectionClass.password;
import static com.example.quizzapplication.ConnectionClass.url;
import static com.example.quizzapplication.ConnectionClass.username;


public class SignUpActivity extends AppCompatActivity {

    //initialisere variabel
    private TextView textView;
    EditText Fornavn,Efternavn,Brugernavn,Email,Kodeord;
    Button Registerbtn, Tilbagebtn;
    Statement stmt;
    private static String RolleId ="2";
    private Connection con = null;

    AwesomeValidation awesomeValidation;

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

        //tildele variabel
        Fornavn = findViewById(R.id.editTextFornavn);
        Fornavn.setTextColor(Color.WHITE);
        Fornavn.setHintTextColor(Color.WHITE);
        Efternavn = findViewById(R.id.editTextEfternavn);
        Efternavn.setTextColor(Color.WHITE);
        Efternavn.setHintTextColor(Color.WHITE);
        Brugernavn = findViewById(R.id.editTextBrugernavn);
        Brugernavn.setTextColor(Color.WHITE);
        Brugernavn.setHintTextColor(Color.WHITE);
        Email = findViewById(R.id.editTextEmail);
        Email.setTextColor(Color.WHITE);
        Email.setHintTextColor(Color.WHITE);
        Kodeord = findViewById(R.id.editTextKodeord);
        Kodeord.setTextColor(Color.WHITE);
        Kodeord.setHintTextColor(Color.WHITE);
        Registerbtn = findViewById(R.id.Registerbtn);
        Tilbagebtn = findViewById(R.id.Tilbagebtn);
        //status = findViewById(R.id.editTextStatus);

        //initialisere validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //Tilføj validation for fornavn, efternavn,brugernavn,email og kodeord
        awesomeValidation.addValidation(this,R.id.editTextFornavn,
                RegexTemplate.NOT_EMPTY,R.string.invalid_Fornavn);
        awesomeValidation.addValidation(this,R.id.editTextEfternavn,
                RegexTemplate.NOT_EMPTY,R.string.invalid_Efternavn);
        awesomeValidation.addValidation(this,R.id.editTextBrugernavn,
                RegexTemplate.NOT_EMPTY,R.string.invalid_Brugernavn);
        awesomeValidation.addValidation(this,R.id.editTextEmail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_Email);
        awesomeValidation.addValidation(this,R.id.editTextKodeord,
                ".{6,}",R.string.invalid_Kodeord);

        //registrere ny bruger
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(),"Form validation succesfully..", Toast.LENGTH_SHORT).show();
                    new registeruser().execute("");
                }else{
                    Toast.makeText(getApplicationContext(),"Validation failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Tilbage til LoginActivity
        Tilbagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
    public class registeruser extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

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
