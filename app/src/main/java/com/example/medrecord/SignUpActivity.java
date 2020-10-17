package com.example.medrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    Button createAccount;
    EditText username, password, confirmPassword;
    private String usernameText, passwordText, confirmpasswordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        createAccount = (Button) findViewById(R.id.buttonCreateAccount);
        username = (EditText) findViewById(R.id.editTextPassword);
        password = (EditText) findViewById(R.id.editTextConfirmPassword);
        confirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameText = username.getText().toString();
                passwordText = password.getText().toString();
                confirmpasswordText = confirmPassword.getText().toString();
                if((passwordText.compareTo(confirmpasswordText)) == 0){
                    SharedPreferences.Editor Ed = login.edit();
                    Ed.putString("username", usernameText);
                    Ed.putString("password", passwordText);
                    Ed.commit();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                }
                else{
                    //snackbar: the passwords do not match
                }
            }
        });
    }
}