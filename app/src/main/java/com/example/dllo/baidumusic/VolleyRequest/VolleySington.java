package com.example.dllo.baidumusic.VolleyRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/9/20.
 */
public class VolleySington {

    private static VolleySington volleySington;
    private RequestQueue requestQueue;


    private VolleySington() {
        requestQueue = Volley.newRequestQueue(MyApp.getContext());
    }

    public static VolleySington getInstance() {
        if (volleySington == null) {
            synchronized (VolleySington.class) {
                if (volleySington == null) {
                    volleySington = new VolleySington();
                }
            }
        }
        return volleySington;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void addRequest(Request request) {
        requestQueue.add(request);
    }
}
