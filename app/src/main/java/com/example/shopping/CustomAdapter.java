package com.example.shopping;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    Home listActivity;
    List<Data> dataList;
    Context context;
    private Uri imageUri;

    Button addToCart;
    public CustomAdapter(Home listActivity, List<Data> dataList) {
        this.listActivity = listActivity;
        this.dataList = dataList;
        this.context=context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        //inflate layout
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_layout,viewGroup,false);

        ViewHolder viewHolder =new ViewHolder(itemView);
        //handle item clicks here

        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //this will be called when user click item


                //show data in toast

                String image=dataList.get(position).getImage();
                String name=dataList.get(position).getName();
               String price =dataList.get(position).getPrice();
                String description=dataList.get(position).getDescription();

                Toast.makeText(listActivity,name+"\n" +description+"\n"+price, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra(name,dataList.get(position).name);
                intent.putExtra(description,dataList.get(position).description);









            }


            @Override
            public void onItemLongClick(View view, int position) {
//this will be called when user long click item
            }
        });



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        //bind views


        viewHolder.pName.setText(dataList.get(position).getName());
        viewHolder.pDescription.setText(dataList.get(position).getDescription());
        viewHolder.pPrice.setText(dataList.get(position).getPrice());



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
