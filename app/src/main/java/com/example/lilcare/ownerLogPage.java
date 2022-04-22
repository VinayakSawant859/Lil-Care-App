package com.example.lilcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ownerLogPage extends AppCompatActivity {
    Button regforadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        regforadmin = (Button) findViewById(R.id.regforadmin);

        regforadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ownerLogPage.this, ownerRegPage.class);
                startActivity(i);
            }
        });    }
}