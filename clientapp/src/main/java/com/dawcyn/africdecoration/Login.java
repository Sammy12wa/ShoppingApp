package com.dawcyn.africdecoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickSignIn(View view) {
        startActivity(new Intent(Login.this, DesignerDashboard.class));
    }

    public void onClickJoinNow(View view) {
        startActivity(new Intent(Login.this, Signup.class));
    }

    public void onClickForgotPassword(View view) {
        startActivity(new Intent(Login.this, ForgotPassword.class));
    }
}
