package com.example.metamaze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView banner,register;
    private EditText editTextTextPersonName, editTextTextPersonName2, Email, Password;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        banner=(TextView)findViewById(R.id.banner);
        banner.setOnClickListener(this);

        register=(Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        editTextTextPersonName=(EditText)findViewById(R.id.fullName);
        editTextTextPersonName2=(EditText)findViewById(R.id.age);
        Email=(EditText)findViewById(R.id.email);
        Password=(EditText)findViewById(R.id.password);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.register:
                register();
                break;
        }

    }

    private void register() {
        String email=Email.getText().toString().trim();
        String password=Password.getText().toString().trim();
        String fullName=editTextTextPersonName.getText().toString().trim();
        String age=editTextTextPersonName2.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextTextPersonName.setError("Full Name is required");
            editTextTextPersonName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextTextPersonName2.setError("Age is required");
            editTextTextPersonName2.requestFocus();
            return;
        }

        if(email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please provide your valid Email");
            Email.requestFocus();
            return;
        }

        if(password.isEmpty()){
           Password.setError("Password is required");
            Password.requestFocus();
            return;
        }
        if(password.length()<6){
            Password.setError("Min Password length should be of 6 characters! ");
            Password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(fullName,age,email);


                        }
                    }
                });
    }
}