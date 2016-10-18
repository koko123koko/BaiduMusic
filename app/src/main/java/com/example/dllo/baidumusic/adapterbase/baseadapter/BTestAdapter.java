package com.example.dllo.baidumusic.adapterbase.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.musicbean.MVBean;
import com.example.dllo.baidumusic.musicbean.RecommBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.DiyBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.MixBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.RadioBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.RecsongBean;
import com.example.dllo.baidumusic.musicbean.SongBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mvolley.DisplaySingle;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 */
public class BTestAdapter<T> extends BHolderAdapter<T, BTestAdapter.ViewHolder> {

    int pos;
//    private final DisplayImageOptions options;
//    private ImageLoader imageLoader;

    //pos -- 根据pos确定


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    //构造方法
    public BTestAdapter(Context context, List<T> arrayList) {
        super(context, arrayList);
//        imageLoader = ImageLoader.getInstance();
    }
    //绑定视图数据
    @Override
    protected void bindViewDatas(BTestAdapter.ViewHolder holder, T t, int i) {

        switch (pos) {
            case 4:
                DiyBean.Diy diy = (DiyBean.Diy) t;
                //                imgToLoader(diy.getPic(), holder.iv);

                DisplaySingle.getInstance().show(diy.getPic(), holder.iv);
                //                imageLoader.displayImage(diy.getPic(), holder.iv);
                holder.tv.setText(diy.getTitle());
                holder.listId.setText(diy.getListid());
                break;
            case 6:
                MixBean.Mix mix1 = (MixBean.Mix) t;
                //                imgToLoader(mix1.getPic(), holder.iv);
                DisplaySingle.getInstance().show(mix1.getPic(), holder.iv);
                //                imageLoader.displayImage(mix1.getPic(), holder.iv);
                holder.tv.setText(mix1.getTitle());
                holder.author.setText(mix1.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                holder.listId.setText(mix1.getType_id());
                break;
            case 7:
                MixBean.Mix mix22 = (MixBean.Mix) t;
                //                imgToLoader(mix22.getPic(), holder.iv);
                //                imageLoader.displayImage(mix22.getPic(), holder.iv);
                DisplaySingle.getInstance().show(mix22.getPic(), holder.iv);
                holder.tv.setText(mix22.getTitle());
                holder.author.setText(mix22.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                holder.listId.setText(mix22.getType_id());
                break;
            case 8:
                RecommBean.ResultBean.SceneBean.Scene.ActionBean actionBean = (RecommBean.ResultBean.SceneBean.Scene.ActionBean) t;
                //                imgToLoader(actionBean.getBgpic_android(), holder.imageViewBg);
                //                imgToLoader(actionBean.getIcon_android(), holder.imageViewSrc);
                //                imageLoader.displayImage(actionBean.getBgpic_android(), holder.imageViewBg);
                //                imageLoader.displayImage(actionBean.getIcon_android(), holder.imageViewSrc);
                DisplaySingle.getInstance().show(actionBean.getBgpic_android(), holder.imageViewBg);
                DisplaySingle.getInstance().show(actionBean.getIcon_android(), holder.imageViewSrc);
                holder.tvBg.setText(actionBean.getScene_desc());
                break;
            case 10:
                RecsongBean.Recsong recsong = (RecsongBean.Recsong) t;
                //                imgToLoader(recsong.getPic_premium(), holder.circleImage);
                //                imageLoader.displayImage(recsong.getPic_premium(), holder.circleImage);
                DisplaySingle.getInstance().show(recsong.getPic_premium(), holder.circleImage);
                holder.tv.setText(recsong.getTitle());
                holder.author.setText(recsong.getAuthor());
                holder.author.setVisibility(View.VISIBLE);

                break;
            case 11:
                MixBean.Mix mix9 = ( MixBean.Mix) t;
                //                imgToLoader(mix9.getPic(), holder.iv);
                //                imageLoader.displayImage(mix9.getPic(), holder.iv);
                DisplaySingle.getInstance().show(mix9.getPic(), holder.iv);
                holder.tv.setText(mix9.getTitle());
                holder.listId.setText(mix9.getType_id());
                break;
            case 12:
                MixBean.Mix mix5 = (MixBean.Mix) t;
                //                imgToLoader(mix5.getPic(), holder.iv);
                //                imageLoader.displayImage(mix5.getPic(), holder.iv);
                DisplaySingle.getInstance().show(mix5.getPic(), holder.iv);
                holder.tv.setText(mix5.getTitle());
                holder.author.setText(mix5.getAuthor());
                holder.author.setVisibility(View.VISIBLE);
                holder.listId.setText(mix5.getType_id());
                break;
            case 13:
               RadioBean.Radio radio = (RadioBean.Radio) t;
                //                imgToLoader(radio.getPic(), holder.iv);
                //                imageLoader.displayImage(radio.getPic(), holder.iv);
                DisplaySingle.getInstance().show(radio.getPic(), holder.iv);
                holder.tv.setText(radio.getTitle());
                holder.listId.setText(radio.getAlbum_id());
                break;
            case 14:
                MixBean.Mix mod7 = (MixBean.Mix) t;
                //                imgToLoader(mod7.getPic(), holder.circleImage);
                //                imageLoader.displayImage(mod7.getPic(), holder.circleImage);
                DisplaySingle.getInstance().show(mod7.getPic(), holder.circleImage);
                holder.tv.setText(mod7.getTitle());
                holder.author.setText(mod7.getDesc());
                holder.author.setVisibility(View.VISIBLE);
                holder.imageButton.setBackgroundResource(R.mipmap.ic_songlist_detail_nor);
//                holder.listId.setText(mod7.getType_id());

                break;
            case 16:
                SongBean.DiyInfoBean contentBean = (SongBean.DiyInfoBean) t;

                //                imgToLoader(contentBean.getPic_300(),holder.ivSong);
                //                imageLoader.displayImage(contentBean.getList_pic(), holder.ivSong);
                DisplaySingle.getInstance().show(contentBean.getList_pic(), holder.ivSong);
                holder.numSong.setText(contentBean.getListen_num() + "");
                holder.titleSong.setText(contentBean.getTitle());
                holder.authorSong.setText("by " + contentBean.getUsername());
                break;

            case 17:
                MVBean.ResultBean.MvListBean mvListBean = (MVBean.ResultBean.MvListBean) t;
                DisplaySingle.getInstance().show(mvListBean.getThumbnail2(), holder.ivMV);
                holder.tvArtist.setText(mvListBean.getArtist());
                holder.tvTitle.setText(mvListBean.getTitle());
                //                imageLoader.displayImage(mvListBean.getThumbnail2(),holder.ivMV);

        }
    }

    //使用viewholder
    @Override
    protected BTestAdapter.ViewHolder buildHolder(View view, T t, int i) {
        ViewHolder viewHolder = new ViewHolder();
        if (pos == 17) {
            viewHolder.tvTitle = bindView(R.id.tv_mv_list_title, view);
            viewHolder.ivMV = bindView(R.id.iv_mv_list, view);
            viewHolder.tvArtist = bindView(R.id.tv_mv_list_artist, view);
        }

        if (pos == 16) {

            viewHolder.ivSong = bindView(R.id.iv_item_song_list, view);//(ImageView) view.findViewById(R.id.iv_item_song_list);
            viewHolder.numSong = bindView(R.id.tv_item_song_list_num, view);//(TextView) view.findViewById(R.id.tv_item_song_list_num);
            viewHolder.authorSong = bindView(R.id.tv_item_song_list_author, view);//(TextView) view.findViewById(R.id.tv_item_song_list_author);
            viewHolder.titleSong = bindView(R.id.tv_item_song_list_title, view);//(TextView) view.findViewById(R.id.tv_item_song_list_title);
            viewHolder.iBtnSong = bindView(R.id.ibtn_item_song_list, view);//(ImageButton) view.findViewById(R.id.ibtn_item_song_list);

        }

        if (pos == 8) {
            viewHolder.imageViewBg = bindView(R.id.iv_item_base_scene_blue, view);//(ImageView) view.findViewById(R.id.iv_item_base_scene);
            viewHolder.imageViewSrc = bindView(R.id.iv_item_base_scene_icon_blue, view);//(ImageView) view.findViewById(R.id.iv_item_base_scene_icon);
            viewHolder.tvBg = bindView(R.id.tv_item_base_scene_blue, view);//(TextView) view.findViewById(R.id.tv_item_base_scene);
        }

        if (pos == 10 || pos == 14) {

            viewHolder.circleImage = bindView(R.id.item_base_resong_div_img, view);//(ImageView) view.findViewById(R.id.item_base_resong_div_img);
            viewHolder.tv = bindView(R.id.tv_item_base_resong_name, view);//(TextView) view.findViewById(R.id.tv_item_base_resong_name);
            viewHolder.author = bindView(R.id.tv_item_base_resong_author, view);//(TextView) view.findViewById(R.id.tv_item_base_resong_author);
            viewHolder.imageButton = bindView(R.id.ibtn_item_base_resong, view);//(ImageButton) view.findViewById(R.id.ibtn_item_base_resong);

        } else {

            viewHolder.iv = bindView(R.id.iv_item_base, view);
            viewHolder.tv = bindView(R.id.tv_item_base, view);
            viewHolder.author = bindView(R.id.tv_item_base_author, view);
            viewHolder.ll = bindView(R.id.ll_item_base, view);
            viewHolder.listId = bindView(R.id.tv_item_base_listId,view);
        }
        return viewHolder;
    }

    @Override
    protected View buildConvertView(LayoutInflater layoutInflater, T t, int i) {



        if (pos == 17) {
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
        TextView tv, author, tvBg,listId;
        LinearLayout ll;

        ImageView circleImage, imageViewBg, imageViewSrc;
        ImageButton imageButton;

        //16
        ImageView ivSong;
        TextView numSong, authorSong, titleSong;
        ImageButton iBtnSong;

        //17
        ImageView ivMV;
        TextView tvTitle, tvArtist;

    }

    protected <V extends View> V bindView(int id, View view) {
        return (V) view.findViewById(id);
    }

}
