package com.framgia.orderfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.framgia.orderfood.screen.cart.CartFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference reference;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new HomeFragment())
                .commit();
        Intent intent=this.getIntent();
        key= getTable();
        reference=FirebaseDatabase.getInstance().getReference().child("Table");
        initView();
    }

    private void initView() {
        NavigationView navigationView=findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        final Intent intentTable=new Intent(this,TableActivity.class);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_logout){
                    SharedPreferences mSharedPreferences =getSharedPreferences("db_app",Context.MODE_PRIVATE);
                    mSharedPreferences.edit().remove("TABLE").commit();
                    reference.child(key).child("Status").setValue("1");
                   startActivity(intentTable);


                }
                return false;
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }
    public String getTable(){
        SharedPreferences mSharedPreferences =getSharedPreferences("db_app",Context.MODE_PRIVATE);
        return mSharedPreferences.getString("TABLE", "");
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString("KEY", key);
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment =HomeFragment.newInstance();
                break;
            case R.id.nav_cart:

                fragment = new CartFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_notification:
                fragment = new NotificationFragment();
                fragment.setArguments(bundle);
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
        return true;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        reference.child(key).child("Status").setValue("1");
    }
}
