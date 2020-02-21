package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static com.squareup.okhttp.internal.http.HttpDate.format;

public class ProductDetails extends AppCompatActivity {


    private String categoryName,Description,Price,pName,saveCurrentDate,saveCurrentTime;
    Button addNewProduct, updateproduct;
    EditText productName,productDescription,productPrice;
    ImageView newProductImage;
    private static  final int GalleryPick=1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;

    StorageReference ProductImagesRef;
    private DatabaseReference ProductRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetails);

        addNewProduct=findViewById(R.id.addnewproduct);
        newProductImage=findViewById(R.id.selectproduct);
        productName=findViewById(R.id.productname);
        productDescription=findViewById(R.id.productdescription);
        productPrice=findViewById(R.id.productprice);
        updateproduct=findViewById(R.id.updatenewproduct);


        categoryName=getIntent().getExtras().get("category").toString();

        ProductImagesRef= FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductRef=FirebaseDatabase.getInstance().getReference().child("Products");

        newProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });


        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
        //button to update in home activity
        updateproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetails.this,Home.class));
                finish();
            }
        });


    }//store image in storage
    private void OpenGallery(){
        Intent gallery= new Intent();
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery,GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null&& data.getData()!=null){
            ImageUri= data.getData();
            newProductImage.setImageURI(ImageUri);
        }
    }

    private void ValidateProductData(){

        Description=productDescription.getText().toString();
        Price=productPrice.getText().toString();
        pName=productName.getText().toString();

        if(ImageUri==null){
            Toast.makeText(this,"Product image is required",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Description)){

            Toast.makeText(this,"Please write description ",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Price)){

            Toast.makeText(this,"Please mention the price  ",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(pName)){

            Toast.makeText(this,"Please write product Name ",Toast.LENGTH_SHORT).show();
        }else{
            StoreProductInfo();
        }
    }

    private void StoreProductInfo() {
        Calendar calender = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("mm,dd,yyyy");
        saveCurrentDate = currentDate.format(calender.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calender.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


         final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadtask = filePath.putFile(ImageUri);

        uploadtask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(ProductDetails.this, "Error:" + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ProductDetails.this, "Image uploaded ", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadtask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProductDetails.this, " getting Product image Url successfully", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }

        });
    }
private  void SaveProductInfoToDatabase() {

    HashMap<String, Object> productMap = new HashMap<>();
    productMap.put("pid", productRandomKey);
    productMap.put("date", saveCurrentDate);
    productMap.put("time", saveCurrentTime);
    productMap.put("description", Description);
    productMap.put("image", downloadImageUrl);
    productMap.put("Category", categoryName);
    productMap.put("price", Price);
    productMap.put("name", pName);


   /* ProductRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful()){

               downloadImageUrl=task.getResult().toString();
               Toast.makeText(ProductDetails.this,"product is added successfully",Toast.LENGTH_SHORT).show();

           }else{
               String message=task.getException().toString();
               Toast.makeText(ProductDetails.this,"Error:" +message,Toast.LENGTH_SHORT).show();
           }
        }
    });*/
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    db.collection("Products").add(productMap)
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {

                        downloadImageUrl = task.getResult().toString();
                        Toast.makeText(ProductDetails.this, "product is added successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        String message = task.getException().toString();
                        Toast.makeText(ProductDetails.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                    }
                }


            });



}




}



