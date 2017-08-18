package com.example.jevin.styleomega.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.jevin.styleomega.Database.DBHandler;
import com.example.jevin.styleomega.Model.User;
import com.example.jevin.styleomega.Others.CommonMethods;
import com.example.jevin.styleomega.R;

public class ManageAccountActivity extends BaseActivity implements View.OnClickListener {
    Switch switchEdit;
    EditText txtNic;
    EditText txtName;
    ImageButton btnName;
    EditText txtEmail;
    ImageButton btnEmail;
    EditText txtPassword;
    ImageButton btnPassword;

    String userNIC;
    String nic;
    String name;
    String email;
    String password;

    DBHandler dbHandler;
    SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_manage_account, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_manage_account);

        prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userNIC = prefs.getString("nic", null);
        initViews();
        setTitle("Manage Account");
    }

    public void initViews() {
        switchEdit = (Switch) findViewById(R.id.switchEdit);
        txtNic = (EditText) findViewById(R.id.inputNIC);
        txtName = (EditText) findViewById(R.id.inputName);
        btnName = (ImageButton) findViewById(R.id.btnName);
        txtEmail = (EditText) findViewById(R.id.inputEmail);
        btnEmail = (ImageButton) findViewById(R.id.btnEmail);
        txtPassword = (EditText) findViewById(R.id.inputPassword);
        btnPassword = (ImageButton) findViewById(R.id.btnPassword);


        btnName.setOnClickListener(this);
        btnEmail.setOnClickListener(this);


        buttonEnable(false); //disable edit button at startup
        switchEditToggled();

        dbHandler = new DBHandler(this);
        User user = dbHandler.viewUser(userNIC);
        txtNic.setText(user.getNic());
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());


    }

    public void switchEditToggled() {

        switchEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonEnable(true);
                    txtNic.setEnabled(false);
                } else {
                    buttonEnable(false);
                }
            }
        });
    }

    public void buttonEnable(boolean flag) {
        txtNic.setEnabled(flag);
        txtName.setEnabled(flag);
        btnName.setEnabled(flag);
        txtEmail.setEnabled(flag);
        btnEmail.setEnabled(flag);
        txtPassword.setEnabled(flag);
        btnPassword.setEnabled(flag);

    }

   /* public void displayToast(int message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }*/


    public void btnDoneClicked(View view) {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        String option = null;
        String value = null;

        switch (v.getId()) {
            case R.id.btnName:
                option = "name";
                value = txtName.getText().toString().trim();
                break;
            case R.id.btnEmail:
                option = "email";
                value = txtEmail.getText().toString().trim();
                break;
            case R.id.btnPassword:
                option = "password";
                value = txtPassword.getText().toString().trim();
                break;
            default:
                break;
        }

        if (value.equals("")) {
            commonMethods.displayToast(this, R.string.error_fields_empty);
        }
        else if (option.equals("email") && commonMethods.isEmailValid(value) == false) {
            CommonMethods.displayToast(this, R.string.error_invalid_details);
        }
        else {
            dbHandler.updateUser(userNIC, option, value);
            CommonMethods.displayToast(this, R.string.successfully_updated);
            finish();
            startActivity(getIntent()); //refresh activity for changes
        }

    }
}
