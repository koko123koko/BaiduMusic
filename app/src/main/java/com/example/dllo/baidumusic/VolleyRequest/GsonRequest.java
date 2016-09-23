package com.example.dllo.baidumusic.VolleyRequest;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/9/20.
 */
public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> tListener;
    private Class<T> tClass;

    public GsonRequest(int method, String url,
                       Class<T> tClass,
                       Response.Listener<T> tListener,
                       Response.ErrorListener listener) {
        super(method, url, listener);
        this.tListener = tListener;
        this.tClass = tClass;

    }

    public GsonRequest(String url,
                       Class<T> tClass,
                       Response.Listener<T> tListener,
                       Response.ErrorListener listener) {
        this(Method.GET, url, tClass, tListener, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        String parsed;
        Log.d("GsonRequest", "Gson-response");
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        Gson gson = new Gson();
        T t = gson.fromJson(parsed, tClass);
        Log.d("GsonRequest", "success");
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));

    }

    @Override
    protected void deliverResponse(T response) {
        tListener.onResponse(response);
    }

//    网络拉取失败 就拉取缓存
    @Override
    public void deliverError(VolleyError error) {
        if (error instanceof NoConnectionError) {
            Cache.Entry entry = this.getCacheEntry();
            if (entry != null) {
                Log.d("数据", "这是缓存数据");
                Response<T> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                deliverResponse(response.result);
                return;
            }
        }
        super.deliverError(error);

    }
}
