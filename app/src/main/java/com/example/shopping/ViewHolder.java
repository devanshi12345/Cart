package com.example.shopping;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.util.Listener;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView pName,pDescription,pPrice;
    ImageView pImage;
    View mView;
    Button addToCart;
    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        mView=itemView;

        //on item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAdapterPosition());
            }
        });


        //item long click listener
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });

        //initialize views with data_layout


        pName=itemView.findViewById(R.id.name);
        pDescription=itemView.findViewById(R.id.description);
        pPrice=itemView.findViewById(R.id.price);
        pImage=itemView.findViewById(R.id.image);
        addToCart=itemView.findViewById(R.id.cartBtn);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    private ViewHolder.ClickListener mClickListener;


    public interface ClickListener{
        void onItemClick(View view,int position);

        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;

    }
}
