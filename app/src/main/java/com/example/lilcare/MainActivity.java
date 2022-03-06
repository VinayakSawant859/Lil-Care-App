package com.example.lilcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bgslide;
    Animation bganim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgslide = (ImageView) findViewById(R.id.bgslide);

        bganim = AnimationUtils.loadAnimation(this, R.anim.bganim);

        bgslide.animate().translationY(-1500).setDuration(800).setStartDelay(300);

    }
}