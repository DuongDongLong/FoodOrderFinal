package com.framgia.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LoadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);
        final Intent intent=new Intent(this,TableActivity.class);
        Handler handler=new Handler();
        Runnable mUpdateTimeTask = new Runnable() {
            public void run() {

                startActivity(intent);
            }
        };
        handler.postDelayed(mUpdateTimeTask,2000);
    }

    @Override
    public void finish() {
        super.finish();
        finishActivity(0);
    }
}