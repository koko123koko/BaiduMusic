package com.example.dllo.baidumusic.mfragment.mlibsfrag.km;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.baidumusic.musicbean.KMBean;
import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/27.
 */
public class KMLVAdapter extends BaseAdapter{

    private Context context;

    ArrayList<KMBean.ResultBean.ItemsBean> kmBeanArrayList;

    public void setKmBeanArrayList(ArrayList<KMBean.ResultBean.ItemsBean> kmBeanArrayList) {
        this.kmBeanArrayList = kmBeanArrayList;
        notifyDataSetChanged();
    }

    public KMLVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return kmBeanArrayList == null ? 0 :kmBeanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return kmBeanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KMViewHolder kmViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_km_frag_sing,null);
            kmViewHolder = new KMViewHolder(view);
            view.setTag(kmViewHolder);
        } else {
            kmViewHolder = (KMViewHolder)view.getTag();
        }

        kmViewHolder.name.setText(kmBeanArrayList.get(i).getSong_title() +" - "+ kmBeanArrayList.get(i).getArtist_name());
        kmViewHolder.num.setText(kmBeanArrayList.get(i).getPlay_num()+"人唱过");

        return view;
    }

    //减少findViewById 的操作 viewHolder
    class KMViewHolder{

        private final TextView name;
        private final TextView num;

        public KMViewHolder(View view) {

            name = (TextView) view.findViewById(R.id.tv_item_km_frag_name);
            num = (TextView) view.findViewById(R.id.tv_item_km_frag_num);
        }
    }
}
