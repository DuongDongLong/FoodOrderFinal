package com.framgia.orderfood.screen.cart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framgia.orderfood.ItemClickListener;
import com.framgia.orderfood.R;
import com.framgia.orderfood.data.model.Cart;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Cart> mcartList;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public CartAdapter(Context context, List<Cart> cartList) {
        mcartList = cartList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_layout, viewGroup, false);
        return new ViewHolder(view, mContext, mcartList, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindViewData(mcartList.get(i));
    }

    @Override
    public int getItemCount() {
        return mcartList != null ? mcartList.size() : 0;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private List<Cart> mcartList;
        private ItemClickListener listener;
        private Button add;
        private CircleImageView plus, minus;
        private ImageView imageView;
        private TextView name, quatity, price;
        /*public RelativeLayout view_background;
        public ConstraintLayout view_foregroud;*/

        public ViewHolder(@NonNull View itemView, Context context, List<Cart> cartList, ItemClickListener itemClickListener) {
            super(itemView);
            mContext = context;
            mcartList = cartList;
            listener = itemClickListener;
            plus = itemView.findViewById(R.id.image_circle_plus_cart);
            minus = itemView.findViewById(R.id.image_circle_minus_cart);
            add = itemView.findViewById(R.id.buttonAddCart);
            imageView = itemView.findViewById(R.id.imageViewCartLayout);
            name = itemView.findViewById(R.id.textViewCartFoodTitle);
            price = itemView.findViewById(R.id.textViewCartFoodPrice);
            quatity = itemView.findViewById(R.id.textViewQuantityCart);
            /*view_background=itemView.findViewById(R.id.view_background);
            view_foregroud=itemView.findViewById(R.id.view_foreground);*/
            add.setOnClickListener(this);
        }

        void bindViewData(Cart cart) {
            name.setText(cart.getFood().getName());
            price.setText(cart.getFood().getPrice());
            quatity.setText(cart.getQuatity() + "");
            Glide.with(itemView.getContext()).load(cart.getFood().getImage()).into(imageView);
        }


        @Override
        public void onClick(View v) {
            if (listener != null) {
                if (v.getId() == R.id.buttonAddCart)
                    listener.onClick(v, getAdapterPosition());
            }
        }
    }
}
