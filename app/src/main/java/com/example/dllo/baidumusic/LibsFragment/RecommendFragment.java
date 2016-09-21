package com.example.dllo.baidumusic.LibsFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.dllo.baidumusic.Adapter.MyRVAdapter;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.RecommendBean;
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

    private ArrayList<RecommendBean.ResultBean> been;
    private MyRVAdapter myRVAdapter;
    private ArrayList<String> arrayList ;
    private RecommendBean recommBean;

    private RecommendBean.ResultBean.FocusBean focusBean;
    private ArrayList<String> imgs = new ArrayList<>();

    private RecommendBean.ResultBean.EntryBean entry;
    private ArrayList<String> entryImg = new ArrayList<>();

    @Override
    protected void initData() {

        arrayList = new ArrayList<>();
        arrayList.add("mix_9");
        arrayList.add("focus");
        arrayList.add("mix_22");
        arrayList.add("entry");
        arrayList.add("mod_7");
        arrayList.add("mix_5");
        arrayList.add("recsong");
        arrayList.add("diy");
        arrayList.add("radio");
        arrayList.add("scene");
        arrayList.add("mix_1");

        rv.setHasFixedSize(true);

        sendGet();
        myRVAdapter = new MyRVAdapter(getContext());
        myRVAdapter.setArrayList(arrayList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());


        rv.setAdapter(myRVAdapter);
        rv.setLayoutManager(manager);
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
        String url = URLVlaues.RECOMMAND;
        GsonRequest<RecommendBean> gsonRequest = new GsonRequest<RecommendBean>(url, RecommendBean.class, new Response.Listener<RecommendBean>() {
            @Override
            public void onResponse(RecommendBean response) {

                Log.d("MyRVAdapter", "response:" + response);
                recommBean = response;
                focusBean = recommBean.getResult().getFocus();
                for (int j = 0; j < focusBean.getResult().size(); j++) {

                    Log.d("MyRVAdapter", focusBean.getResult().get(j).getRandpic());
                    imgs.add(focusBean.getResult().get(j).getRandpic());
                }

                entry = recommBean.getResult().getEntry();
                for (int i = 0; i < entry.getResult().size(); i++) {
                    entryImg.add(entry.getResult().get(i).getIcon());
                    Log.d("RecommendFragment", entry.getResult().get(i).getIcon());

                }



                myRVAdapter.setRecommBean(recommBean);
                myRVAdapter.setFocusBean(focusBean);
                myRVAdapter.setImgs(imgs);
                myRVAdapter.setEntryBean(entry);
                myRVAdapter.setEntryImg(entryImg);
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
