package com.example.dllo.baidumusic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.dllo.baidumusic.Bean.RecommendBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MyRVAdapter extends RecyclerView.Adapter {

    private Context context;
    private RecommendBean recommBean;
    private ArrayList<String> arrayList;
    private ArrayList<RecommendBean.ResultBean.FocusBean> focusBeanArrayList;
    private RecommendBean.ResultBean.FocusBean focusBean;
    private ArrayList<String> imgs;

    private RecommendBean.ResultBean.EntryBean entryBean;
    private ArrayList<String> entryImg;

    public void setEntryBean(RecommendBean.ResultBean.EntryBean entryBean) {
        this.entryBean = entryBean;
    }

    public void setEntryImg(ArrayList<String> entryImg) {
        this.entryImg = entryImg;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;

    }

    public MyRVAdapter(Context context) {
        this.context = context;
    }

    public void setRecommBean(RecommendBean recommBean) {
        this.recommBean = recommBean;
        init();
        notifyDataSetChanged();
    }

    private void init() {
        this.entryBean = recommBean.getResult().getEntry();


    }

    public void setFocusBean(RecommendBean.ResultBean.FocusBean focusBean) {
        this.focusBean = focusBean;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        String name = "focus";

        switch (name) {

            case "mix_9":
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                MyMusicViewHolder myMusicViewHolder = new MyMusicViewHolder(view1);

                return myMusicViewHolder;
            case "focus":
                View view = LayoutInflater.from(context).inflate(R.layout.item_adv_btn, parent, false);
                MyCBViewHolder myCBViewHolder = new MyCBViewHolder(view);

                return myCBViewHolder;


        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        String name = "focus";
        switch (name) {
            case "mix_9":
                MyMusicViewHolder myMusicViewHolder = (MyMusicViewHolder) holder;
                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(context);
                myMusicViewHolder.gv.setAdapter(myBaseAdapter);
                break;
            case "focus":

                MyCBViewHolder cbViewHolder = (MyCBViewHolder) holder;
                cbViewHolder.cb.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, imgs)
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setPageIndicator(new int[]{R.mipmap.ic_dot_default_unselected, R.mipmap.ic_dot_default_selected});

                if (entryBean != null) {
                    imgToLoader(entryImg.get(0), cbViewHolder.allSong);
                    imgToLoader(entryImg.get(1), cbViewHolder.classify);
                    imgToLoader(entryImg.get(2), cbViewHolder.btnCommand);
                    cbViewHolder.day.setText(entryBean.getResult().get(2).getDay());

                }

                break;

        }

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void imgToLoader(String url, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url,
                new com.android.volley.Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.d("RecommendFragment", "imageRequest");

                        imageView.setImageBitmap(response);

                    }
                }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        VolleySington.getInstance().addRequest(imageRequest);


    }


    class MyCBViewHolder extends RecyclerView.ViewHolder {


        TextView day;
        ImageButton allSong;
        ImageButton classify;
        ImageButton btnCommand;
        ConvenientBanner cb;

        public MyCBViewHolder(View itemView) {
            super(itemView);
            cb = (ConvenientBanner) itemView.findViewById(R.id.frag_recommend_cb);
            allSong = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_all);
            classify = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_classify);
            btnCommand = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_command);
            day = (TextView) itemView.findViewById(R.id.tv_item_adv_day);
        }
    }

    class MyMusicViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final GridView gv;

        public MyMusicViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_more_item_rv_recommend);
            gv = (GridView) itemView.findViewById(R.id.gv_item_rv_recommend);

        }
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
            //            Picasso.with(context).load(data).into(imageView);
            imgLoader(data);

        }

        public void imgLoader(String url) {
            ImageRequest imageRequest = new ImageRequest(url,
                    new com.android.volley.Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            Log.d("RecommendFragment", "imageRequest");
                            imageView.setImageBitmap(response);

                        }
                    }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }

            });

            VolleySington.getInstance().addRequest(imageRequest);


        }
    }

}
