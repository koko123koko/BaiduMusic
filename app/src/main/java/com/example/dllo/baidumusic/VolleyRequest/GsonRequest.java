package com.example.dllo.baidumusic.VolleyRequest;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
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
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        Gson gson = new Gson();

        return Response.success(gson.fromJson(parsed, tClass), HttpHeaderParser.parseCacheHeaders(response));

    }

    @Override
    protected void deliverResponse(T response) {
        tListener.onResponse(response);
    }
}
