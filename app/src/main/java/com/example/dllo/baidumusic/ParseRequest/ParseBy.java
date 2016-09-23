package com.example.dllo.baidumusic.ParseRequest;

/**
 * Created by dllo on 16/9/21.
 */
public class ParseBy {

    private String url;



    public ParseBy(String url) {
        this.url = url;

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
