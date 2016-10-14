package com.example.dllo.baidumusic.Service;

import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.SongInfoBean;

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
