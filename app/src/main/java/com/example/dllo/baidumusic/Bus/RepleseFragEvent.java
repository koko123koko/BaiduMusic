package com.example.dllo.baidumusic.Bus;

import android.support.v4.app.Fragment;

/**
 * Created by dllo on 16/9/29.
 */
public class RepleseFragEvent {

     private Fragment fragment;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

//    public  void repleseFrag(Fragment newFragment){
//        FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.main_ll,newFragment);
//        transaction.commit();
//    }
}
