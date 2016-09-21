package com.example.dllo.baidumusic.ParseRequest;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.Bean.RecommendBean;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

/**
 * Created by dllo on 16/9/21.
 */
public class ParseBy {

    private String url;



    public ParseBy(String url) {
        this.url = url;

    }

    public void sendGet() {


        GsonRequest<RecommendBean.ResultBean.FocusBean> gsonRequest = new GsonRequest<RecommendBean.ResultBean.FocusBean>(url, RecommendBean.ResultBean.FocusBean.class, new Response.Listener<RecommendBean.ResultBean.FocusBean>() {
            @Override
            public void onResponse(RecommendBean.ResultBean.FocusBean response) {
                Log.d("RecommendFragment", "response:" + response);
                //                been = new ArrayList<>();
                //                been.add(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RecommendFragment", "error:" + error);
            }
        });

        VolleySington.getInstance().addRequest(gsonRequest);
    }

    //    public void imgLoader(String url) {
    //        ImageRequest imageRequest = new ImageRequest(url,
    //                new com.android.volley.Response.Listener<Bitmap>() {
    //                    @Override
    //                    public void onResponse(Bitmap response) {
    //                        Log.d("RecommendFragment", "imageRequest");
    //                        bitmaps.add(response);
    //                    }
    //                }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
    //            @Override
    //            public void onErrorResponse(VolleyError error) {
    //
    //            }
    //
    //        });
    //
    //        VolleySington.getInstance().addRequest(imageRequest);
    //
    //
    //    }
}
