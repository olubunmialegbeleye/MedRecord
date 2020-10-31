package com.example.medrecord;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    Button signIn;
    EditText username, password;
    String usernameText, passwordText, usernameTRUE, passwordTRUE;
    DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        usernameTRUE = login.getString("username", null);
        passwordTRUE = login.getString("password", null);
        signIn = (Button) findViewById(R.id.buttonSignIn);
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        signIn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                usernameText = username.getText().toString();
                passwordText = password.getText().toString();
                if((usernameTRUE.compareTo(usernameText) == 0) && (passwordTRUE.compareTo(passwordText) == 0)){
                    //check if db is empty first.
                    if(dbHelper.getPatientList().isEmpty()){
                        startActivity(new Intent(SignInActivity.this, EmptyRecordActivity.class));
                    }
                    else {
                        startActivity(new Intent(SignInActivity.this, RecordManagerActivity.class));
                    }
                    finish();
                }
                else{

                }
            }
        });
    }
}