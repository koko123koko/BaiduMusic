package com.example.dllo.baidumusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.example.dllo.baidumusic.Adapter.MyVPAdapter;
import com.example.dllo.baidumusic.Base.BaseActivity;
import com.example.dllo.baidumusic.Fragment.MSGFragment;
import com.example.dllo.baidumusic.Fragment.MusicFragment;
import com.example.dllo.baidumusic.Fragment.LiveFragment;
import com.example.dllo.baidumusic.Fragment.MyFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private TabLayout tl;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

        MyVPAdapter myVPAdapter = new MyVPAdapter(getSupportFragmentManager());
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyFragment());
        fragments.add(new MusicFragment());
        fragments.add(new MSGFragment());
        fragments.add(new LiveFragment());

        ArrayList<String> arr = new ArrayList<>();
        arr.add("我的");
        arr.add("音乐");
        arr.add("动态");
        arr.add("直播");

        myVPAdapter.setFragments(fragments);
        myVPAdapter.setArrayList(arr);
        vp.setAdapter(myVPAdapter);
        tl.setupWithViewPager(vp);
    }

    @Override
    protected void initView() {

        tl = bindView(R.id.main_tab);
        vp = bindView(R.id.main_vp);



    }
}
