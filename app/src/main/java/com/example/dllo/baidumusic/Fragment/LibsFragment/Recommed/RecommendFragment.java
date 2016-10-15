package com.example.dllo.baidumusic.Fragment.LibsFragment.Recommed;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.dllo.baidumusic.Adapter.MyRVAdapter;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.ReommendBean.ModuleBean;
import com.example.dllo.baidumusic.Bean.ReommendBean.RecommBean;
import com.example.dllo.baidumusic.Fragment.LibsFragment.RecycleViewDivider;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView rv;
    private ConvenientBanner cb;
    private MyRVAdapter myRVAdapter;
    private RecommBean recommBean;
    private LinearLayoutManager manager;
    private SwipeRefreshLayout srf;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }

    @Override
    protected void initData() {
        rv.setHasFixedSize(true);
        myRVAdapter = new MyRVAdapter(getContext());
        srf.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendGet();
                        srf.setRefreshing(false);
                    }
                }, 2000);

            }
        });

        sendGet();
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(myRVAdapter);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(manager);
        rv.addItemDecoration(new RecycleViewDivider(getContext(), manager.getOrientation()));
        //        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //            @Override
        //            public void onRefresh() {
        //                sendGet();
        //            }
        //        });

    }

    @Override
    protected void initVIew() {
        cb = bindView(R.id.frag_recommend_cb);
        rv = bindView(R.id.frag_recommend_rv);
        srf = bindView(R.id.srf);

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

                Log.d("RecommendFragment", "recommBean.getModule().size():" + recommBean.getModule().size());

                Log.d("RecommendFragment", "moduleBean:" + recommBean);

                //                myRVAdapter.setRecommBean(recommBean);
                ArrayList<ModuleBean> moduleBean = new ArrayList<>();

                //                Log.d("RecommendFragment", "recommBean.getResult().getDiyBean():" + recommBean.getResult().getDiy());
                //                Log.d("RecommendFragment", "moduleBean.size():" + moduleBean.size());
                //                int size = recommBean.getModule().size();
                //                DiyBean diyBean = recommBean.getResult().getDiy();
                //
                //                EntryBean entryBean = recommBean.getResult().getEntry();
                //                FocusBean focusBean = recommBean.getResult().getFocus();
                //                RadioBean radioBean = recommBean.getResult().getRadio();
                //                RecsongBean recsongBean = recommBean.getResult().getRecsong();
                //                SceneBean sceneBean = recommBean.getResult().getScene();
                //                MixBean mix1 = recommBean.getResult().getMix_1();
                //                MixBean mix5 = recommBean.getResult().getMix_5();
                //                MixBean mix9 = recommBean.getResult().getMix_9();
                //                MixBean mix22 = recommBean.getResult().getMix_22();
                //                MixBean mod7 = recommBean.getResult().getMod_7();
                //                MixBean mod26 = recommBean.getResult().getMod_26();
                //                MixBean adsmall = recommBean.getResult().getAd_small();

                //                for (int i = 0; i < diyBean.getResult().size(); i++) {
                //                    Log.d("RecommendFragment", diyBean.getResult().get(i).getTitle());
                //                }


                //                for (int i = 0; i < recommBean.getModule().size(); i++) {
                //                    Log.d("RecommendFragment" + i, recommBean.getModule().get(i).getTitle());
                //                    Log.d("RecommendFragment", recommBean.getModule().get(i).getTitle_more());
                //                    Log.d("RecommendFragment", recommBean.getModule().get(i).getJump());
                //                    Log.d("RecommendFragment", recommBean.getModule().get(i).getKey());
                //                    Log.d("RecommendFragment", recommBean.getModule().get(i).getLink_url());
                //                    Log.d("RecommendFragment", recommBean.getModule().get(i).getPos() + "");
                //                    Log.d("RecommendFragment", recommBean.getModule().get(i).getStyle() + "");
                //
                //
                //                }

                for (int i = 0; i < recommBean.getModule().size(); i++) {
                    if (2 == i || 4 == i || 7 == i || 14 == i || 8 == i) {
                        continue;
                    }
                    moduleBean.add(recommBean.getModule().get(i));
                }
                myRVAdapter.setRecommBean(recommBean);
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

    @Override
    public void onClick(View view) {

    }
}
