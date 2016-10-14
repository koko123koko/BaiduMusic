package com.example.dllo.baidumusic.Bus;

import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.SongInfoBean;

import java.util.List;

/**
 * Created by dllo on 16/10/14.
 */
public class InfoBeanEvent {
    private SongInfoBean songInfoBean;
    private List<SongInfoBean> songInfoBeanList;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<SongInfoBean> getSongInfoBeanList() {
        return songInfoBeanList;
    }

    public void setSongInfoBeanList(List<SongInfoBean> songInfoBeanList) {
        this.songInfoBeanList = songInfoBeanList;
    }

    public SongInfoBean getSongInfoBean() {
        return songInfoBean;
    }

    public void setSongInfoBean(SongInfoBean songInfoBean) {
        this.songInfoBean = songInfoBean;
    }
}
