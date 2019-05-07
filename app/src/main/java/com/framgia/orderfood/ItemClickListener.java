package com.framgia.orderfood;

import android.view.View;

import com.framgia.orderfood.screen.cart.adapter.CartAdapter;

public interface ItemClickListener {
    void onClick(View view, int position);
    void onClickAdd(View view,int position);
    void onClickPlus(CartAdapter.ViewHolder viewHolder,View view, int position);
    void onClickMinus(CartAdapter.ViewHolder viewHolder,View view,int position);
}
