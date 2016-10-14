package com.example.dllo.baidumusic.Fragment.LibsFragment.Song;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dllo.baidumusic.Adapter.BAdapter.BTestAdapter;
import com.example.dllo.baidumusic.Bean.SongBean;
import com.example.dllo.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{

    Context context;

    private ArrayList<SongBean.DiyInfoBean> contentBeen;

    ArrayList<SongBean> songBeanArrayList;
    private final DisplayImageOptions options;

    public void setSongBeanArrayList(ArrayList<SongBean> songBeanArrayList) {
        this.songBeanArrayList = songBeanArrayList;
    }

    public SongAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    public void setContentBeen(ArrayList<SongBean.DiyInfoBean> contentBeen) {
        this.contentBeen = contentBeen;
        notifyDataSetChanged();
    }

    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_frag_song,parent,false);
        SongViewHolder songViewHolder = new SongViewHolder(view);

        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(SongAdapter.SongViewHolder holder, int position) {

        BTestAdapter songGVAdapter = new BTestAdapter(context, contentBeen);
        songGVAdapter.setPos(16);
        holder.gv.setAdapter(songGVAdapter);

    }

    @Override
    public int getItemCount() {
        return songBeanArrayList == null ? 0 : songBeanArrayList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {

        private final TextView hot;
        private final TextView newTV;
        private final TextView songTV;
        private final GridView gv;
        private final ImageButton ibtn;

        public SongViewHolder(View itemView) {
            super(itemView);
            hot = (TextView)itemView.findViewById(R.id.tv_item_rv_frag_song_hot);
            newTV = (TextView) itemView.findViewById(R.id.tv_item_rv_frag_song_new);
            songTV = (TextView) itemView.findViewById(R.id.tv_song_frag);
            gv = (GridView) itemView.findViewById(R.id.gv_item_rv_song);
            ibtn = (ImageButton) itemView.findViewById(R.id.ibtn_song_frag);

        }
    }
}
