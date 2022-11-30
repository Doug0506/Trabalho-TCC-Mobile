package com.example.Arter_Legends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {

     EditText edit_nome, edit_email;
     TextInputEditText edit_senha;
     Button btncadastrar, btnvoltar;
     String UsuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        getSupportActionBar().hide();

        edit_nome=findViewById(R.id.edit_nome);
        edit_senha=findViewById(R.id.edit_senha);
        edit_email=findViewById(R.id.edit_email);
        btncadastrar = findViewById(R.id.btncadastrar);
        btnvoltar = findViewById(R.id.btnvoltar);


        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormCadastro.this, FormLogin.class);
                startActivity(intent);
            }
        });
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome, email, senha;

                nome = edit_nome.getText().toString();
                email = edit_email.getText().toString();
                senha = edit_senha.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty())
                {
                    Toast.makeText(FormCadastro.this, "Por favor não deixe os campos vazios", Toast.LENGTH_SHORT).show();
                }
                else{
                    CadastrarUsuario();
                }

            }
        });

    }

    public void CadastrarUsuario(){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    SalvarDadosCadastro();
                    Toast.makeText(FormCadastro.this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();
                }
                else{
                    String erro;

                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException e)
                    {
                        erro="Digite uma senha com mais de 6 caracteres";

                    }
                    catch (FirebaseAuthUserCollisionException e)
                    {
                        erro="Este usuario já foi cadastrado";
                    }
                    catch(FirebaseAuthInvalidCredentialsException e){
                        erro = "Dados Inválidos";
                    }
                    catch (Exception e){
                        erro = "Ocorreu um erro ao cadastrar o usuario";
                    }

                    Toast.makeText(FormCadastro.this, erro, Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void SalvarDadosCadastro(){
        String nome = edit_nome.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);
        UsuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(UsuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao salvar os dados");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error","Erro ao salvar os dados" + e.toString());
                    }
                });
    };
}