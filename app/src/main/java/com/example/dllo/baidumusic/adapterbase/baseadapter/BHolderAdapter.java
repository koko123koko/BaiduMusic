package com.example.dllo.baidumusic.adapterbase.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public abstract class BHolderAdapter<T,H> extends BBaseAdapter<T> {

    public BHolderAdapter(Context context, List<T> arrayList) {
        super(context, arrayList);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        H holder = null;
        T t = arrayList.get(i);

        if (view == null){
            view = buildConvertView(layoutInflater,t,i);
            holder = buildHolder(view,t,i);
            view.setTag(holder);
        } else {
            holder = (H) view.getTag();
        }
        bindViewDatas(holder,t,i);
        return view;
    }
    /**
     * 绑定数据
     * @param holder
     * @param t
     * @param i
     */
    protected abstract void bindViewDatas(H holder, T t, int i);

    /**
     * 建立视图Holder
     * @param view
     * @param t
     * @param i
     * @return
     */
    protected abstract H buildHolder(View view, T t, int i);

    /**
     * 建立ConverView
     * @param layoutInflater
     * @param t
     * @param i
     * @return
     */
    protected abstract View buildConvertView(LayoutInflater layoutInflater, T t, int i);


}
