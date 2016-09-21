package com.example.dllo.baidumusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MyBaseAdapter extends BaseAdapter{

    private Context context;



    private ArrayList<ImageView> imageViews;

    public void setImageViews(ArrayList<ImageView> imageViews) {
        this.imageViews = imageViews;
    }



    public MyBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageViews == null ? 0 : imageViews.size();
    }

    @Override
    public Object getItem(int i) {
        return imageViews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base,null);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }

        myViewHolder.iv.setImageBitmap(imageViews.get(i).getDrawingCache());
        myViewHolder.tv.setText(i+"");
        return view;
    }

    class MyViewHolder{

        private final ImageView iv;
        private final TextView tv;

        public MyViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.iv_item_base);
            tv = (TextView) view.findViewById(R.id.tv_item_base);

        }
    }
}
