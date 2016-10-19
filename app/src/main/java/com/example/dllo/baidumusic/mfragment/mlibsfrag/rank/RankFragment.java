package com.example.dllo.baidumusic.mfragment.mlibsfrag.rank;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.musicbean.RankBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mvolley.GsonRequest;
import com.example.dllo.baidumusic.mvolley.URLVlaues;
import com.example.dllo.baidumusic.mvolley.VolleySington;

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

        View view = new View(mContext);
//        ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
//        params.height =  ViewGroup.LayoutParams.MATCH_PARENT;
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout.setLayoutParams(params);
//        lv.addHeaderView(linearLayout);
        sendGet();


        lv.setAdapter(rankLVAdapter);

        View footView = LayoutInflater.from(mContext).inflate(R.layout.item_list_foot,null);
        footView.setMinimumHeight(100);
        lv.addFooterView(footView);


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

                ArrayList<RankBean.Rank> contentBeanArrayList = (ArrayList<RankBean.Rank>) response.getContent();
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
