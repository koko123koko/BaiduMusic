package com.example.dllo.baidumusic.LibsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/20.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{

    Context context;
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_frag_song,parent,false);
        SongViewHolder songViewHolder = new SongViewHolder(view);

        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(SongAdapter.SongViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        public SongViewHolder(View itemView) {
            super(itemView);
        }
    }
}
