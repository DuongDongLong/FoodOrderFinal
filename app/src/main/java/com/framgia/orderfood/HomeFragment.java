package com.framgia.orderfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.framgia.orderfood.data.model.Category;
import com.framgia.orderfood.screen.MenuFragment;
import com.framgia.orderfood.screen.home.viewholder.CategoryViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerOptions<Category> options;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    DatabaseReference databaseReference;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment=new HomeFragment();
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.home_fragment, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category");
        mRecyclerView = view.findViewById(R.id.recyclerViewCategoryFood);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(databaseReference, new SnapshotParser<Category>() {
            @NonNull
            @Override
            public Category parseSnapshot(@NonNull DataSnapshot snapshot) {
                // TODO parse data ra đây
                return new Category.CategoryBuilder().setFoodName(snapshot.child("Name").getValue().toString()).setImageUrl(snapshot.child("Image").getValue().toString()).build(); // parse trong đây
            }
        }).build();

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final Category model) {
                Log.d("TAGG", "parseSnapshot: " + position);
                holder.mTextView.setText(model.getCategoryName());
                Glide.with(view).load(model.getImageUrl()).into(holder.mImageView);
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Bundle b = new Bundle();
                        b.putString("ID", adapter.getRef(position).getKey());
                        MenuFragment menuFragment = new MenuFragment();
                        menuFragment.setArguments(b);
                        getFragmentManager().beginTransaction().replace(R.id.frame_menu, menuFragment).commit();
                    }

                    @Override
                    public void onClickAdd(View view, int position) {
                        Toast.makeText(getActivity(),position,Toast.LENGTH_SHORT).show();
                    }

                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_categoty, viewGroup, false);
                return new CategoryViewHolder(view1);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
        getFragmentManager().beginTransaction().replace(R.id.frame_menu, new MenuFragment()).commit();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
