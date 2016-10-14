package com.example.dllo.baidumusic.Fragment.LibsFragment.Recommed.Songer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bean.SongerBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.DisplaySingle;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/29.
 */
public class SongerFrag extends BaseFragment {


    private ListView lv;
    private ArrayList<ArrayList<SongerBean.ArtistBean>> artistBeen = new ArrayList<>();
    private ConvenientBanner cb;


    @Override
    protected void initData() {

        sendGet();
        SongerAdapter songerAdapter = new SongerAdapter(getContext());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("华语男歌手");
        arrayList.add("华语女歌手");
        arrayList.add("华语组合");

        arrayList.add("欧美男歌手");
        arrayList.add("欧美女歌手");
        arrayList.add("欧美组合");

        arrayList.add("韩国男歌手");
        arrayList.add("韩国女歌手");
        arrayList.add("韩国组合");

        arrayList.add("日本男歌手");
        arrayList.add("日本女歌手");
        arrayList.add("日本组合");

        arrayList.add("其他歌手");

        songerAdapter.setArrayList(arrayList);


        lv.setAdapter(songerAdapter);


    }

    @Override
    protected void initVIew() {

        lv = bindView(R.id.lv_item_songer);

    }

    @Override
    protected int setLayout() {
        return R.layout.item_songer;
    }

    private void sendGet() {
        String url = URLVlaues.SONGER;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<SongerBean> gsonRequest = new GsonRequest<SongerBean>(url, SongerBean.class, new Response.Listener<SongerBean>() {


            //            private ArrayList<SongerBean.ArtistBean> artistBeen;

            @Override
            public void onResponse(SongerBean response) {

                SongerBean songerBean = response;

                //                ArrayList<KMBean.ResultBean.ItemsBean> itemsBean = new ArrayList<>();



                View headView = LayoutInflater.from(mContext).inflate(R.layout.item_songer_head, null,false);



                cb = bindView(R.id.cb_item_songer_head, headView);
                ViewGroup.LayoutParams params = cb.getLayoutParams();
                params.height = 300;
                cb.setLayoutParams(params);
                ArrayList<SongerBean.ArtistBean> artistBeen1 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    artistBeen1.add(songerBean.getArtist().get(i * 3));
                    artistBeen1.add(songerBean.getArtist().get(i * 3+1));
                    artistBeen1.add(songerBean.getArtist().get(i * 3+2));
                    artistBeen.add(artistBeen1);
                }




                cb.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, artistBeen)
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setPageIndicator(new int[]{R.mipmap.ic_dot_default_unselected, R.mipmap.ic_dot_default_selected})
                ;
                cb.startTurning(5000);

                //                for (int i = 0; i < songerBean.getArtist().size(); i++) {
                //                    itemsBean.add(kmBean.getResult().getItems().get(i));
                //                }
                //                kmlvAdapter.setKmBeanArrayList(itemsBean);

                lv.addHeaderView(headView);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RecommendFragment", "error:" + error);
            }
        });
        VolleySington.getInstance().addRequest(gsonRequest);

    }

    public class LocalImageHolderView implements Holder<ArrayList<SongerBean.ArtistBean>> {
        private ImageView iv1;
        private ImageView iv2;
        private ImageView iv3;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        //        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //            imageView = new ImageView(context);
            //            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            View view = LayoutInflater.from(context).inflate(R.layout.item_songer_cb, null);

            iv1 = bindView(R.id.iv1_item_songer_cb, view);
            iv2 = bindView(R.id.iv2_item_songer_cb, view);
            iv3 = bindView(R.id.iv3_item_songer_cb, view);
            tv1 = bindView(R.id.tv1_item_songer_cb, view);
            tv2 = bindView(R.id.tv2_item_songer_cb, view);
            tv3 = bindView(R.id.tv3_item_songer_cb, view);


            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, ArrayList<SongerBean.ArtistBean> data) {
            //            imageLoader.displayImage(data,imageView);
            //            Picasso.with(context).load(data).into(imageView);
            //            DisplaySingle.getInstance().show(data, imageView);

            DisplaySingle.getInstance().show(data.get(position * 3).getAvatar_big(), iv1);
            DisplaySingle.getInstance().show(data.get(position * 3 + 1).getAvatar_big(), iv2);
            DisplaySingle.getInstance().show(data.get(position * 3 + 2).getAvatar_big(), iv3);
            tv1.setText(data.get(position * 3).getName());
            tv2.setText(data.get(position * 3 + 1).getName());
            tv3.setText(data.get(position * 3 + 2).getName());

        }


    }
}
