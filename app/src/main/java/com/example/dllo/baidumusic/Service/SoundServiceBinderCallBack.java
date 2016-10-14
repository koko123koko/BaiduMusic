package com.example.dllo.baidumusic.Service;

import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.SongInfoBean;

import java.util.List;

/**
 * Created by dllo on 16/10/13.
 */
public interface SoundServiceBinderCallBack {

    void setImagePlay();
    void setImagePaused();
    void setCurrentTime(String time);
    void setTotalTime(String time);
    void setMusicTitle(String title);
    void setMusicArtist(String artist);
    void setMusicPic(String urlPic);
    void setBean(SongInfoBean songInfoBean);
    void setArrayListBean(List<SongInfoBean> songInfoBeanList);
    void setCurrentPosition(int position);


}
