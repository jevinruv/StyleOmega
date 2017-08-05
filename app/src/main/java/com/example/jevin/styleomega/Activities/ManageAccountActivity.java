package com.example.jevin.styleomega.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.jevin.styleomega.Database.UserDBHandler;
import com.example.jevin.styleomega.Model.User;
import com.example.jevin.styleomega.R;

public class ManageAccountActivity extends AppCompatActivity {
    Switch switchEdit;
    EditText txtNic;
    EditText txtName;
    ImageButton  btnName;
    EditText txtEmail;
    ImageButton  btnEmail;
    EditText txtPassword;
    ImageButton  btnPassword;

    String userNIC;
    String nic;
    String name;
    String email;
    String password;

    UserDBHandler userDBHandler;
    SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userNIC = prefs.getString("nic", null);
        initViews();
    }

    public void initViews(){
        switchEdit = (Switch)  findViewById(R.id.switchEdit);
        txtNic = (EditText) findViewById(R.id.inputNIC);
        txtName = (EditText) findViewById(R.id.inputName);
        btnName = (ImageButton) findViewById(R.id.btnName);
        txtEmail = (EditText) findViewById(R.id.inputEmail);
        btnEmail = (ImageButton) findViewById(R.id.btnEmail);
        txtPassword = (EditText) findViewById(R.id.inputPassword);
        btnPassword = (ImageButton) findViewById(R.id.btnPassword);

        buttonEnable(false);
        switchEditToggled();

        userDBHandler = new UserDBHandler(this);
        User user = userDBHandler.viewUser(userNIC);
        txtNic.setText(user.getNic());
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());


    }

    public void switchEditToggled(){

        switchEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonEnable(true);
                    txtNic.setEnabled(false);
                }
                else{
                    buttonEnable(false);
                }
            }
        });
    }

    public void buttonEnable(boolean flag){
        txtNic.setEnabled(flag);
        txtName.setEnabled(flag);
        btnName.setEnabled(flag);
        txtEmail.setEnabled(flag);
        btnEmail.setEnabled(flag);
        txtPassword.setEnabled(flag);
        btnPassword.setEnabled(flag);

    }

    public void displayToast(int message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void btnNameClicked(View view){
        name = txtName.getText().toString();

        if(name.equals("")){
            displayToast(R.string.error_fields_empty);
        }
        else{
            userDBHandler.updateUser(userNIC,"name",name);
            displayToast(R.string.successfully_updated);
            finish();
            startActivity(getIntent());
        }

    }


}
