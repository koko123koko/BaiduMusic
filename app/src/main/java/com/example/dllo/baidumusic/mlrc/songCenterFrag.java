package com.example.dllo.baidumusic.mlrc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.example.dllo.baidumusic.mservice.SoundService;
import com.example.dllo.baidumusic.mservice.SoundServiceBinder;
import com.example.dllo.baidumusic.mservice.SoundServiceBinderCallBack;
import com.example.dllo.baidumusic.mutil.CommonUtils;
import com.example.dllo.baidumusic.mvolley.DisplaySingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/15.
 */
public class songCenterFrag extends BaseFragment {

    private ImageView imageView;
    private ImageView mv;
    private TextView song;
    private TextView songer;
    private Intent intent;
    private ServiceConnection mServiceConnection;
    private SoundServiceBinder mSoundServiceBinder;
    private SoundService mSoundService;
    private SongInfoBean currentSong;
    private List<SongInfoBean> songs;
    private int currentPosition;
    private boolean mBound;
    private int state;

    @Override
    protected void initData() {
        bindMusicService();
    }
    @Override
    protected void initVIew() {

        imageView = bindView(R.id.iv_lrc_center);
        mv = bindView(R.id.mv_lrc_center);
        song = bindView(R.id.song_name_lrc_center);
        songer = bindView(R.id.songer_lrc_center);
    }

    @Override
    protected int setLayout() {
        return R.layout.lrc_center_item;
    }

    public void bindMusicService() {
        defineServiceConnection(); // we define our service connection mConnection
        intent = new Intent(getActivity(), SoundService.class);
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


        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, final IBinder service) {
                mSoundServiceBinder = (SoundServiceBinder) service;
                mSoundService = mSoundServiceBinder.getSoundService(new SoundServiceBinderCallBack() {
                    @Override
                    public void setImagePlay() {
                    }

                    @Override
                    public void setImagePaused() {
                    }

                    @Override
                    public void setCurrentTime(String time) {
                    }

                    @Override
                    public void setTotalTime(String time) {
                    }

                    @Override
                    public void setMusicTitle(String title) {
                        if (song != null){
                            song.setText(title);
                        }
                    }

                    @Override
                    public void setMusicArtist(String artist) {
                        if (songer != null){
                            songer.setText(artist);
                        }
                    }

                    @Override
                    public void setMusicPic(String urlPic) {
                        if (imageView != null) {
                            DisplaySingle.getInstance().show(urlPic, imageView);
                        }
                    }

                    @Override
                    public void setBean(SongInfoBean songInfoBean) {
                        currentSong = songInfoBean;
                    }

                    @Override
                    public void setArrayListBean(List<SongInfoBean> songInfoBeanList) {
                        songs = songInfoBeanList;
                    }

                    @Override
                    public void setCurrentPosition(int position) {
                        currentPosition = position;
                    }

                    @Override
                    public void setLRC(String lrc) {
                    }

                });
                mBound = true;
                state = mSoundService.getState();
                //                seekBar.setMax(Integer.parseInt());
                //                Log.d("LRCFrag", "currentSong.getBitrate().getFile_duration():" + currentSong.getBitrate().getFile_duration()+"");

                //                currentSong = mSoundService.getSongInfoBeanList().get(currentPosition);
                //                end.setText(CommonUtils.timeFormatMs2Str(currentSong.getBitrate().getFile_duration()));
                //
                //
                //                //                currentSong.getBitrate().getFile_duration();
                //                seekBar.setMax(currentSong.getBitrate().getFile_duration());
                //                seekBar.setProgress(mSoundService.getPlayingPosition());
                //
                //
                //                mSoundService.startSeekBarTracker(currentSong.getBitrate().getFile_duration());


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;

            }
        };

    }
}
