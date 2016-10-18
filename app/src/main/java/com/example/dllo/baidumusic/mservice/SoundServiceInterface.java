package com.example.dllo.baidumusic.mservice;

import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;

import java.util.List;

/**
 * Created by dllo on 16/10/13.
 */
public interface SoundServiceInterface {


    void addMusicToQueue(SongInfoBean bean);
    void addMusicToQueue(List<SongInfoBean> songs);
    void removeMusicFromQueue(SongInfoBean bean);
    void removeMusicFromQueue(List<SongInfoBean> songs);
    void skipToPoint(int point);
    void play(int position);
    void play();
    void pause();




}
