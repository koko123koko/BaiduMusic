package com.example.dllo.baidumusic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.dllo.baidumusic.Bean.RecommBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class DiyBaseAdapter extends BaseAdapter {

    private Context context;


    ArrayList<RecommBean.ResultBean.DiyBean.Diy> diyBeanArrayList;

    public void setDiyBeanArrayList(ArrayList<RecommBean.ResultBean.DiyBean.Diy> diyBeanArrayList) {
        this.diyBeanArrayList = diyBeanArrayList;
    }

    public DiyBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return diyBeanArrayList == null ? 0 : diyBeanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return diyBeanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base, null);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }

            imgToLoader(diyBeanArrayList.get(i).getPic(), myViewHolder.iv);
            myViewHolder.tv.setText(diyBeanArrayList.get(i).getTitle());

        return view;
    }

    public void imgToLoader(String url, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url,
                new com.android.volley.Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {


                        imageView.setImageBitmap(response);

                    }
                }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        VolleySington.getInstance().addRequest(imageRequest);
    }

    class MyViewHolder {

        ImageView iv;
         TextView tv;

        public MyViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.iv_item_base);
            tv = (TextView) view.findViewById(R.id.tv_item_base);

        }
    }
}
