package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    ImageView Tshirts,sportTshirts,femaleDresses, sweaters;
    ImageView glasses,hatsCaps,WallestBagsPurses,shoes;
    ImageView headPhones,laptops,watches,mobilePhones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Tshirts=findViewById(R.id.t_shirts);
        sportTshirts=findViewById(R.id.sports_t_shirts);
        femaleDresses=findViewById(R.id.female_dresses);
        sweaters=findViewById(R.id.sweathers);

        glasses=findViewById(R.id.glasses);
        hatsCaps=findViewById(R.id.hats_caps);
        WallestBagsPurses =findViewById(R.id.purses_bags_wallets);
        shoes=findViewById(R.id.shoes);

        headPhones=findViewById(R.id.headphones_handfree);
        laptops=findViewById(R.id.laptop_pc);
        watches=findViewById(R.id.watches);
        mobilePhones=findViewById(R.id.mobilephones);

        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();



        Tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(Admin.this,ProductDetails.class);
             intent.putExtra("category","Tshirts");
             startActivity(intent);
            }
        });
        sportTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","sportsTshirts");
                startActivity(intent);
            }
        });
        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","femaleDresses");
                startActivity(intent);
            }
        });

        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","sweaters");
                startActivity(intent);
            }
        });
        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","Glasses");
                startActivity(intent);
            }
        });
        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","Hats Caps");
                startActivity(intent);
            }
        });

        WallestBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","Wallets Bags Purses");
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","Shoes");
                startActivity(intent);
            }
        });


        headPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","HeadPhones");
                startActivity(intent);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","Laptops");
                startActivity(intent);
            }
        });

        mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","MobilePhones");
                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,ProductDetails.class);
                intent.putExtra("category","Watches");
                startActivity(intent);
            }
        });

    }

}
