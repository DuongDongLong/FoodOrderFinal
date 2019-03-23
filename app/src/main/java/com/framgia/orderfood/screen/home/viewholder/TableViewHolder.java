package com.framgia.orderfood.screen.home.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;

public class TableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textViewNameTable;
    public ImageView imageView;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TableViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewNameTable=itemView.findViewById(R.id.textViewTableName);
        imageView=itemView.findViewById(R.id.imageViewTableLayout);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
