package com.framgia.orderfood.screen.home.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView mImageView;
    public TextView mTextView;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private ItemClickListener itemClickListener;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView=itemView.findViewById(R.id.textViewCategoryTitle);
        mImageView=itemView.findViewById(R.id.imageViewMenuLayout);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
