package com.framgia.orderfood.screen.cart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;
import com.framgia.orderfood.data.model.Cart;
import com.framgia.orderfood.screen.cart.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements ItemClickListener, RecyclerItemTouchHelperListener, View.OnClickListener {
    public static List<Cart> cartList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView totalText, quantityTotal;
    private Button order;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        order=view.findViewById(R.id.button_order);
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cartAdapter = new CartAdapter(getContext(), cartList);
        cartAdapter.notifyDataSetChanged();
        cartAdapter.setItemClickListener(this);
        recyclerView.setAdapter(cartAdapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        totalText=view.findViewById(R.id.textViewTotal);
        quantityTotal=view.findViewById(R.id.textViewQuantityTotal);
        order=view.findViewById(R.id.button_order);
        order.setOnClickListener(this);
        payment();
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onClickAdd(View view, int position) {

    }

    @Override
    public void onClickPlus(CartAdapter.ViewHolder viewHolder, View view, int position) {
        int quantity = Integer.parseInt(viewHolder.quantity.getText().toString())+1;
        viewHolder.quantity.setText(quantity+ "");
        viewHolder.totalItem.setText("$"+quantity*Double.parseDouble(cartList.get(position).getFood().getPrice()));
        cartList.get(position).setQuatity(quantity);
        payment();
    }

    @Override
    public void onClickMinus(CartAdapter.ViewHolder viewHolder, View view, int position) {
        int quantity = Integer.parseInt(viewHolder.quantity.getText().toString());
        if (quantity > 1) {
            quantity-=1;
            viewHolder.quantity.setText(quantity + "");
            viewHolder.totalItem.setText("$"+quantity*Double.parseDouble(cartList.get(position).getFood().getPrice()));
            cartList.get(position).setQuatity(quantity);
            payment();
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartAdapter.ViewHolder) {
            cartList.remove(viewHolder.getAdapterPosition());
            cartAdapter.notifyDataSetChanged();
            payment();
        }
    }

    private void payment() {
        if (cartList != null) {
            double  total = 0;
            int quantity = 0;
            for(int i=0;i<cartList.size();i++){
                quantity += cartList.get(i).getQuatity();
                total +=Double.parseDouble(cartList.get(i).getFood().getPrice())*cartList.get(i).getQuatity();
            }
            totalText.setText("$"+ total);
            quantityTotal.setText(quantity+"");
        }
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_order:
                        if(cartList.size()>0)
                            openDialog();
                        else
                            Toast.makeText(getContext(),"Empty",Toast.LENGTH_SHORT).show();
            }
    }
    public void openDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Do you want oder?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
