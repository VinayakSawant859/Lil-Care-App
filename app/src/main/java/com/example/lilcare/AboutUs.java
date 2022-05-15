package com.example.lilcare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class AboutUs extends AppCompatActivity {

    CardView card;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);



    }


    public void about(View view) {
        card = findViewById(R.id.about);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseInt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/VinayakSawant859/Lil-Care-App"));
                startActivity(browseInt);
            }
        });
    }
}
