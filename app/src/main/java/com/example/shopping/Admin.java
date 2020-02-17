package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    ImageView Tshirts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Tshirts=findViewById(R.id.t_shirts);

        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();

        Tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });
    }

}
