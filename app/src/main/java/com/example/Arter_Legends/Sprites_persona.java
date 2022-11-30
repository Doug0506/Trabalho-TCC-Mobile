package com.example.Arter_Legends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sprites_persona extends AppCompatActivity {

    Button btn_apresentação;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprites_persona);
        getSupportActionBar().hide();

        btn_apresentação = findViewById(R.id.btn_apresentação);

        btn_apresentação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(Sprites_persona.this, SlideShow.class);
                startActivity(intent);
            }
        });

    }
}