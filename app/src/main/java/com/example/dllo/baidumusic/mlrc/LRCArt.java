package com.example.dllo.baidumusic.mlrc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.baidumusic.basefrag.BaseActivity;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/14.
 */
public class LRCArt extends BaseActivity {

    private ImageButton download;
    private ImageButton comment;
    private TextView end;
    private ImageButton like;
    private ImageButton moreaction;
    private ImageButton ret;
    private SeekBar seekBar;
    private ImageButton share;
    private TextView start;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.activity_lrc;
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        SongInfoBean currentSong =intent.getParcelableExtra("songInfoBean");



        LRCVPAdapter lrcvpAdapter = new LRCVPAdapter(getSupportFragmentManager());

        songLrcFrag lrcFrag = new songLrcFrag();
        lrcFrag.setCurrentSong(currentSong);
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new songDetailFrag());
        fragments.add(new songCenterFrag());
        fragments.add(lrcFrag);


        lrcvpAdapter.setFragmentArrayList(fragments);

        vp.setAdapter(lrcvpAdapter);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("LRCArt", "哈哈");
                finish();
            }
        });

    }

    @Override
    protected void initView() {

        download = bindView(R.id.download_btn_lrc_aty);
        comment = bindView(R.id.comment_btn_lrc_aty);
        end = bindView(R.id.end_tv_lrc_aty);
        like = bindView(R.id.like_btn_lrc_aty);
        moreaction = bindView(R.id.moreaction_btn_lrc_aty);
        ret = bindView(R.id.return_btn_lrc_aty);
        seekBar = bindView(R.id.seekbar_lrc_aty);
        share = bindView(R.id.share_btn_lrc_aty);
        start = bindView(R.id.start_time_tv_lrc_aty);
        vp = bindView(R.id.vp_lrc_aty);


    }
}
