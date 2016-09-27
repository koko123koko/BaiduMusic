package com.example.dllo.baidumusic.LibsFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.dllo.baidumusic.Adapter.MyRVAdapter;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.RecommBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment {

    private RecyclerView rv;
    private ConvenientBanner cb;
    private MyRVAdapter myRVAdapter;
    private RecommBean recommBean;
    private LinearLayoutManager manager;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//            sendGet();
        }
    }
    @Override
    protected void initData() {
        rv.setHasFixedSize(true);
        myRVAdapter = new MyRVAdapter(getContext());


        sendGet();
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(myRVAdapter);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        rv.addItemDecoration(new RecycleViewDivider(getContext(),manager.getOrientation()));
    }

    @Override
    protected void initVIew() {
        cb = bindView(R.id.frag_recommend_cb);
        rv = bindView(R.id.frag_recommend_rv);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_recommend;
    }

    private void sendGet() {
        String url = URLVlaues.RECOMMAND_ALL;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<RecommBean> gsonRequest = new GsonRequest<RecommBean>(url, RecommBean.class, new Response.Listener<RecommBean>() {
            @Override
            public void onResponse(RecommBean response) {
                recommBean = response;

                myRVAdapter.setRecommBean(recommBean);
                ArrayList<RecommBean.ModuleBean> moduleBean = new ArrayList<>();
                Log.d("RecommendFragment", "moduleBean.size():" + moduleBean.size());
                int size = recommBean.getModule().size();
                for (int i = 0; i < recommBean.getModule().size(); i++) {
                    if(2 == i || 4 == i || 7 == i ||  14 == i|| 8 == i){
                        continue;
                    }
                    moduleBean.add(recommBean.getModule().get(i));
                }
                myRVAdapter.setModuleBeanArrayList(moduleBean);
                myRVAdapter.notifyDataSetChanged();
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
