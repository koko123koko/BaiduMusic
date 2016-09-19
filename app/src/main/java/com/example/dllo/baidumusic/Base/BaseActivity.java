package com.example.dllo.baidumusic.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(setLayout());
        initView();
        initData();
    }

    protected abstract int setLayout();

    protected abstract void initData();

    protected abstract void initView();

    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }

}
