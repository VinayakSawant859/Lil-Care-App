package com.example.lilcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class parentLogPage extends AppCompatActivity {
    Button regforparent, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_log);
        regforparent = (Button) findViewById(R.id.regforparent);
        regforparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(parentLogPage.this, parentRegPage.class);
                startActivity(i);
                //hii
            }
        });


        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(parentLogPage.this, HomePage.class);
                startActivity(i);
            }
        });
    }
}