package com.example.Arter_Legends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FormLogado extends AppCompatActivity {

    TextView text_email, text_nome;
    Button btn_deslogar;
    FirebaseFirestore db =  FirebaseFirestore.getInstance();
    String usuarioID;
    ImageButton btn_loja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_logado);
        getSupportActionBar().hide();

        btn_loja = findViewById(R.id.btn_loja);
        text_nome = findViewById(R.id.text_nome);
        text_email = findViewById(R.id.text_email);
        btn_deslogar = findViewById(R.id.btn_deslogar);


        btn_loja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (FormLogado.this, Menu_Sprite.class);
                startActivity(intent);
            }
        });

        btn_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent (FormLogado.this, FormLogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null)
                {
                    text_nome.setText(documentSnapshot.getString("nome"));
                    text_email.setText(email);
                }
            }
        });
    }


}