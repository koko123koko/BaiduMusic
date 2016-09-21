package com.example.dllo.baidumusic.LibsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;

import java.util.ArrayList;


/**
 * Created by dllo on 16/9/20.
 */
public class RankLVAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<String> arrayList;
    Context context;
    ArrayList<Integer> imgs;
    ArrayList<String> top;

    public void setTop(ArrayList<String> top) {
        this.top = top;
    }

    public void setImgs(ArrayList<Integer> imgs) {
        this.imgs = imgs;
    }

    public RankLVAdapter(Context context) {
        this.context = context;
    }



    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return top == null ? 0 : top.size();
    }

    @Override
    public Object getItem(int i) {
        return top.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RankViewHolder rankViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_rank_lv,null);
            rankViewHolder = new RankViewHolder(view);
            view.setTag(rankViewHolder);
        } else {
            rankViewHolder = (RankViewHolder)view.getTag();
        }
        rankViewHolder.ibtn.setOnClickListener(this);
        rankViewHolder.number1.setText(arrayList.get(0));
        rankViewHolder.number2.setText(arrayList.get(1));
        rankViewHolder.number3.setText(arrayList.get(2));
        rankViewHolder.iv.setImageResource(imgs.get(i));
        rankViewHolder.top.setText(top.get(i));
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    class RankViewHolder {

        private final ImageButton ibtn;
        private final TextView number1;
        private final TextView number2;
        private final TextView number3;
        private final ImageView iv;
        private final TextView top;

        public RankViewHolder(View view) {
            ibtn = (ImageButton) view.findViewById(R.id.ib_item_rank_lv);
            number1 = (TextView) view.findViewById(R.id.number1_item_rank_lv);
            number2 = (TextView) view.findViewById(R.id.number2_item_rank_lv);
            number3 = (TextView) view.findViewById(R.id.number3_item_rank_lv);
            iv = (ImageView) view.findViewById(R.id.iv_item_rank_lv);
            top = (TextView) view.findViewById(R.id.top_item_rank_lv);
        }
    }
}
