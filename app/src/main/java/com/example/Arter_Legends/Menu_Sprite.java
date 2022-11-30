package com.example.Arter_Legends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Sprite extends AppCompatActivity {

    Button btnsprite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sprite);
        getSupportActionBar().hide();

        btnsprite = findViewById(R.id.btnsprite);

        btnsprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Menu_Sprite.this, Sprites_persona.class);
                startActivity(intent);
            }
        });
    }
}