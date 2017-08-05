package com.example.jevin.styleomega.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jevin.styleomega.Database.UserDBHandler;
import com.example.jevin.styleomega.Model.User;
import com.example.jevin.styleomega.R;

public class RegisterActivity extends AppCompatActivity {

    EditText _nic;
    EditText _name;
    EditText _email;
    EditText _password;
    String nic;
    String name;
    String email;
    String password;

    UserDBHandler userDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
    }

    public void initViews(){
        _nic = (EditText) findViewById(R.id.inputNIC);
        _name = (EditText) findViewById(R.id.inputName);
        _email = (EditText) findViewById(R.id.inputEmail);
        _password = (EditText) findViewById(R.id.inputPassword);
    }

    public void BtnRegisterClicked(View view){

        userDBHandler = new UserDBHandler(this);

        nic = _nic.getText().toString();
        name = _name.getText().toString();
        email = _email.getText().toString();
        password = _password.getText().toString();

        if(nic.equals("") || name.equals("") || email.equals("") || password.equals("")){
            displayToast(R.string.error_fields_empty);        }
        else{
            if(userDBHandler.viewUser(nic) == null) {

                User newUser = new User(nic, name, password, email);
                userDBHandler.addUser(newUser);

                displayToast(R.string.successfully_registered);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            else{
                displayToast(R.string.error_user_exists);
            }

        }

    }


    public void displayToast(int message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
