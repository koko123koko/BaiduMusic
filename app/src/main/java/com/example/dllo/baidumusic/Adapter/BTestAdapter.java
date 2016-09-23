package com.example.dllo.baidumusic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.dllo.baidumusic.Bean.RecommBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 */
public class BTestAdapter<T> extends BHolderAdapter<T, BTestAdapter.ViewHolder> {


    int pos;


    public void setPos(int pos) {
        this.pos = pos;
    }

    public BTestAdapter(Context context, List<T> arrayList) {
        super(context, arrayList);
    }

    @Override
    protected void bindViewDatas(BTestAdapter.ViewHolder holder, T t, int i) {

        switch (pos) {
            case 4:
                RecommBean.ResultBean.DiyBean.Diy diy = (RecommBean.ResultBean.DiyBean.Diy) t;
                imgToLoader(diy.getPic(), holder.iv);
                holder.tv.setText(diy.getTitle());
                break;

            case 6:
                RecommBean.ResultBean.Mix1Bean.Mix1 mix1 = (RecommBean.ResultBean.Mix1Bean.Mix1) t;
                imgToLoader(mix1.getPic(), holder.iv);
                holder.tv.setText(mix1.getTitle());
                holder.author.setText(mix1.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;
            case 7:
                RecommBean.ResultBean.Mix22Bean.Mix22 mix22 = (RecommBean.ResultBean.Mix22Bean.Mix22) t;
                imgToLoader(mix22.getPic(), holder.iv);
                holder.tv.setText(mix22.getTitle());
                holder.author.setText(mix22.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;

            case 8:
                RecommBean.ResultBean.SceneBean.Scene.ActionBean actionBean = (RecommBean.ResultBean.SceneBean.Scene.ActionBean)t;
               imgToLoader(actionBean.getBgpic_android(),holder.imageViewBg);
                imgToLoader(actionBean.getIcon_android(),holder.imageViewSrc);
                holder.tvBg.setText(actionBean.getScene_desc());
                break;
            case 10:
                RecommBean.ResultBean.RecsongBean.Recsong recsong = (RecommBean.ResultBean.RecsongBean.Recsong) t;



                imgToLoader(recsong.getPic_premium(), holder.circleImage);

                holder.tv.setText(recsong.getTitle());
                holder.author.setText(recsong.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;
            case 11:
                RecommBean.ResultBean.Mix9Bean.Mix9 mix9 = (RecommBean.ResultBean.Mix9Bean.Mix9)t;
                imgToLoader(mix9.getPic(), holder.iv);
                holder.tv.setText(mix9.getTitle());
                break;
            case 12:
                RecommBean.ResultBean.Mix5Bean.Mix5 mix5 = (RecommBean.ResultBean.Mix5Bean.Mix5)t;
                imgToLoader(mix5.getPic(), holder.iv);

                holder.tv.setText(mix5.getTitle());
                holder.author.setText(mix5.getAuthor());
                holder.author.setVisibility(View.VISIBLE);

                break;
            case 13:
                RecommBean.ResultBean.RadioBean.Radio radio = (RecommBean.ResultBean.RadioBean.Radio)t;
                imgToLoader(radio.getPic(), holder.iv);
                holder.tv.setText(radio.getTitle());
                break;
            case 14:

                RecommBean.ResultBean.Mod7Bean.Mod7 mod7 = (RecommBean.ResultBean.Mod7Bean.Mod7)t;

                imgToLoader(mod7.getPic(), holder.circleImage);

                holder.tv.setText(mod7.getTitle());
                holder.author.setText(mod7.getDesc());
                holder.author.setVisibility(View.VISIBLE);
                holder.imageButton.setBackgroundResource(R.mipmap.ic_songlist_detail_nor);
                break;


        }
    }

    @Override
    protected BTestAdapter.ViewHolder buildHolder(View view, T t, int i) {
        ViewHolder viewHolder = new ViewHolder();

        if(pos == 8){
            viewHolder.imageViewBg = (ImageView) view.findViewById(R.id.iv_item_base_scene);
            viewHolder.imageViewSrc = (ImageView) view.findViewById(R.id.iv_item_base_scene_icon);
            viewHolder.tvBg = (TextView) view.findViewById(R.id.tv_item_base_scene);
        }

        if (pos == 10 || pos == 14) {

            viewHolder.circleImage = (ImageView) view.findViewById(R.id.item_base_resong_div_img);
            viewHolder.tv = (TextView) view.findViewById(R.id.tv_item_base_resong_name);
            viewHolder.author = (TextView) view.findViewById(R.id.tv_item_base_resong_author);
            viewHolder.imageButton = (ImageButton) view.findViewById(R.id.ibtn_item_base_resong);

        } else {

            viewHolder.iv = (ImageView) view.findViewById(R.id.iv_item_base);
            viewHolder.tv = (TextView) view.findViewById(R.id.tv_item_base);
            viewHolder.author = (TextView) view.findViewById(R.id.tv_item_base_author);
            viewHolder.ll = (LinearLayout) view.findViewById(R.id.ll_item_base);
        }
        return viewHolder;
    }

    @Override
    protected View buildConvertView(LayoutInflater layoutInflater, T t, int i) {
        if (pos == 10 || pos == 14) {
            return inflate(R.layout.item_base_recsong);
        }
        if (pos == 8){
            return inflate(R.layout.item_base_scene);
        }
        return inflate(R.layout.item_base);
    }

    public class ViewHolder {
        ImageView iv;
        TextView tv, author,tvBg;
        LinearLayout ll;

        ImageView circleImage,imageViewBg,imageViewSrc;
        ImageButton imageButton;


    }

    public void imgToLoader(String url, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url,
                new com.android.volley.Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {


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
