package com.example.Arter_Legends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {

    private TextView text_cadastro;
    TextInputEditText edit_senha;
    EditText edit_email;
    ProgressBar progressBar;
    Button btn_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        getSupportActionBar().hide();

        text_cadastro = findViewById(R.id.text_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        progressBar = findViewById(R.id.progressBar);
        btn_entrar = findViewById(R.id.btn_entrar);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha;
                email = edit_email.getText().toString();
                senha = edit_senha.getText().toString();

                if(email.isEmpty() || senha.isEmpty())
                {
                    Toast.makeText(FormLogin.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else{
                    AutenticarUsuario();
                }
            }
        });


        text_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void AutenticarUsuario(){
        String email, senha;
        email = edit_email.getText().toString();
        senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(FormLogin.this, "Login efetuado aguarde...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    }, 3000);
                }
                else
                {
                    String erro;

                    try{
                        throw task.getException();
                    }
                    catch (Exception e){
                        erro = "Ocorreu um erro ao cadastrar com o usuário";
                    }

                    Toast.makeText(FormLogin.this, erro, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void TelaPrincipal(){
        Intent intent = new Intent(FormLogin.this, FormLogado.class);
        startActivity(intent);
        finish();
    }
}