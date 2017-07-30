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


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    UserDBHandler user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        SqlScoutServer.create(this, getPackageName());

        initViews();

    }


    public void initViews(){
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);

    }

    public void BtnSignInClicked(View view){

        user = new UserDBHandler(this);

        if (user.isUserExist(email.getText().toString(), password.getText().toString())) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.error_invalid_credentials), Toast.LENGTH_LONG).show();
        }

        /*if(email != null || password != null) {
            if (user.isUserExist(email.getText().toString(), password.getText().toString())) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_invalid_credentials), Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.error_fields_empty), Toast.LENGTH_LONG).show();
        }
*/


    }

    public void BtnSignUpClicked(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }




}
