package com.framgia.orderfood.screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.framgia.orderfood.screen.cart.CartFragment;
import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;
import com.framgia.orderfood.data.model.Cart;
import com.framgia.orderfood.data.model.Food;
import com.framgia.orderfood.screen.home.viewholder.MenuViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuFragment extends Fragment {
    DatabaseReference databaseReference1;
    FirebaseRecyclerOptions<Food> options2;
    FirebaseRecyclerAdapter<Food, MenuViewHolder> adapter2;
    DatabaseReference databaseReference2;
    RecyclerView mRecyclerView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=LayoutInflater.from(container.getContext()).inflate(R.layout.menu_fragment,container,false);
        String id="01";
        if(getArguments()!=null)
            id= getArguments().getString("ID");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Foods");
        mRecyclerView2 = view.findViewById(R.id.recyclerViewMenu);
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        options2 = new FirebaseRecyclerOptions.Builder<Food>().setQuery(databaseReference2.orderByChild("MenuId").equalTo(id), new SnapshotParser<Food>() {
            @NonNull
            @Override
            public Food parseSnapshot(@NonNull DataSnapshot snapshot) {
                // TODO parse data ra đây
                return new Food(snapshot.child("Name").getValue().toString(),
                        snapshot.child("Description").getValue().toString(),
                        snapshot.child("Image").getValue().toString(),
                        snapshot.child("Discount").getValue().toString(),
                        snapshot.child("MenuId").getValue().toString(),
                        snapshot.child("Price").getValue().toString());
            }
        }).build();
        adapter2= new FirebaseRecyclerAdapter<Food, MenuViewHolder>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull final Food model) {
                Log.d("TAGG", "parseSnapshot: " + model.getPrice());
                holder.title.setText(model.getName());
                holder.describe.setText(model.getDescription());
                holder.price.setText("$"+model.getPrice());
                Glide.with(view).load(model.getImage()).into(holder.imageView);
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left).add(R.id.frame_main, FoodDetailFragment.newInstance(model)).addToBackStack(null).commit();

                    }

                    @Override
                    public void onClickAdd(View view, int position) {
                        CartFragment.cartList.add(new Cart(model,1));
                    }
                });
            }

            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_layout,viewGroup,false);
                return new MenuViewHolder(view1);
            }
        };
        adapter2.startListening();
        mRecyclerView2.setAdapter(adapter2);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }
}
