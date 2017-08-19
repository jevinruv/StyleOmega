package com.example.jevin.styleomega.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jevin.styleomega.Database.DBHandler;
import com.example.jevin.styleomega.Model.User;
import com.example.jevin.styleomega.Others.CommonMethods;
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

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
    }

    public void initViews() {
        _nic = (EditText) findViewById(R.id.inputNIC);
        _name = (EditText) findViewById(R.id.inputName);
        _email = (EditText) findViewById(R.id.inputEmail);
        _password = (EditText) findViewById(R.id.inputPassword);
    }

    public void BtnRegisterClicked(View view) {

        dbHandler = new DBHandler(this);

        nic = _nic.getText().toString().trim();
        name = _name.getText().toString().trim();
        email = _email.getText().toString().trim();
        password = _password.getText().toString().trim();

        if (nic.equals("") || name.equals("") || email.equals("") || password.equals("")) {
            CommonMethods.displaySnackbar(view, R.string.error_fields_empty);
        } else {
            if (CommonMethods.isEmailValid(email) && CommonMethods.isNicValid(nic)) {

                if (dbHandler.viewUser(nic) == null &&  // checks for existing nic
                        dbHandler.viewUserAny("email", email) == null) {    // checks for existing email

                    User newUser = new User(nic, name, password, email);
                    dbHandler.addUser(newUser);

                    CommonMethods.displaySnackbar(view, R.string.successfully_registered);
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    CommonMethods.displaySnackbar(view, R.string.error_user_exists);
                }

            } else {
                CommonMethods.displaySnackbar(view, R.string.error_invalid_details);
            }
        }
    }

}
