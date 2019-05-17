package com.framgia.orderfood.screen.home.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.framgia.orderfood.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView title,note,time;
    private OnClickItem itemClickListener;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.txtDetail);
        time=itemView.findViewById(R.id.txtTime);
        note=itemView.findViewById(R.id.txtNote);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(OnClickItem itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.setOnClickItem(getAdapterPosition());
    }

}
