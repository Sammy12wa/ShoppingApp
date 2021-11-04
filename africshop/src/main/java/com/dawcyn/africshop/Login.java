package com.dawcyn.africshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dawcyn.common.Shop;

public class Login extends AppCompatActivity {
    EditText etUsername,etPassword;
    ImageView imgUsername,imgPassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        imgUsername = (ImageView) findViewById(R.id.imgUsername);
        imgPassword = (ImageView) findViewById(R.id.imgPassword);

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etUsername.getText().length()>0){
                    imgUsername.setImageResource(R.drawable.username_green);
                    //finalChar = etUsername.getText().toString();
                }else {
                    imgUsername.setImageResource(R.drawable.username_red);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPassword.getText().length()>0){
                    imgPassword.setImageResource(R.drawable.lock_green);
                }else {
                    imgPassword.setImageResource(R.drawable.lock_red);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onClickSignIn(View view) {
        Intent call = new Intent(Login.this, Shop.class);
        call.putExtra("user", "Shop");
        startActivity(call);
        //startActivity(new Intent(Login.this, Shop.class));
    }

    public void onClickForgotPassword(View view) {
        startActivity(new Intent(Login.this, ForgotPassword.class));
    }

    public void onClickJoinNow(View view) {
        startActivity(new Intent(Login.this, Signup.class));
    }

}
