package com.example.dllo.baidumusic.LRC;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.Bus.InfoBeanEvent;
import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.SongInfoBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.Service.SoundService;
import com.example.dllo.baidumusic.Service.SoundServiceBinder;
import com.example.dllo.baidumusic.Service.SoundServiceBinderCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/15.
 */
public class LRCFrag extends BaseFragment {

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

    private ServiceConnection mServiceConnection;
    private SoundServiceBinder mSoundServiceBinder;
    private SoundService mSoundService;

    private boolean mBound = false;
    private List<SongInfoBean> songs;
    private int currentPosition;
    private SongInfoBean currentSong;
    private ImageButton next;
    private ImageButton order;
    private ImageButton play;
    private ImageButton prev;
    private ImageButton list;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setSongInfoBeanList(InfoBeanEvent event) {
        if (event.getSongInfoBeanList() != null) {
            Log.d("LRCFrag", "进来了");
            songs = event.getSongInfoBeanList();
            currentPosition = event.getPosition();
            currentSong = songs.get(currentPosition);
        }

    }
    @Override
    protected void initData() {


        bindMusicService();
        LRCVPAdapter lrcvpAdapter = new LRCVPAdapter(getChildFragmentManager());

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

                Log.d("LRCFrag", "点击");
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    protected void initVIew() {
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

        next =  bindView(R.id.next_btn_lrc_aty);
        order =  bindView(R.id.order_btn_lrc_aty);
        play =  bindView(R.id.play_btn_lrc_aty);
        prev =  bindView(R.id.previous_btn_lrc_aty);
        list =  bindView(R.id.list_btn_lrc_aty);

        EventBus.getDefault().register(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_lrc;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void bindMusicService(){
        defineServiceConnection(); // we define our service connection mConnection
        Intent intent = new Intent(getActivity(), SoundService.class);
        //        intent.putExtra(MusicPlayerService.ACTIVITY_INDENTIFY, MusicPlayerService.FULLSCREEN_PLAYER_ACTIVITY);
        getActivity().bindService(intent, mServiceConnection
                , Context.BIND_AUTO_CREATE);
    }
    private void defineServiceConnection() {

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, final IBinder service) {
                mSoundServiceBinder = (SoundServiceBinder) service;
                mSoundService = mSoundServiceBinder.getSoundService(new SoundServiceBinderCallBack() {
                    @Override
                    public void setImagePlay() {

                        if (play != null) {
                            play.setBackgroundResource(R.mipmap.bt_minibar_pause_normal);
                        }

                    }

                    @Override
                    public void setImagePaused() {
                        if (play != null) {
                            play.setBackgroundResource(R.mipmap.bt_minibar_play_normal);
                        }
                    }

                    @Override
                    public void setCurrentTime(String time) {

                    }

                    @Override
                    public void setTotalTime(String time) {

                    }

                    @Override
                    public void setMusicTitle(String title) {
//                        if (songName != null) {
//                            songName.setText(title);
//                        }
                    }

                    @Override
                    public void setMusicArtist(String artist) {
//                        if (author != null) {
//                            author.setText(artist);
//                        }
                    }

                    @Override
                    public void setMusicPic(String urlPic) {
//                        if (pic != null) {
//                            DisplaySingle.getInstance().show(urlPic, pic);
//                        }
                    }

                    @Override
                    public void setBean(SongInfoBean songInfoBean) {
                        //                        if (songInfoBean != null){
                        currentSong = songInfoBean;
                        //                        }
                    }

                    @Override
                    public void setArrayListBean(List<SongInfoBean> songInfoBeanList) {
                        //                        if (songs != null){
                        songs = songInfoBeanList;
                        //                        }
                    }

                    @Override
                    public void setCurrentPosition(int position) {
                        currentPosition = position;
                    }

                });
                mBound = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;

            }
        };

    }
}
