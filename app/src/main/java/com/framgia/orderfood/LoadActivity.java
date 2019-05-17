package com.framgia.orderfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class LoadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);
        final Intent intentTable=new Intent(this,TableActivity.class);
        final Intent intentHome=new Intent(this,MainActivity.class);
        Handler handler=new Handler();
        Runnable mUpdateTimeTask = new Runnable() {
            public void run() {
                if(getTable()=="")
                    startActivity(intentTable);
                else
                    startActivity(intentHome);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        };
        handler.postDelayed(mUpdateTimeTask,2000);

    }

    @Override
    public void finish() {
        super.finish();
        finishActivity(0);
    }

    public String getTable(){
        SharedPreferences mSharedPreferences =getSharedPreferences("db_app",Context.MODE_PRIVATE);
        return mSharedPreferences.getString("TABLE", "");
    }
}