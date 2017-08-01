package com.example.jevin.styleomega.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jevin.styleomega.Database.UserDBHandler;
import com.example.jevin.styleomega.R;
import com.idescout.sql.SqlScoutServer;


public class LoginActivity extends AppCompatActivity {

    EditText _email;
    EditText _password;
    String email;
    String password;
    UserDBHandler user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        SqlScoutServer.create(this, getPackageName()); //for live viewing the sqlite database

        initViews();

    }


    public void initViews(){
        _email = (EditText) findViewById(R.id.inputEmail);
        _password = (EditText) findViewById(R.id.inputPassword);

    }

    public void BtnSignInClicked(View view){

        user = new UserDBHandler(this);
        email = _email.getText().toString();
        password = _password.getText().toString();


        if(!email.equals("") || password.equals("")) {
            if (user.isUserExist(email, password)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_invalid_credentials), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.error_fields_empty), Toast.LENGTH_SHORT).show();
        }



    }

    public void BtnSignUpClicked(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }




}
