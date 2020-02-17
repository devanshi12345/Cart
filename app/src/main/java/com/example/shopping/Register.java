package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.StreamHandler;

public class Register extends AppCompatActivity {

    Button createAccount;
    EditText userName, phoneNumber, passwordInput,Email;





    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName= findViewById(R.id.userName);
        Email=findViewById(R.id.Email);
        passwordInput=findViewById(R.id.registerPasswordInput);
        phoneNumber=findViewById(R.id.Contact);
        createAccount=findViewById(R.id.registerBtn);


        fAuth=FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail =Email.getText().toString().trim();
                String mPassword=passwordInput.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    Email.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    passwordInput.setError("Password is Required.");
                    return;
                }

                if(mPassword.length()<6 ){
                    passwordInput.setError("password must be greater than 6 character");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "User created.PLease check your email for verification", Toast.LENGTH_SHORT).show();

                                    }else{
                                        Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(Register.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


}



    }
