package com.framgia.orderfood;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.framgia.orderfood.data.model.Cart;
import com.framgia.orderfood.data.model.Notification;
import com.framgia.orderfood.screen.home.viewholder.NotificationViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class NotificationFragment extends Fragment {
    FirebaseRecyclerOptions<Notification> options;
    FirebaseRecyclerAdapter<Notification, NotificationViewHolder> adapter;
    DatabaseReference databaseReference;
    RecyclerView mRecyclerView;
    String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.notification_fragment, container, false);
        key=getArguments().getString("KEY");
        mRecyclerView = view.findViewById(R.id.recyclerView_notification);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        databaseReference = FirebaseDatabase.getInstance().getReference("Notification");

        options = new FirebaseRecyclerOptions.Builder<Notification>().setQuery(databaseReference.orderByChild("table").equalTo(key), new SnapshotParser<Notification>() {
            @NonNull
            @Override
            public Notification parseSnapshot(@NonNull DataSnapshot snapshot) {
                // TODO parse data ra đây
                List<Cart> carts= (List<Cart>) snapshot.child("carts").getValue();
                return new Notification(carts,snapshot.child("table").getValue().toString(),snapshot.child("title").getValue().toString(),snapshot.child("note").getValue().toString(),snapshot.child("time").getValue().toString(),snapshot.child("status").getValue().toString());
            }
        }).build();


        adapter = new FirebaseRecyclerAdapter<Notification, NotificationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final NotificationViewHolder holder, int position, @NonNull final Notification oder) {
                holder.title.setText(oder.getTitle());
                holder.note.setText(oder.getNote());
                holder.time.setText(oder.getTime());
            }

            @NonNull
            @Override
            public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_notification, viewGroup, false);
                return new NotificationViewHolder(view1);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

}
