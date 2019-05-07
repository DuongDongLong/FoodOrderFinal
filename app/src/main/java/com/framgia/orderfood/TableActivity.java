package com.framgia.orderfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.framgia.orderfood.data.model.Table;
import com.framgia.orderfood.screen.cart.adapter.CartAdapter;
import com.framgia.orderfood.screen.home.viewholder.TableViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TableActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerOptions<Table> options1;
    FirebaseRecyclerAdapter<Table, TableViewHolder> adapter1;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_activity);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Table");
        mRecyclerView = findViewById(R.id.recyclerViewTable);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        options1 = new FirebaseRecyclerOptions.Builder<Table>().setQuery(databaseReference1, new SnapshotParser<Table>() {
            @NonNull
            @Override
            public Table parseSnapshot(@NonNull DataSnapshot snapshot) {
                // TODO parse data ra đây
                return new Table(snapshot.child("Name").getValue().toString(), snapshot.child("Status").getValue().toString()); // parse trong đây
            }
        }).build();

        adapter1 = new FirebaseRecyclerAdapter<Table, TableViewHolder>(options1) {
            @SuppressLint("ResourceAsColor")
            @Override
            protected void onBindViewHolder(@NonNull TableViewHolder holder, int position, @NonNull final Table model) {
                holder.textViewNameTable.setText(model.getName());
                if (model.getStatus().equals("1")) {
                    holder.imageView.setImageResource(R.drawable.im_table);
                    holder.textViewNameTable.setTextColor(R.color.colorTextNameTable);
                } else {
                    holder.imageView.setImageResource(R.drawable.img_table);
                    holder.textViewNameTable.setTextColor(R.color.colorTextNameTableUnVisible);
                }
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Log.d("TAGG", "parseSnapshot: " + position);
                        if (model.getStatus().equals("1")) {
                            Intent intent = new Intent(TableActivity.this, MainActivity.class);
                            String key=adapter1.getRef(position).getKey();
                            intent.putExtra("ID_TABLE", key);
                            databaseReference1.child(key).child("Status").setValue("0");
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                        } else {
                            Toast.makeText(getApplication(), "Ban da duoc su dung", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onClickAdd(View view, int position) {

                    }

                    @Override
                    public void onClickPlus(CartAdapter.ViewHolder viewHolder, View view, int position) {

                    }

                    @Override
                    public void onClickMinus(CartAdapter.ViewHolder viewHolder, View view, int position) {

                    }
                });
            }

            @NonNull
            @Override
            public TableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_table_layout, viewGroup, false);
                return new TableViewHolder(view1);
            }
        };
        adapter1.startListening();
        mRecyclerView.setAdapter(adapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }


}
