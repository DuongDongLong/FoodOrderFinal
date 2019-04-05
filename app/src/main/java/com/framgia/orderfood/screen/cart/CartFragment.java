package com.framgia.orderfood.screen.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;
import com.framgia.orderfood.data.model.Cart;
import com.framgia.orderfood.screen.cart.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements ItemClickListener {
    public static  List<Cart> cartList =new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.cart_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
         cartAdapter=new CartAdapter(getContext(),cartList);
        cartAdapter.notifyDataSetChanged();
        cartAdapter.setItemClickListener(this);
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        cartList.remove(position);
        cartAdapter.notifyDataSetChanged();
        //Toast.makeText(getContext(),"sadsad",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickAdd(View view, int position) {

    }
}
