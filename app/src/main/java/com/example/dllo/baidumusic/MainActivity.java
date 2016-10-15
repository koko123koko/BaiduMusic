package com.example.dllo.baidumusic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.Adapter.MyVPAdapter;
import com.example.dllo.baidumusic.Base.BaseActivity;
import com.example.dllo.baidumusic.Bus.InfoBeanEvent;
import com.example.dllo.baidumusic.Bus.RepleseFragEvent;
import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.PopupAdapter;
import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.SongInfoBean;
import com.example.dllo.baidumusic.Fragment.LiveFragment;
import com.example.dllo.baidumusic.Fragment.MSGFragment;
import com.example.dllo.baidumusic.Fragment.MusicFragment;
import com.example.dllo.baidumusic.Fragment.MyFragment;
import com.example.dllo.baidumusic.LRC.LRCFrag;
import com.example.dllo.baidumusic.Service.SoundService;
import com.example.dllo.baidumusic.Service.SoundServiceBinder;
import com.example.dllo.baidumusic.Service.SoundServiceBinderCallBack;
import com.example.dllo.baidumusic.VolleyRequest.DisplaySingle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TabLayout tl;
    private ViewPager vp;
    private ImageButton btnNext;
    private ImageButton btnPlay;
    private ImageButton list;
    private TextView author;
    private TextView songName;
    private ImageView pic;

    private SongInfoBean currentSong;
    private int currentPosition;

    private ServiceConnection mServiceConnection;
    private SoundServiceBinder mSoundServiceBinder;
    private SoundService mSoundService;
    private List<SongInfoBean> songs;

    private boolean mBound = false;

    private int mState;
    private RelativeLayout ll;
    private ListView lv;
    private Button btnClose;

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

        btnNext.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        list.setOnClickListener(this);
        bindMusicService();

        if (currentSong == null) {
            btnNext.setClickable(false);
            btnPlay.setClickable(false);
            list.setClickable(false);
        } else {
            author.setText(currentSong.getSonginfo().getAuthor());
            songName.setText(currentSong.getSonginfo().getTitle());
            DisplaySingle.getInstance().show(currentSong.getBitrate().getFile_link(), pic);
            btnNext.setClickable(true);
            btnPlay.setClickable(true);
            list.setClickable(true);

        }

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LRCArt.class);
//                intent.putExtra("songInfoBean", currentSong);
//                startActivity(intent);
                LRCFrag lrcFrag = new LRCFrag();
                InfoBeanEvent event = new InfoBeanEvent();
                event.setSongInfoBeanList(songs);
                EventBus.getDefault().post(event);


                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.popshow_anim, R.anim.pophidden_anim);
                transaction.replace(R.id.fl_main_aty, lrcFrag);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

    }

    @Override
    protected void initView() {
        tl = bindView(R.id.main_tab);
        vp = bindView(R.id.main_vp);

        btnNext = bindView(R.id.main_ibtn_next);
        btnPlay = bindView(R.id.main_ibtn_play);
        list = bindView(R.id.main_ibtn_playinglist);
        author = bindView(R.id.main_tv_author_name);
        songName = bindView(R.id.main_tv_name);
        pic = bindView(R.id.main_iv);

        ll = (RelativeLayout) findViewById(R.id.main_ll);

        ShareSDK.initSDK(this, "d6a1118259c4b383e93ca11f");

        EventBus.getDefault().register(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //        if (mSoundService != null)
        //            stopService(new Intent(MainActivity.this, SoundService.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setFragment(RepleseFragEvent event) {
        if (event.getFragment() != null) {
            repleseFrag(event.getFragment());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setSongInfoBeanList(InfoBeanEvent event) {
        if (event.getSongInfoBeanList() != null) {
            songs = event.getSongInfoBeanList();
            currentPosition = event.getPosition();
            currentSong = songs.get(currentPosition);
            //            author.setText(currentSong.getSonginfo().getAuthor());
            //            songName.setText(currentSong.getSonginfo().getTitle());
            //            DisplaySingle.getInstance().show(currentSong.getSonginfo().getPic_small(), pic);
            btnNext.setClickable(true);
            btnPlay.setClickable(true);
            list.setClickable(true);
        }

    }




    public void repleseFrag(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.setCustomAnimations(R.anim.popshow_anim, R.anim.pophidden_anim);
        transaction.replace(R.id.item_main_fl, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //        if (mServiceConnection != null && mSoundService != null)
        //            unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.main_ibtn_next:
                Log.d("MainActivity", "main_ibtn_next");
                if (mSoundService == null) {
                    // 将 currentPosition 增加1，如果到 list 尾就返回第一个
                    //                    bindMusicService();
                    //                    if (currentPosition == (songs.size() - 1)) {
                    //                        currentPosition = 0;
                    //                    }
                    //                    else {
                    //                        currentPosition++;
                    //                    }
                    return;
                }
                if (mBound) {
                    //                    btnNext.setImageResource(R.drawable.playbar_btn_play);
                    mSoundService.playNext();
                }

                break;
            case R.id.main_ibtn_play:
                Log.d("MainActivity", "main_ibtn_play");
                if (mSoundService == null) {
                    bindMusicService();
                    return;
                }
                if (mBound) {
                    mState = mSoundService.changeState();
                    switch (mState) {
                        case SoundService.PAUSED:
                            btnPlay.setBackgroundResource(R.mipmap.bt_minibar_play_normal);
                            break;
                        case SoundService.PLAYING:
                            btnPlay.setBackgroundResource(R.mipmap.bt_minibar_pause_normal);
                            break;
                    }
                }

                break;
            case R.id.main_ibtn_playinglist:
                showPopupWindow();
                break;
        }
    }

    public void bindMusicService() {
        defineServiceConnection();
        Intent intent = new Intent(MainActivity.this, SoundService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        //        startService(intent);
    }

    private void defineServiceConnection() {

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, final IBinder service) {
                mSoundServiceBinder = (SoundServiceBinder) service;
                mSoundService = mSoundServiceBinder.getSoundService(new SoundServiceBinderCallBack() {
                    @Override
                    public void setImagePlay() {

                        if (btnPlay != null) {
                            btnPlay.setBackgroundResource(R.mipmap.bt_minibar_pause_normal);
                        }

                    }

                    @Override
                    public void setImagePaused() {
                        if (btnPlay != null) {
                            btnPlay.setBackgroundResource(R.mipmap.bt_minibar_play_normal);
                        }
                    }

                    @Override
                    public void setCurrentTime(String time) {

                    }

                    @Override
                    public void setTotalTime(String time) {

                    }

                    @Override
                    public void setMusicTitle(String title) {
                        if (songName != null) {
                            songName.setText(title);
                        }
                    }

                    @Override
                    public void setMusicArtist(String artist) {
                        if (author != null) {
                            author.setText(artist);
                        }
                    }

                    @Override
                    public void setMusicPic(String urlPic) {
                        if (pic != null) {
                            DisplaySingle.getInstance().show(urlPic, pic);
                        }
                    }

                    @Override
                    public void setBean(SongInfoBean songInfoBean) {
                        //                        if (songInfoBean != null){
                        currentSong = songInfoBean;
                        //                        }
                    }

                    @Override
                    public void setArrayListBean(List<SongInfoBean> songInfoBeanList) {
                        //                        if (songs != null){
                        songs = songInfoBeanList;
                        //                        }
                    }

                    @Override
                    public void setCurrentPosition(int position) {
                        currentPosition = position;
                    }

                });
                mBound = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;

            }
        };

    }

    private void showPopupWindow() {

        View view = LayoutInflater.from(this).inflate(R.layout.popup_item, null);

        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                1000, true);


        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(MainActivity.this.findViewById(R.id.main_ibtn_playinglist),
                Gravity.BOTTOM, 0, 0);

        lv = (ListView) view.findViewById(R.id.lv_popup_item);

        btnClose = (Button) view.findViewById(R.id.btn_popup_item_close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                onBackPressed();
                window.dismiss();
            }
        });

        PopupAdapter popupAdapter = new PopupAdapter(this);

        popupAdapter.setSongInfoBeanArrayList((ArrayList<SongInfoBean>) songs);
        lv.setAdapter(popupAdapter);

        View footView = LayoutInflater.from(this).inflate(R.layout.item_list_foot, null);
        footView.setMinimumHeight(100);
        lv.addFooterView(footView);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("SongListAdapter", "消失");
            }
        });

    }
}
