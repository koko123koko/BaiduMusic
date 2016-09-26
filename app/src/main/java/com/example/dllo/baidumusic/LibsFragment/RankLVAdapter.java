package com.example.dllo.baidumusic.LibsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.Bean.RankBean;
import com.example.dllo.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


/**
 * Created by dllo on 16/9/20.
 */
public class RankLVAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<RankBean.Rank> arrayList;
    ArrayList<RankBean.Rank.ContentBean> contentBeen;
    Context context;
    private final DisplayImageOptions options;
    //    ArrayList<Integer> imgs;
//    ArrayList<String> top;

//    public void setTop(ArrayList<String> top) {
//        this.top = top;
//    }
//
//    public void setImgs(ArrayList<Integer> imgs) {
//        this.imgs = imgs;
//    }

    public RankLVAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_rank_lv,null);
            rankViewHolder = new RankViewHolder(view);
            view.setTag(rankViewHolder);
        } else {
            rankViewHolder = (RankViewHolder)view.getTag();
        }
//        Log.d("RankLVAdapter", arrayList.get(0).getContent().get(i).getTitle() + "-" + arrayList.get(0).getContent().get(i).getAuthor());
        rankViewHolder.ibtn.setOnClickListener(this);

        for (int j = 0; j < 3; j++) {
            rankViewHolder.number1.setText(arrayList.get(0).getContent().get(j).getTitle()+"-"+ arrayList.get(0).getContent().get(j).getAuthor());
            rankViewHolder.number2.setText(arrayList.get(1).getContent().get(j).getTitle()+"-"+ arrayList.get(1).getContent().get(j).getAuthor());
            rankViewHolder.number3.setText(arrayList.get(2).getContent().get(j).getTitle()+"-"+ arrayList.get(2).getContent().get(j).getAuthor());

        }
//      Picasso.with(context).load(arrayList.get(i).getPic_s210()).into(rankViewHolder.iv);
        ImageLoader.getInstance().displayImage(arrayList.get(i).getPic_s210(),rankViewHolder.iv);
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
