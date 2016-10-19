package com.example.dllo.baidumusic.mfragment.mlibsfrag.song;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.adapterbase.baseadapter.BTestAdapter;
import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.musicbean.SongBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mvolley.GsonRequest;
import com.example.dllo.baidumusic.mvolley.URLVlaues;
import com.example.dllo.baidumusic.mvolley.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class SongFragment extends BaseFragment {

    private RecyclerView rv;
    private SongAdapter songAdapter;
    TextView hot;
    TextView newTV;
    TextView songTV;
    GridView gv;
    ImageButton ibtn;
    private BTestAdapter songGVAdapter;

    @Override
    protected void initData() {


        sendGet();

        //        songAdapter = new SongAdapter(getContext());
        //        sendGet();
        //
        //        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //
        //
        //        rv.setAdapter(songAdapter);
        //        rv.setLayoutManager(manager);
    }

    private void sendGet() {
                String url = URLVlaues.SONG_HOT;
                Log.d("RecommendFragment", "sendGet");
                GsonRequest<SongBean> gsonRequest = new GsonRequest<SongBean>(url, SongBean.class, new Response.Listener<SongBean>() {
                    @Override
                    public void onResponse(SongBean response) {

                        ArrayList<SongBean> songBeanArray = new ArrayList<>();
                        songBeanArray.add(response);
                        ArrayList<SongBean.DiyInfoBean> contentBeanArray = (ArrayList<SongBean.DiyInfoBean>) response.getDiyInfo();
                        songGVAdapter = new BTestAdapter<>(getContext(), contentBeanArray);
                        songGVAdapter.setPos(16);
                        gv.setAdapter(songGVAdapter);

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
        //        rv = bindView(R.id.rv_frag_song);

        hot = bindView(R.id.tv_item_rv_frag_song_hot);
        newTV = bindView(R.id.tv_item_rv_frag_song_new);
        songTV = bindView(R.id.tv_song_frag);
        gv = bindView(R.id.gv_item_rv_song);
        ibtn = bindView(R.id.ibtn_song_frag);
//        ViewCompat.setNestedScrollingEnabled(gv,true);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_song;
    }
}
