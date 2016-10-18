package com.example.dllo.baidumusic.mlrc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.mbus.InfoBeanEvent;
import com.example.dllo.baidumusic.mutil.CommonUtils;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mservice.SoundService;
import com.example.dllo.baidumusic.mservice.SoundServiceBinder;
import com.example.dllo.baidumusic.mservice.SoundServiceBinderCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/15.
 */
public class LRCFrag extends BaseFragment implements View.OnClickListener {

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


    private List<SongInfoBean> songs;
    private int currentPosition;
    private SongInfoBean currentSong;
    private ImageButton next;
    private ImageButton order;
    private ImageButton play;
    private ImageButton prev;
    private ImageButton list;
    private Intent intent;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;

    boolean mBound = false;
    int playPosition;

    int state;


    public LRCFrag() {
        //        bindMusicService();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setSongInfoBeanList(InfoBeanEvent event) {
        if (event.getSongInfoBeanList() != null) {
            Log.d("LRCFrag", "进来了");
            bindMusicService();
            songs = event.getSongInfoBeanList();
            currentPosition = event.getPosition();
            currentSong = songs.get(currentPosition);


        }

    }

    public void setSongs(List<SongInfoBean> songs) {
        this.songs = songs;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    @Override
    protected void initData() {

        bindMusicService();

        //        currentSong = songs.get(currentPosition);

        LRCVPAdapter lrcvpAdapter = new LRCVPAdapter(getFragmentManager());

        songLrcFrag lrcFrag = new songLrcFrag();
        lrcFrag.setCurrentSong(songs.get(currentPosition));
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


        initOnSeekBarChangeListener();
        initButtonOnClickListener();
        seekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

    }

    private void initButtonOnClickListener() {
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        order.setOnClickListener(this);
        prev.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    private void initOnSeekBarChangeListener() {
        mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mSoundService == null || !fromUser) {
                    return;
                }
                seekBar.setProgress(progress);
                mSoundService.skipToPoint(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mSoundService == null) {
                    return;
                }
                if (mBound) {
                    state = mSoundService.changeState();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mSoundService == null) {
                    return;
                }
                if (mBound) {
                    state = mSoundService.changeState();
                }
            }
        };

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

        next = bindView(R.id.next_btn_lrc_aty);
        order = bindView(R.id.order_btn_lrc_aty);
        play = bindView(R.id.play_btn_lrc_aty);
        prev = bindView(R.id.previous_btn_lrc_aty);
        list = bindView(R.id.list_btn_lrc_aty);

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
        if (mSoundService != null && intent != null)
            getActivity().stopService(intent);
    }

    public void bindMusicService() {
        defineServiceConnection(); // we define our service connection mConnection
        Intent intent = new Intent(getActivity(), SoundService.class);
        //        intent.putExtra(MusicPlayerService.ACTIVITY_INDENTIFY, MusicPlayerService.FULLSCREEN_PLAYER_ACTIVITY);
        getActivity().bindService(intent, mServiceConnection
                , Context.BIND_AUTO_CREATE);
    }

    private void defineServiceConnection() {

        if (!CommonUtils.isServiceWorked(SoundService.SERVICE_NAME, getActivity())) {
            intent = new Intent(getActivity(), SoundService.class);
            intent.putExtra("playing", true);
            intent.putExtra("position", currentPosition);
            intent.putParcelableArrayListExtra("songInfo", (ArrayList<? extends Parcelable>) songs);
            getActivity().startService(intent);

        }
        //        if (!isServiceRunning()) {
        //            intent = new Intent(getActivity(), SoundService.class);
        //            intent.putExtra("playing", true);
        //            intent.putExtra("position", currentPosition);
        //            intent.putParcelableArrayListExtra("songInfo", (ArrayList<? extends Parcelable>) songs);
        //            getActivity().startService(intent);
        //            //            getActivity().startActivity(new Intent(getActivity(),SoundService.class));
        //        }
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, final IBinder service) {
                mSoundServiceBinder = (SoundServiceBinder) service;
                mSoundService = mSoundServiceBinder.getSoundService(new SoundServiceBinderCallBack() {
                    @Override
                    public void setImagePlay() {

                        if (play != null) {
                            play.setBackgroundResource(R.mipmap.bt_playpage_button_pause_normal_new);
                        }

                    }

                    @Override
                    public void setImagePaused() {
                        if (play != null) {
                            play.setBackgroundResource(R.mipmap.bt_playpage_button_play_normal_new);
                        }
                    }

                    @Override
                    public void setCurrentTime(String time) {
                        if (start != null) {
                            start.setText(time);
                        }
                    }

                    @Override
                    public void setTotalTime(String time) {
                        if (end != null) {
                            Log.d("LRCFrag", time);
                            end.setText(time);
                        }
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
                state = mSoundService.getState();
                //                seekBar.setMax(Integer.parseInt());
                //                Log.d("LRCFrag", "currentSong.getBitrate().getFile_duration():" + currentSong.getBitrate().getFile_duration()+"");

                currentSong = mSoundService.getSongInfoBeanList().get(currentPosition);
                end.setText(CommonUtils.timeFormatMs2Str(currentSong.getBitrate().getFile_duration()));


                //                currentSong.getBitrate().getFile_duration();
                seekBar.setMax(currentSong.getBitrate().getFile_duration());
                seekBar.setProgress(mSoundService.getPlayingPosition());


                mSoundService.startSeekBarTracker(currentSong.getBitrate().getFile_duration());


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;

            }
        };

    }

    //    private boolean isServiceRunning() {
    //        ActivityManager manager = (ActivityManager) getActivity().getSystemService(mContext.ACTIVITY_SERVICE);
    //        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
    //            if (".Service.SoundService".equals(service.service.flattenToShortString())) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }

    @Override
    public void onPause() {
        super.onPause();
        if (mServiceConnection != null && mSoundService != null)
            getActivity().unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn_lrc_aty:
                Toast.makeText(mSoundService, "下一首", Toast.LENGTH_SHORT).show();
        }
    }
}
