package com.example.dllo.baidumusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.Bean.MVBean;
import com.example.dllo.baidumusic.Bean.RecommBean;
import com.example.dllo.baidumusic.Bean.SongBean;
import com.example.dllo.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 */
public class BTestAdapter<T> extends BHolderAdapter<T, BTestAdapter.ViewHolder> {

    int pos;
    private final DisplayImageOptions options;
    private ImageLoader imageLoader;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public BTestAdapter(Context context, List<T> arrayList) {
        super(context, arrayList);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    protected void bindViewDatas(BTestAdapter.ViewHolder holder, T t, int i) {

        switch (pos) {
            case 4:
                RecommBean.ResultBean.DiyBean.Diy diy = (RecommBean.ResultBean.DiyBean.Diy) t;
                //                imgToLoader(diy.getPic(), holder.iv);

                imageLoader.displayImage(diy.getPic(), holder.iv);
                holder.tv.setText(diy.getTitle());
                break;
            case 6:
                RecommBean.ResultBean.Mix1Bean.Mix1 mix1 = (RecommBean.ResultBean.Mix1Bean.Mix1) t;
                //                imgToLoader(mix1.getPic(), holder.iv);
                imageLoader.displayImage(mix1.getPic(), holder.iv);
                holder.tv.setText(mix1.getTitle());
                holder.author.setText(mix1.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;
            case 7:
                RecommBean.ResultBean.Mix22Bean.Mix22 mix22 = (RecommBean.ResultBean.Mix22Bean.Mix22) t;
                //                imgToLoader(mix22.getPic(), holder.iv);
                imageLoader.displayImage(mix22.getPic(), holder.iv);
                holder.tv.setText(mix22.getTitle());
                holder.author.setText(mix22.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;
            case 8:
                RecommBean.ResultBean.SceneBean.Scene.ActionBean actionBean = (RecommBean.ResultBean.SceneBean.Scene.ActionBean) t;
                //                imgToLoader(actionBean.getBgpic_android(), holder.imageViewBg);
                //                imgToLoader(actionBean.getIcon_android(), holder.imageViewSrc);
                imageLoader.displayImage(actionBean.getBgpic_android(), holder.imageViewBg);
                imageLoader.displayImage(actionBean.getIcon_android(), holder.imageViewSrc);
                holder.tvBg.setText(actionBean.getScene_desc());
                break;
            case 10:
                RecommBean.ResultBean.RecsongBean.Recsong recsong = (RecommBean.ResultBean.RecsongBean.Recsong) t;
                //                imgToLoader(recsong.getPic_premium(), holder.circleImage);
                imageLoader.displayImage(recsong.getPic_premium(), holder.circleImage);
                holder.tv.setText(recsong.getTitle());
                holder.author.setText(recsong.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;
            case 11:
                RecommBean.ResultBean.Mix9Bean.Mix9 mix9 = (RecommBean.ResultBean.Mix9Bean.Mix9) t;
                //                imgToLoader(mix9.getPic(), holder.iv);
                imageLoader.displayImage(mix9.getPic(), holder.iv);
                holder.tv.setText(mix9.getTitle());
                break;
            case 12:
                RecommBean.ResultBean.Mix5Bean.Mix5 mix5 = (RecommBean.ResultBean.Mix5Bean.Mix5) t;
                //                imgToLoader(mix5.getPic(), holder.iv);
                imageLoader.displayImage(mix5.getPic(), holder.iv);
                holder.tv.setText(mix5.getTitle());
                holder.author.setText(mix5.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                break;
            case 13:
                RecommBean.ResultBean.RadioBean.Radio radio = (RecommBean.ResultBean.RadioBean.Radio) t;
                //                imgToLoader(radio.getPic(), holder.iv);
                imageLoader.displayImage(radio.getPic(), holder.iv);
                holder.tv.setText(radio.getTitle());
                break;
            case 14:
                RecommBean.ResultBean.Mod7Bean.Mod7 mod7 = (RecommBean.ResultBean.Mod7Bean.Mod7) t;
                //                imgToLoader(mod7.getPic(), holder.circleImage);
                imageLoader.displayImage(mod7.getPic(), holder.circleImage);
                holder.tv.setText(mod7.getTitle());
                holder.author.setText(mod7.getDesc());
                holder.author.setVisibility(View.VISIBLE);
                holder.imageButton.setBackgroundResource(R.mipmap.ic_songlist_detail_nor);

                break;
            case 16:
                SongBean.DiyInfoBean contentBean = (SongBean.DiyInfoBean) t;

                //                imgToLoader(contentBean.getPic_300(),holder.ivSong);
                imageLoader.displayImage(contentBean.getList_pic(), holder.ivSong);
                holder.numSong.setText(contentBean.getListen_num()+"");
                holder.titleSong.setText(contentBean.getTitle());
                holder.authorSong.setText("by " + contentBean.getUsername());
                break;

            case 17:
                MVBean.ResultBean.MvListBean mvListBean = (MVBean.ResultBean.MvListBean) t;
                holder.tvArtist.setText(mvListBean.getArtist());
                holder.tvTitle.setText(mvListBean.getTitle());
                imageLoader.displayImage(mvListBean.getThumbnail2(),holder.ivMV);


        }
    }

    @Override
    protected BTestAdapter.ViewHolder buildHolder(View view, T t, int i) {
        ViewHolder viewHolder = new ViewHolder();
        if (pos == 17){
           viewHolder.tvTitle = bindView(R.id.tv_mv_list_title,view);
           viewHolder.ivMV = bindView(R.id.iv_mv_list,view);
           viewHolder.tvArtist = bindView(R.id.tv_mv_list_artist,view);
        }


        if (pos == 16) {

            viewHolder.ivSong = bindView (R.id.iv_item_song_list,view);//(ImageView) view.findViewById(R.id.iv_item_song_list);
            viewHolder.numSong = bindView (R.id.tv_item_song_list_num,view);//(TextView) view.findViewById(R.id.tv_item_song_list_num);
            viewHolder.authorSong = bindView (R.id.tv_item_song_list_author,view);//(TextView) view.findViewById(R.id.tv_item_song_list_author);
            viewHolder.titleSong = bindView (R.id.tv_item_song_list_title,view);//(TextView) view.findViewById(R.id.tv_item_song_list_title);
            viewHolder.iBtnSong = bindView (R.id.ibtn_item_song_list,view);//(ImageButton) view.findViewById(R.id.ibtn_item_song_list);

        }

        if (pos == 8) {
            viewHolder.imageViewBg = bindView (R.id.iv_item_base_scene_blue,view);//(ImageView) view.findViewById(R.id.iv_item_base_scene);
            viewHolder.imageViewSrc = bindView (R.id.iv_item_base_scene_icon_blue,view);//(ImageView) view.findViewById(R.id.iv_item_base_scene_icon);
            viewHolder.tvBg = bindView (R.id.tv_item_base_scene_blue,view);//(TextView) view.findViewById(R.id.tv_item_base_scene);
        }

        if (pos == 10 || pos == 14) {

            viewHolder.circleImage = bindView (R.id.item_base_resong_div_img,view);//(ImageView) view.findViewById(R.id.item_base_resong_div_img);
            viewHolder.tv = bindView (R.id.tv_item_base_resong_name,view);//(TextView) view.findViewById(R.id.tv_item_base_resong_name);
            viewHolder.author = bindView (R.id.tv_item_base_resong_author,view);//(TextView) view.findViewById(R.id.tv_item_base_resong_author);
            viewHolder.imageButton = bindView (R.id.ibtn_item_base_resong,view);//(ImageButton) view.findViewById(R.id.ibtn_item_base_resong);

        } else {

            viewHolder.iv = bindView (R.id.iv_item_base,view);
            viewHolder.tv = bindView (R.id.tv_item_base,view);
            viewHolder.author = bindView (R.id.tv_item_base_author,view);
            viewHolder.ll = bindView (R.id.ll_item_base,view);
        }
        return viewHolder;
    }

    @Override
    protected View buildConvertView(LayoutInflater layoutInflater, T t, int i) {

        if(pos == 17){
            return inflate(R.layout.item_mv_list);
        }

        if (pos == 16) {
            return inflate(R.layout.item_song_list);
        }

        if (pos == 10 || pos == 14) {
            return inflate(R.layout.item_base_recsong);
        }
        if (pos == 8) {
            return inflate(R.layout.item_base_scene);
        }
        return inflate(R.layout.item_base);
    }

    public class ViewHolder {
        ImageView iv;
        TextView tv, author, tvBg;
        LinearLayout ll;

        ImageView circleImage, imageViewBg, imageViewSrc;
        ImageButton imageButton;

        //16
        ImageView ivSong;
        TextView numSong, authorSong, titleSong;
        ImageButton iBtnSong;

        //17
        ImageView ivMV;
        TextView tvTitle,tvArtist;

    }

    protected <V extends View> V bindView(int id, View view) {
        return (V) view.findViewById(id);
    }

}
