package com.example.dllo.baidumusic.adapterbase.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public abstract class BBaseAdapter<T> extends BaseAdapter {

    protected List<T> arrayList;

    protected Context context;
    protected LayoutInflater layoutInflater;

    public void setArrayList(List<T> arrayList) {
        this.arrayList = arrayList;
    }

    public List<T> getArrayList() {
        return arrayList;
    }

    public BBaseAdapter(Context context,List<T> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View inflate(int layoutId){
        return layoutInflater.inflate(layoutId,null);
    }


}
