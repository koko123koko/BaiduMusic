package com.example.dllo.baidumusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.example.dllo.baidumusic.Adapter.MyVPAdapter;
import com.example.dllo.baidumusic.Base.BaseActivity;
import com.example.dllo.baidumusic.Bus.RepleseFragEvent;
import com.example.dllo.baidumusic.Fragment.LiveFragment;
import com.example.dllo.baidumusic.Fragment.MSGFragment;
import com.example.dllo.baidumusic.Fragment.MusicFragment;
import com.example.dllo.baidumusic.Fragment.MyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private TabLayout tl;
    private ViewPager vp;

    //    private static FragmentManager fragmentManager;

    @Override
    protected int setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
//        fragmentManager = getSupportFragmentManager();
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

        EventBus.getDefault().register(this);




    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setFragment(RepleseFragEvent event){

        repleseFrag(event.getFragment());



    }




    public  void repleseFrag(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.item_main_fl,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
