package com.example.lilcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lilcare.databinding.ActivityOwnerLoginBinding;

public class ownerLogPage extends AppCompatActivity {

    ActivityOwnerLoginBinding ownerLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownerLoginBinding = ActivityOwnerLoginBinding.inflate(getLayoutInflater());
        setContentView(ownerLoginBinding.getRoot());


        ownerLoginBinding.regforadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ownerLogPage.this, ownerRegPage.class);
                startActivity(i);
            }
        });    }
}