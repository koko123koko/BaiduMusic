package com.example.dllo.baidumusic.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class FragVPAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments;
    private ArrayList<String> arrayList;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public FragVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
