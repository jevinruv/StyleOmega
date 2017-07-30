package com.example.jevin.styleomega.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.jevin.styleomega.Database.UserDBHandler;
import com.example.jevin.styleomega.Model.User;
import com.example.jevin.styleomega.R;

public class RegisterActivity extends AppCompatActivity {

    EditText nic;
    EditText email;
    EditText password;

    UserDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nic = (EditText) findViewById(R.id.inputNIC);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        dbHandler = new UserDBHandler(this);
    }

    public void BtnRegisterClicked(View view){
        User user = new User(nic.getText().toString(),email.getText().toString(),password.getText().toString());
        dbHandler.addUser(user);
    }
}
