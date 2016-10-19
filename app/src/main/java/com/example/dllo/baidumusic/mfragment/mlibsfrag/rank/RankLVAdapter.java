package com.example.dllo.baidumusic.mfragment.mlibsfrag.rank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.musicbean.RankBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mvolley.DisplaySingle;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/9/20.
 */
public class RankLVAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<RankBean.Rank> arrayList;
    Context context;
    private List<RankBean.Rank.ContentBean> content;


    public RankLVAdapter(Context context) {
        this.context = context;
    }


    public void setArrayList(ArrayList<RankBean.Rank> arrayList) {
        this.arrayList = arrayList;


        notifyDataSetChanged();
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RankViewHolder rankViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_rank_lv, null);
            rankViewHolder = new RankViewHolder(view);
            view.setTag(rankViewHolder);
        } else {
            rankViewHolder = (RankViewHolder) view.getTag();
        }

        rankViewHolder.ibtn.setOnClickListener(this);

        content = arrayList.get(i).getContent();
        rankViewHolder.number1.setText(content.get(0).getTitle() + "-" + content.get(0).getAuthor());
        rankViewHolder.number2.setText(content.get(1).getTitle() + "-" + content.get(1).getAuthor());
        rankViewHolder.number3.setText(content.get(2).getTitle() + "-" + content.get(2).getAuthor());
        DisplaySingle.getInstance().show(arrayList.get(i).getPic_s210(), rankViewHolder.iv);
        rankViewHolder.top.setText(arrayList.get(i).getName());
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
