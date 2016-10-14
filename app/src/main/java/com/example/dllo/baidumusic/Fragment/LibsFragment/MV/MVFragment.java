package com.example.dllo.baidumusic.Fragment.LibsFragment.MV;

import android.util.Log;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.Adapter.BAdapter.BTestAdapter;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.MVBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MVFragment extends BaseFragment {

    private GridView gv;
    private TextView hot;
    private TextView newTV;
    private TextView songTV;

    private ImageButton ibtn;
    private BTestAdapter mvgvAdapter;

    @Override
    protected void initData() {
        sendGet();
    }

    private void sendGet() {
        String url = URLVlaues.MV_NEW;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<MVBean> gsonRequest = new GsonRequest<MVBean>(url, MVBean.class, new Response.Listener<MVBean>() {



            @Override
            public void onResponse(MVBean response) {
                MVBean  mvBean = response;
                ArrayList<MVBean> mvBeanArrayList = new ArrayList<>();
                mvBeanArrayList.add(mvBean);
                ArrayList<MVBean.ResultBean.MvListBean> mvListBeanArrayList = (ArrayList<MVBean.ResultBean.MvListBean>) mvBean.getResult().getMv_list();
                mvgvAdapter = new BTestAdapter(getContext(), mvListBeanArrayList);
                mvgvAdapter.setPos(17);
                gv.setAdapter(mvgvAdapter);

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
        gv = bindView(R.id.gv_item_mv_frag);
        hot = bindView(R.id.tv_item_mv_frag_hot);
        newTV = bindView(R.id.tv_item_mv_frag_new);
        songTV = bindView(R.id.tv_mv_frag);
        ibtn = bindView(R.id.ibtn_mv_frag);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_mv;
    }
}
