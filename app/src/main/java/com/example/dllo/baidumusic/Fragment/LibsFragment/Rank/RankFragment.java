package com.example.dllo.baidumusic.Fragment.LibsFragment.Rank;

import android.util.Log;
import android.view.View;
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

        View view = new View(mContext);
//        ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
//        params.height =  ViewGroup.LayoutParams.MATCH_PARENT;
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout.setLayoutParams(params);
//        lv.addHeaderView(linearLayout);
        sendGet();


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
