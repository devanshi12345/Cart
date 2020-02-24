package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Home extends AppCompatActivity {
  List<Data>dataList =new ArrayList<>();
    RecyclerView mRecyclerView;
    String Url;
//layout manger for recyclerview

    RecyclerView.LayoutManager layoutManager;



    //Firestore
    FirebaseFirestore db;
    CustomAdapter adapter;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db=FirebaseFirestore.getInstance();


        //initialize vies
        mRecyclerView=findViewById(R.id.recycleview);




        //set it properties
        mRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        pd =new ProgressDialog(this);


        //show data in recycle vie

        showData();





    }

    private void showData() {

        //set title for loading
        pd.setTitle("Loading Data");
        pd.show();

        db.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                //called when data is retrieved
                pd.dismiss();
                for(DocumentSnapshot doc: task.getResult()){
                    Data data=new Data(doc.getString("pid"),
                            doc.getString("name"),
                            doc.getString("description"),
                            doc.getString("price"),
                            doc.getString("image"));

                    dataList.add(data);

                }

                adapter=new CustomAdapter(Home.this,dataList);
                mRecyclerView.setAdapter(adapter);




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Home.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
