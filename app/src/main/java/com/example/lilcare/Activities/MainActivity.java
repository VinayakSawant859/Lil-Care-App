package com.example.lilcare.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilcare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    ImageView bgslide, Parent, daycare;
    LinearLayout textsplash, menu;
    Animation bganim, frombottom;
    TextView loginTXT, registerTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                    }
                });

        bgslide = (ImageView) findViewById(R.id.bgslide);
        loginTXT = findViewById(R.id.loginTXT);
        registerTXT = findViewById(R.id.registerTXT);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        menu = (LinearLayout) findViewById(R.id.menu);

        bganim = AnimationUtils.loadAnimation(this, R.anim.bganim);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgslide.animate().translationY(-1500).setDuration(1000).setStartDelay(800);
        textsplash.animate().translationY(-1700).alpha(0).setDuration(1000).setStartDelay(800);
        menu.animate().translationY(-100).setDuration(600).setStartDelay(1200).alpha(1.0f);

        menu.setAnimation(frombottom);

        loginTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, parentLogPage.class));
            }
        });

        registerTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, parentRegPage.class));
            }
        });

    }
}