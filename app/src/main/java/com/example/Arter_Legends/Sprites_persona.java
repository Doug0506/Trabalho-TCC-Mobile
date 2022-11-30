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
                String url = "https://etecspgov-my.sharepoint.com/:p:/g/personal/bryan_leite_etec_sp_gov_br/EcTaE-xUr2JPqIrXPQ1phaMBCZauKI0_CXM8XG7LkpYqmw?rtime=4BQgxnfS2kg";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}