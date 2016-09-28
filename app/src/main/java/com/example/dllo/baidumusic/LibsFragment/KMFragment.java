package com.example.dllo.baidumusic.LibsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.KMBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.DisplaySingle;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class KMFragment extends BaseFragment {

    private RecyclerView rv;
    private ConvenientBanner cb;
    private ImageLoader imageLoader;
    private ListView lv;
    private KMLVAdapter kmlvAdapter;

    @Override
    protected void initData() {


        imageLoader = ImageLoader.getInstance();
        ArrayList<String> focusArr = new ArrayList<>();
        focusArr.add("http://musicdata.baidu.com/data2/pic/c05365550f8ad742e54425900c28662d/246706976/246706976.jpg");
        focusArr.add("http://musicdata.baidu.com/data2/pic/dcf2261041b080d0e22d7583f115ae0c/246585815/246585815.jpg");
        //        BTestAdapter KMAdapter = new BTestAdapter(getContext(),);
        //        rv.setAdapter();
        cb.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, focusArr)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageIndicator(new int[]{R.mipmap.ic_dot_default_unselected, R.mipmap.ic_dot_default_selected})
        ;
        cb.startTurning(5000);

        kmlvAdapter = new KMLVAdapter(getContext());
        sendGet();
        lv.setAdapter(kmlvAdapter);

    }

    @Override
    protected void initVIew() {
        cb = bindView(R.id.cb_frag_km);
        lv = bindView(R.id.lv_frag_km);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_km;
    }

    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
//            imageLoader.displayImage(data, imageView);
            //            Picasso.with(context).load(data).into(imageView);

            DisplaySingle.getInstance().show(data,imageView);
        }
    }

    private void sendGet() {
        String url = URLVlaues.KTV;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<KMBean> gsonRequest = new GsonRequest<KMBean>(url, KMBean.class, new Response.Listener<KMBean>() {


            @Override
            public void onResponse(KMBean response) {

                KMBean kmBean = response;

                ArrayList<KMBean.ResultBean.ItemsBean> itemsBean = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    itemsBean.add(kmBean.getResult().getItems().get(i));
                }
                kmlvAdapter.setKmBeanArrayList(itemsBean);


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
