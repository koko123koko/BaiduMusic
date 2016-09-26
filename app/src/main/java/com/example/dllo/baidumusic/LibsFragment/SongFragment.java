package com.example.dllo.baidumusic.LibsFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.SongBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class SongFragment extends BaseFragment {

    private RecyclerView rv;
    private SongAdapter songAdapter;

    @Override
    protected void initData() {

        songAdapter = new SongAdapter(getContext());
        sendGet();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());


        rv.setAdapter(songAdapter);
        rv.setLayoutManager(manager);
    }

    private void sendGet() {
        String url = URLVlaues.ALL_SONGLIST;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<SongBean> gsonRequest = new GsonRequest<SongBean>(url, SongBean.class, new Response.Listener<SongBean>() {
            @Override
            public void onResponse(SongBean response) {
                SongBean  songBean = response;
                ArrayList<SongBean> songBeanArray = new ArrayList<>();
                songBeanArray.add(songBean);
                ArrayList<SongBean.ContentBean> contentBeanArray = (ArrayList<SongBean.ContentBean>) songBean.getContent();

                songAdapter.setSongBeanArrayList(songBeanArray);
                songAdapter.setContentBeen(contentBeanArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RecommendFragment", "error:" + error);
            }
        });
        VolleySington.getInstance().addRequest(gsonRequest);

    }

    @Override
    protected void initVIew() {
        rv = bindView(R.id.rv_frag_song);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_song;
    }
}
