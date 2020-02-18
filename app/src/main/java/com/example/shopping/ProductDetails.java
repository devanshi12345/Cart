package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {
    private String categoryName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetails);

        categoryName=getIntent().getExtras().get("category").toString();


    }

}
