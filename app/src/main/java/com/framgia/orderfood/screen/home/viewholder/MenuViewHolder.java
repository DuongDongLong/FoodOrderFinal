package com.framgia.orderfood.screen.home.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imageView;
    public TextView title,describe,price;
    private ItemClickListener itemClickListener;
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.textViewFoodTitle);
        imageView=itemView.findViewById(R.id.imageViewMenuLayout);
        describe=itemView.findViewById(R.id.textViewFoodDescribe);
        price=itemView.findViewById(R.id.textViewFoodPrice);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
