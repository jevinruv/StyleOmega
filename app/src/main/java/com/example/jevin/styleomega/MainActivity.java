package com.example.jevin.styleomega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



    }

    public void BtnSignInClicked(View view){
        Intent intent = new Intent(this, HomeActivity.class);
      startActivity(intent);

    }

    public void BtnSignUpClicked(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }




}
