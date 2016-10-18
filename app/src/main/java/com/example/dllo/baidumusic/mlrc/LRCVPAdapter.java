package com.example.dllo.baidumusic.mlrc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/15.
 */
public class LRCVPAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragmentArrayList;


    public void setFragmentArrayList(ArrayList<Fragment> fragmentArrayList) {
        this.fragmentArrayList = fragmentArrayList;
    }

    public LRCVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList == null ? 0 : fragmentArrayList.size();
    }
}
