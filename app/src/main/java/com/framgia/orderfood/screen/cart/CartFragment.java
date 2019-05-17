package com.framgia.orderfood.screen.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.orderfood.HomeFragment;
import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.MainActivity;
import com.framgia.orderfood.R;
import com.framgia.orderfood.data.model.Cart;
import com.framgia.orderfood.data.model.Order;
import com.framgia.orderfood.screen.cart.adapter.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CartFragment extends Fragment implements ItemClickListener, RecyclerItemTouchHelperListener, View.OnClickListener {
    public static ArrayList<Cart> cartList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView totalText, quantityTotal,tableTitle;
    DatabaseReference databaseReference;
    private Button order;
    private String key;
    double  total = 0;
    int quantity = 0;
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
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
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
        tableTitle=view.findViewById(R.id.cart_title_table);
        quantityTotal=view.findViewById(R.id.textViewQuantityTotal);
        order=view.findViewById(R.id.button_order);
        order.setOnClickListener(this);
        key=getArguments().getString("KEY");
        Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
        tableTitle.setText("Table "+key);
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
        viewHolder.totalItem.setText("$"+quantity*Double.parseDouble(cartList.get(position).getPrice()));
        cartList.get(position).setQuantity(quantity);
        payment();
    }

    @Override
    public void onClickMinus(CartAdapter.ViewHolder viewHolder, View view, int position) {
        int quantity = Integer.parseInt(viewHolder.quantity.getText().toString());
        if (quantity > 1) {
            quantity-=1;
            viewHolder.quantity.setText(quantity + "");
            viewHolder.totalItem.setText("$"+quantity*Double.parseDouble(cartList.get(position).getPrice()));
            cartList.get(position).setQuantity(quantity);
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
            quantity=0;
            total=0;
            for(int i=0;i<cartList.size();i++){
                quantity += cartList.get(i).getQuantity();
                total +=Double.parseDouble(cartList.get(i).getPrice())*cartList.get(i).getQuantity();
            }
            totalText.setText("$"+ total);
            quantityTotal.setText(quantity+"");
        }
        else {
            totalText.setText("$"+ 0);
            quantityTotal.setText(0+"");
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
                        getTime(System.currentTimeMillis());
                        Order order=new Order(cartList,key,total, "order");
                        databaseReference.child(String.valueOf(System.currentTimeMillis())).setValue(order);
                        clear();

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
    public void clear(){
        cartList.clear();
        cartAdapter.notifyDataSetChanged();
        quantity=0;
        total=0;
        payment();

    }
    public String getTime(long millis){
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long hoursCurrent= hours <=16 ? hours+7 : hours-17 ;
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        return  hoursCurrent+":"+minutes;
    }
}
