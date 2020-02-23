package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    EditText Password, Email;
    Button Loginbtn,register;
    private ProgressDialog loadingBar;
    FirebaseAuth fAuth;
    TextView hint;


    private CheckBox chkBoxRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Loginbtn = findViewById(R.id.loginBtn);
        Password = (EditText) findViewById(R.id.passwordInput);
        Email = (EditText) findViewById(R.id.email);
        hint=findViewById(R.id.hint);
        register=findViewById(R.id.registerBtn);
        fAuth=FirebaseAuth.getInstance();


        loadingBar = new ProgressDialog(this);

        chkBoxRememberMe = findViewById(R.id.rememberMe);
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,AdminLogin.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });


        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mEmail = Email.getText().toString().trim();
                String mPassword = Password.getText().toString().trim();

                fAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                          if(fAuth.getCurrentUser().isEmailVerified()){
                              Intent intent=new Intent(Login.this,Home.class);
                              startActivity(intent);
                          }else{
                              Toast.makeText(Login.this, "Please verify your Email address", Toast.LENGTH_SHORT).show();
                          }

                        } else {
                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}


