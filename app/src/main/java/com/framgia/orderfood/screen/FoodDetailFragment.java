package com.framgia.orderfood.screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.framgia.orderfood.screen.cart.CartFragment;
import com.framgia.orderfood.R;
import com.framgia.orderfood.data.model.Cart;
import com.framgia.orderfood.data.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodDetailFragment extends Fragment implements View.OnClickListener {
    CircleImageView circleImageView;
    private TextView textViewQuantity,textViewNameFood,textViewDesFood,textViewPrice,textViewCategoryFoodLayout;
    private CircleImageView minus, plus;
    private Button buttonAddCart;
    private CheckBox checkBoxHere,checkBoxHome;
    private final static String KEY_FOOD = "KEY_FOOD";
    private Food food;

    final public static FoodDetailFragment newInstance(Food food) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_FOOD, food);
        FoodDetailFragment fragment = new FoodDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.food_detail_fragment, container, false);


        view.findViewById(R.id.button_back_food_detail).setOnClickListener(this);
        textViewQuantity = view.findViewById(R.id.textViewQuantity);
        minus = view.findViewById(R.id.image_circle_minus_cart);
        plus = view.findViewById(R.id.image_circle_plus_cart);
        buttonAddCart = view.findViewById(R.id.button_add_cart_detail);
        textViewNameFood=view.findViewById(R.id.textViewNameFoodLayout);
        textViewDesFood=view.findViewById(R.id.textViewDesFoodLayout);
        textViewPrice=view.findViewById(R.id.textViewPriceFoodLayout);
        textViewCategoryFoodLayout=view.findViewById(R.id.textViewCategoryFoodLayout);
        circleImageView=view.findViewById(R.id.imageFoodLayout);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        buttonAddCart.setOnClickListener(this);
        checkBoxHere=view.findViewById(R.id.checkBoxHere);
        checkBoxHome=view.findViewById(R.id.checkBoxHome);
        checkBoxHere.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkBoxHome.setChecked(false);
                }
                else
                    checkBoxHome.setChecked(true);
            }
        });
        checkBoxHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkBoxHere.setChecked(false);
                }
                else
                    checkBoxHere.setChecked(true);
            }
        });
        initData(view);
        return view;
    }

    private void initData(View view) {
        if (getArguments() != null) {
            food = getArguments().getParcelable(KEY_FOOD);
            textViewNameFood.setText(food.getName());
            textViewDesFood.setText(food.getDescription());
            textViewPrice.setText("$"+food.getPrice());
            Glide.with(view).load(food.getImage()).into(circleImageView);
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Category").child(food.getMenuId());
            reference.child("Name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    textViewCategoryFoodLayout.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_back_food_detail:
                getFragmentManager().popBackStack();
                break;
            case R.id.image_circle_minus_cart:
                int quantity = Integer.parseInt(textViewQuantity.getText().toString());
                if (quantity > 1)
                    quantity-=1;
                    textViewQuantity.setText(quantity + "");
                    textViewPrice.setText("$"+quantity*Double.parseDouble(food.getPrice()));
                break;
            case R.id.image_circle_plus_cart:
                int quantity_b = Integer.parseInt(textViewQuantity.getText().toString())+1;
                textViewQuantity.setText(quantity_b+"");
                textViewPrice.setText("$"+quantity_b*Double.parseDouble(food.getPrice()));
                break;
            case R.id.button_add_cart_detail:
                Toast.makeText(getActivity(), "Đã thêm "+food.getName()+" vào giỏ hàng", Toast.LENGTH_SHORT).show();
                Cart cart=new Cart(food,Integer.parseInt(textViewQuantity.getText().toString()));
                CartFragment.cartList.add(cart);
                getFragmentManager().popBackStack();
        }
    }
}
