package com.dawcyn.africshop;

import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dawcyn.common.Shop;

public class Welcome extends AppCompatActivity {

    FrameLayout frameLogo;
    LinearLayout panelFooter;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        frameLogo = (FrameLayout) findViewById(R.id.frameLogo);
        panelFooter = (LinearLayout) findViewById(R.id.panelFooter);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        frameLogo.setAnimation(uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        panelFooter.setAnimation(downtoup);
        downtoup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(Welcome.this, Shop.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }




}
