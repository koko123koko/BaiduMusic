package com.example.dllo.baidumusic.mfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.baidumusic.adapterbase.FragVPAdapter;
import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.mv.MVFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.km.KMFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.rank.RankFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.recommed.RecommendFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.SongFragment;
import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MusicFragment extends BaseFragment {

    private TabLayout tl;
    private ViewPager vp;

    @Override
    protected void initData() {
        FragVPAdapter fragVPAdapter = new FragVPAdapter(getChildFragmentManager());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("推荐");
        arrayList.add("歌单");
        arrayList.add("榜单");
        arrayList.add("视频");
        arrayList.add("K歌");

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new SongFragment());
        fragments.add(new RankFragment());
        fragments.add(new MVFragment());
        fragments.add(new KMFragment());



        fragVPAdapter.setArrayList(arrayList);
        fragVPAdapter.setFragments(fragments);
        vp.setAdapter(fragVPAdapter);
        tl.setupWithViewPager(vp);
    }

    @Override
    protected void initVIew() {
        vp = bindView(R.id.frag_music_vp);
        tl = bindView(R.id.frag_music_tl);
    }

    @Override
    protected int setLayout() {
        return R.layout.frag_music;
    }


}
