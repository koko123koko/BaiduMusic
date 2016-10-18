package com.example.dllo.baidumusic.mservice;

import android.content.Context;
import android.os.Binder;

import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;

import java.util.List;

/**
 * Created by dllo on 16/10/14.
 */
public class SoundServiceBinder extends Binder {

    private SoundService soundService;
    private SoundServiceBinderCallBack soundServiceBinderCallBack;
    private Context context;

    public SoundServiceBinder(SoundService soundService, Context context) {
        this.soundService = soundService;
        this.context = context;
    }

    public SoundService getSoundService(SoundServiceBinderCallBack soundServiceBinderCallBack) {
        this.soundServiceBinderCallBack = soundServiceBinderCallBack;
        return soundService;
    }

   public void play(){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setImagePlay();
        }
    }
    public void paused(){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setImagePaused();
        }
    }
    public void setCurrentTime(String time){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setCurrentTime(time);
        }
    }
    public void setTotalTime(String time){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setTotalTime(time);
        }
    }
    public void setMusicTitle(String title){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setMusicTitle(title);
        }
    }
    public void setMusicArtist(String artist){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setMusicArtist(artist);
        }
    }
    public void setMusicPic(String urlPic){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setMusicPic(urlPic);
        }
    }

    public void SetBean(SongInfoBean songInfoBean){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setBean(songInfoBean);
        }
    }
    public void setArrayListBean(List<SongInfoBean> songInfoBeanList){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setArrayListBean(songInfoBeanList);
        }
    }
    void setCurrentPosition(int position){
        if(context != null && soundServiceBinderCallBack != null){
            soundServiceBinderCallBack.setCurrentPosition(position);
        }
    }

}
