package com.framgia.orderfood.screen.home.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imageView;
    public TextView title,describe,price;
    private ItemClickListener itemClickListener;
    private Button buttonAdd;
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.textViewCartFoodTitle);
        imageView=itemView.findViewById(R.id.imageViewMenuLayout);
        describe=itemView.findViewById(R.id.textViewCartFoodPrice);
        price=itemView.findViewById(R.id.textViewFoodPrice);
        buttonAdd=itemView.findViewById(R.id.buttonADD);
        itemView.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonADD)
            itemClickListener.onClickAdd(v,getAdapterPosition());
        else
            itemClickListener.onClick(v,getAdapterPosition());
    }
}
