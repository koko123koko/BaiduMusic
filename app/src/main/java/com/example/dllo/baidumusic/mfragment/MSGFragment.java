package com.example.dllo.baidumusic.mfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.musicbean.MSGBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mvolley.GsonRequest;
import com.example.dllo.baidumusic.mvolley.URLVlaues;
import com.example.dllo.baidumusic.mvolley.VolleySington;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class MSGFragment extends BaseFragment {

    private RecyclerView rv;
    private MSGAdapter msgAdapter;

    @Override
    protected void initData() {
//        BTestAdapter bTestAdapter = new BTestAdapter(getContext(),);
//
//        rv.setAdapter(bTestAdapter);


        sendGet();

        msgAdapter = new MSGAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);

    }

    @Override
    protected void initVIew() {

        rv = bindView(R.id.rv_frag_msgmusic);

    }

    @Override
    protected int setLayout() {
        return R.layout.frag_msgmusic;
    }

    private void sendGet() {
        String url = URLVlaues.MSG;

        GsonRequest<MSGBean> gsonRequest = new GsonRequest<MSGBean>(url, MSGBean.class, new Response.Listener<MSGBean>() {


            private List<MSGBean.MsgBean> msg;

            @Override
            public void onResponse(MSGBean response) {

                MSGBean msgBean = response;
                msg = msgBean.getMsg();

                msgAdapter.setMsgBeanArrayList(msg);
                rv.setAdapter(msgAdapter);


//                msgBean.getTopics();
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
