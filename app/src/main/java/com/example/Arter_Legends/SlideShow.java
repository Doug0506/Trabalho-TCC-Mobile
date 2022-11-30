package com.example.Arter_Legends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Slide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class SlideShow extends AppCompatActivity {

    ImageButton btnlogin;
    Button btnsite;
    WebView wvslide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        getSupportActionBar().hide();



        btnlogin = findViewById(R.id.btnlogin);
        wvslide = findViewById(R.id.wvslide);
        btnsite = findViewById(R.id.btnsite);

        wvslide.getSettings().setJavaScriptEnabled(true);
        wvslide.loadUrl("https://etecspgov-my.sharepoint.com/:p:/g/personal/bryan_leite_etec_sp_gov_br/EcTaE-xUr2JPqIrXPQ1phaMBCZauKI0_CXM8XG7LkpYqmw?rtime=4BQgxnfS2kg");

        btnsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://onedrive.live.com/edit.aspx?resid=DC54F151AACC91B2!2717&ithint=file%2cdocx&authkey=!ACm-TMK27ySQYw8")));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SlideShow.this, FormLogado.class);
                startActivity(intent);
            }
        });
    }
}
