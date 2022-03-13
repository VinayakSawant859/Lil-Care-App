package com.example.lilcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView bgslide, Parent, daycare;
    LinearLayout textsplash, menu;
    Animation bganim, frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgslide = (ImageView) findViewById(R.id.bgslide);
        Parent = (ImageView) findViewById(R.id.Parent);
        daycare = (ImageView) findViewById(R.id.daycare);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        menu = (LinearLayout) findViewById(R.id.menu);

        bganim = AnimationUtils.loadAnimation(this, R.anim.bganim);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgslide.animate().translationY(-1500).setDuration(1000).setStartDelay(800);
        textsplash.animate().translationY(-1700).alpha(0).setDuration(1000).setStartDelay(800);
        menu.animate().translationY(-100).setDuration(600).setStartDelay(1200).alpha(1.0f);

        menu.setAnimation(frombottom);

        Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,parentLoginPage.class);
                startActivity(i);
                //hii
            }
        });

        daycare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ownerLoginPage.class);
                startActivity(i);

                //startActivity(new Intent(MainActivity.this,ownerLoginPage.class));
                //hii
            }
        });


    }
}