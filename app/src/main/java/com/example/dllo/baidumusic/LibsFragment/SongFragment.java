package com.example.dllo.baidumusic.LibsFragment;

import android.support.v7.widget.RecyclerView;

import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/19.
 */
public class SongFragment extends BaseFragment {

    private RecyclerView rv;

    @Override
    protected void initData() {

        SongAdapter songAdapter = new SongAdapter();
        rv.setAdapter(songAdapter);
    }

    @Override
    protected void initVIew() {
        rv = bindView(R.id.rv_frag_song);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_song;
    }
}
