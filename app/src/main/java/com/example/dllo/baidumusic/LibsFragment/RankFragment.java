package com.example.dllo.baidumusic.LibsFragment;

import android.widget.ListView;

import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RankFragment extends BaseFragment {

    private ListView lv;

    @Override
    protected void initData() {

        RankLVAdapter rankLVAdapter = new RankLVAdapter(getContext());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("独角戏 - 许茹芸");
        arrayList.add("万事如风 - 齐秦");
        arrayList.add("偏偏喜欢你 - 陈百强");

        ArrayList<Integer> imgs = new ArrayList<>();
        ArrayList<String> top = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            top.add(""+i);
            imgs.add(R.mipmap.ic_launcher);
        }
        rankLVAdapter.setArrayList(arrayList);
        rankLVAdapter.setTop(top);
        rankLVAdapter.setImgs(imgs);
        lv.setAdapter(rankLVAdapter);

    }

    @Override
    protected void initVIew() {
        lv = bindView(R.id.lv_frag_rank);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_rank;
    }
}
