package com.example.dllo.baidumusic.LibsFragment;

import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.RankBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RankFragment extends BaseFragment {

    private ListView lv;
    private RankLVAdapter rankLVAdapter;

    @Override
    protected void initData() {

        rankLVAdapter = new RankLVAdapter(getContext());
        sendGet();
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("独角戏 - 许茹芸");
//        arrayList.add("万事如风 - 齐秦");
//        arrayList.add("偏偏喜欢你 - 陈百强");
//
//        ArrayList<Integer> imgs = new ArrayList<>();
//        ArrayList<String> top = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            top.add(""+i);
//            imgs.add(R.mipmap.ic_launcher);
//        }
//        rankLVAdapter.setArrayList(arrayList);
//        rankLVAdapter.setTop(top);
//        rankLVAdapter.setImgs(imgs);
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

    private void sendGet() {
        String url = URLVlaues.MUSICSTORE_TOP;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<RankBean> gsonRequest = new GsonRequest<RankBean>(url, RankBean.class, new Response.Listener<RankBean>() {
            @Override
            public void onResponse(RankBean response) {
                RankBean  rankBean = response;
                ArrayList<RankBean.Rank> contentBeanArrayList = (ArrayList<RankBean.Rank>) rankBean.getContent();
                Log.d("RankFragment", "contentBeanArrayList.size():" + contentBeanArrayList.size());
                rankLVAdapter.setArrayList(contentBeanArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RecommendFragment", "error:" + error);
            }
        });
        VolleySington.getInstance().addRequest(gsonRequest);

    }
}
