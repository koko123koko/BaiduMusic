package com.example.dllo.baidumusic.adapterbase;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.dllo.baidumusic.adapterbase.baseadapter.BTestAdapter;
import com.example.dllo.baidumusic.musicbean.mrecommbean.DiyBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.EntryBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.FocusBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.MixBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.ModuleBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.RadioBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.RecommBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.RecsongBean;
import com.example.dllo.baidumusic.musicbean.mrecommbean.SceneBean;
import com.example.dllo.baidumusic.mbus.RepleseFragEvent;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.recommed.songer.SongClassify;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.recommed.songer.SongerFrag;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongListFrag;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mvolley.DisplaySingle;
import com.example.dllo.baidumusic.mvolley.URLVlaues;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MyRVAdapter extends RecyclerView.Adapter implements AdapterView.OnItemClickListener, View.OnClickListener {

    private RecommBean recommBean;
    //    private RecommBean.ResultBean.DiyBean diyBean;
    //    private RecommBean.ResultBean.Mix1Bean mix1Bean;
    //    private RecommBean.ResultBean.Mix5Bean mix5Bean;
    //    private RecommBean.ResultBean.Mix9Bean mix9Bean;
    //    private RecommBean.ResultBean.Mix22Bean mix22Bean;
    //    private RecommBean.ResultBean.Mod7Bean mod7Bean;
    //    private RecommBean.ResultBean.RadioBean radioBean;
    //    private RecommBean.ResultBean.RecsongBean recsongBean;
    //    private RecommBean.ResultBean.SceneBean sceneBean;
    //    private RecommBean.ResultBean.EntryBean entryBean;
    //    private RecommBean.ResultBean.FocusBean focusBean;
    //    private RecommBean.ResultBean.SceneBean.Scene.ActionBean actionBean;


    private String urlStr;
    private ArrayList<ModuleBean> moduleBeanArrayList;

    private ArrayList<String> focusArr;//轮播图

    //    private ArrayList<String> entryArr;//图标
    //    private ArrayList<RecommBean.ResultBean.Mix1Bean.Mix1> mix1ArrayList;
    //    private ArrayList<RecommBean.ResultBean.DiyBean.Diy> diyBeanArrayList;
    //    private List<RecommBean.ResultBean.Mix22Bean.Mix22> mix22List;
    //    private ArrayList<RecommBean.ResultBean.RecsongBean.Recsong> recsongArrayList;
    //    private ArrayList<RecommBean.ResultBean.Mix9Bean.Mix9> mix9ArrayList;
    //    private ArrayList<RecommBean.ResultBean.Mix5Bean.Mix5> mix5ArrayList;
    //    private ArrayList<RecommBean.ResultBean.RadioBean.Radio> radioArrayList;
    //    private ArrayList<RecommBean.ResultBean.Mod7Bean.Mod7> mod7ArrayList;
    //    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.ActionBean> actionBeanArrayList;
    //    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.EmotionBean> emotionBeanArrayList;
    //    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.OperationBean> operationBeanArrayList;
    //    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.OtherBean> otherBeanArrayList;

    private RepleseFragEvent event;
    private DiyBean diyBean;
    private EntryBean entryBean;
    private FocusBean focusBean;
    private RadioBean radioBean;
    private RecsongBean recsongBean;
    private SceneBean sceneBean;
    private MixBean mix1;
    private MixBean mix5;
    private MixBean mix9;
    private MixBean mix22;
    private MixBean mod7;
    private MixBean mod26;
    private MixBean adsmall;

    //    private ImageLoader imageLoader;
    //    private final DisplayImageOptions options;

    public void setModuleBeanArrayList(ArrayList<ModuleBean> moduleBeanArrayList) {
        this.moduleBeanArrayList = moduleBeanArrayList;
        Log.d("MyRVAdapter", "moduleBeanArrayList:" + moduleBeanArrayList);
        notifyDataSetChanged();
    }

    private Context context;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setTransaction(FragmentTransaction transaction) {
        this.transaction = transaction;
    }

    public MyRVAdapter(Context context) {
        this.context = context;
        event = new RepleseFragEvent();


    }

    public void setRecommBean(RecommBean recommBean) {
        this.recommBean = recommBean;
        Log.d("MyRVAdapter", "recommBean:" + recommBean);
        init();
        notifyDataSetChanged();
    }

    private void init() {

        diyBean = recommBean.getResult().getDiy();
        entryBean = recommBean.getResult().getEntry();
        focusBean = recommBean.getResult().getFocus();
        radioBean = recommBean.getResult().getRadio();
        recsongBean = recommBean.getResult().getRecsong();
        sceneBean = recommBean.getResult().getScene();
        mix1 = recommBean.getResult().getMix_1();
        mix5 = recommBean.getResult().getMix_5();
        mix9 = recommBean.getResult().getMix_9();
        mix22 = recommBean.getResult().getMix_22();
        mod7 = recommBean.getResult().getMod_7();
        mod26 = recommBean.getResult().getMod_26();
        adsmall = recommBean.getResult().getAd_small();

        //        this.entryBean = recommBean.getResult().getEntry();
        //        this.focusBean = recommBean.getResult().getFocus();
        //        this.diyBean = recommBean.getResult().getDiy();
        //        this.mix1Bean = recommBean.getResult().getMix_1();
        //        this.mix5Bean = recommBean.getResult().getMix_5();
        //        this.mix9Bean = recommBean.getResult().getMix_9();
        //        this.mix22Bean = recommBean.getResult().getMix_22();
        //        this.mod7Bean = recommBean.getResult().getMod_7();
        //        this.radioBean = recommBean.getResult().getRadio();
        //        this.recsongBean = recommBean.getResult().getRecsong();
        //        this.sceneBean = recommBean.getResult().getScene();

        //1
        //        entryArr = new ArrayList<>();
        //        for (int i = 0; i < entryBean.getResult().size(); i++) {
        //            entryArr.add(entryBean.getResult().get(i).getIcon());
        //        }
        //
        //2
        focusArr = new ArrayList<>();
        for (int i = 0; i < focusBean.getResult().size(); i++) {
            focusArr.add(focusBean.getResult().get(i).getRandpic());
        }
        //        //4
        //        diyBeanArrayList = new ArrayList<>();
        //        for (int i = 0; i < diyBean.getResult().size(); i++) {
        //            diyBeanArrayList.add(diyBean.getResult().get(i));
        //        }
        //        //6
        //        mix1ArrayList = new ArrayList<>();
        //        for (int i = 0; i < 6; i++) {
        //            mix1ArrayList.add(mix1Bean.getResult().get(i));
        //
        //        }
        //        //7
        //        mix22List = new ArrayList<>();
        //        for (int i = 0; i < mix22Bean.getResult().size(); i++) {
        //            mix22List.add(mix22Bean.getResult().get(i));
        //        }
        //
        //        //8
        //        actionBeanArrayList = new ArrayList<>();
        //        for (int i = 0; i < 4; i++) {
        //            actionBeanArrayList.add(sceneBean.getResult().getAction().get(i));
        //        }
        //        //        emotionBeanArrayList = new ArrayList<>();
        //        //        for (int i = 0; i < sceneBean.getResult().getAction().size(); i++) {
        //        //            emotionBeanArrayList.add(sceneBean.getResult().getEmotion().get(i));
        //        //        }
        //        //        operationBeanArrayList = new ArrayList<>();
        //        //        for (int i = 0; i < sceneBean.getResult().getAction().size(); i++) {
        //        //            operationBeanArrayList.add(sceneBean.getResult().getOperation().get(i));
        //        //        }
        //        //        otherBeanArrayList = new ArrayList<>();
        //        //        for (int i = 0; i < sceneBean.getResult().getAction().size(); i++) {
        //        //            otherBeanArrayList.add(sceneBean.getResult().getOther().get(i));
        //        //        }
        //
        //
        //        //10
        //        recsongArrayList = new ArrayList<>();
        //        for (int i = 0; i < 3; i++) {
        //            recsongArrayList.add(recsongBean.getResult().get(i));
        //        }
        //        //11
        //        mix9ArrayList = new ArrayList<>();
        //        for (int i = 0; i < mix9Bean.getResult().size(); i++) {
        //            mix9ArrayList.add(mix9Bean.getResult().get(i));
        //        }
        //        //12
        //        mix5ArrayList = new ArrayList<>();
        //        for (int i = 0; i < mix5Bean.getResult().size(); i++) {
        //            mix5ArrayList.add(mix5Bean.getResult().get(i));
        //        }
        //        //13
        //        radioArrayList = new ArrayList<>();
        //        for (int i = 0; i < radioBean.getResult().size(); i++) {
        //            radioArrayList.add(radioBean.getResult().get(i));
        //        }
        //        //14
        //        mod7ArrayList = new ArrayList<>();
        //        for (int i = 0; i < mod7Bean.getResult().size(); i++) {
        //            mod7ArrayList.add(mod7Bean.getResult().get(i));
        //        }


    }


    @Override
    public int getItemViewType(int position) {
        return moduleBeanArrayList.get(position).getPos();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            //            case 3:
            //            case 5:
            //            case 8:
            //            case 9:
            //
            //            case 15:
            case 1:
                View focusView = LayoutInflater.from(context).inflate(R.layout.item_adv_btn, parent, false);
                FocusViewHolder focusViewHolder = new FocusViewHolder(focusView);
                return focusViewHolder;
            case 2:
                View entryView = LayoutInflater.from(context).inflate(R.layout.item_entry, parent, false);
                EntryViewHolder entryViewHolder = new EntryViewHolder(entryView);
                return entryViewHolder;
            //            case 4:
            //                return getViewHolder(parent);
            //            case 6:
            //                return getViewHolder(parent);
            //            case 7:
            //                return getViewHolder(parent);
            //            case 10:
            //                return getViewHolder(parent);
            //            case 11:
            //                return getViewHolder(parent);
            //            case 12:
            //                return getViewHolder(parent);
            //            case 13:
            //                return getViewHolder(parent);
            //            case 14:
            //                return getViewHolder(parent);
            //            default:
            //                return null;


        }
        return getViewHolder(parent);
    }

    RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
        return new Mix1ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int num = getItemViewType(position);
        switch (num) {
            //            case 3:
            //            case 5:
            //            case 8:
            //            case 9:
            //            case 15:
            case 1:

                FocusViewHolder focusViewHolder = (FocusViewHolder) holder;
                focusViewHolder.cb.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, focusArr)
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setPageIndicator(new int[]{R.mipmap.ic_dot_default_unselected, R.mipmap.ic_dot_default_selected})
                ;
                focusViewHolder.cb.startTurning(5000);
                break;
            case 2:
                EntryViewHolder entryViewHolder = (EntryViewHolder) holder;
                if (entryBean.getResult() != null) {
                    //                    imageLoader.displayImage(entryArr.get(0),entryViewHolder.allSong);
                    //                    imageLoader.displayImage(entryArr.get(1),entryViewHolder.classify);
                    //                    imageLoader.displayImage(entryArr.get(2),entryViewHolder.btnCommand);
                    //                    imageLoader.displayImage(entryArr.get(3),entryViewHolder.vip);
                    DisplaySingle.getInstance().show(entryBean.getResult().get(0).getIcon(), entryViewHolder.allSong);
                    DisplaySingle.getInstance().show(entryBean.getResult().get(1).getIcon(), entryViewHolder.classify);
                    DisplaySingle.getInstance().show(entryBean.getResult().get(2).getIcon(), entryViewHolder.btnCommand);
                    DisplaySingle.getInstance().show(entryBean.getResult().get(3).getIcon(), entryViewHolder.vip);

                    entryViewHolder.allSong.setOnClickListener(this);
                    entryViewHolder.classify.setOnClickListener(this);
                    entryViewHolder.btnCommand.setOnClickListener(this);
                    entryViewHolder.vip.setOnClickListener(this);


                }
                break;
            case 4:
                BTestAdapter diyBaseAdapter = new BTestAdapter(context, diyBean.getResult());
                show((Mix1ViewHolder) holder, diyBaseAdapter, num, position);
                break;
            case 6:
                BTestAdapter mix1BaseAdapter = new BTestAdapter(context, mix1.getResult().subList(0, 6));
                show((Mix1ViewHolder) holder, mix1BaseAdapter, num, position);
                break;
            case 7:
                BTestAdapter bTestAdapter = new BTestAdapter(context, mix22.getResult());
                show((Mix1ViewHolder) holder, bTestAdapter, num, position);
                break;
            case 10:
                BTestAdapter recsongAdapter = new BTestAdapter(context, recsongBean.getResult().subList(0, 3));
                show((Mix1ViewHolder) holder, recsongAdapter, num, position);
                break;
            case 11:
                BTestAdapter mix9BaseAdapter = new BTestAdapter(context, mix9.getResult());
                show((Mix1ViewHolder) holder, mix9BaseAdapter, num, position);
                break;
            case 12:
                BTestAdapter mix5BaseAdapter = new BTestAdapter(context, mix5.getResult());
                show((Mix1ViewHolder) holder, mix5BaseAdapter, num, position);
                break;
            case 13:
                BTestAdapter radioBaseAdapter = new BTestAdapter(context, radioBean.getResult());
                show((Mix1ViewHolder) holder, radioBaseAdapter, num, position);
                break;
            case 14:
                BTestAdapter mod7Adapter = new BTestAdapter(context, mod7.getResult().subList(0,3));
                show((Mix1ViewHolder) holder, mod7Adapter, num, position);
                break;

        }


    }

    void show(Mix1ViewHolder holder, BTestAdapter testAdapter, int pos, int position) {
        testAdapter.setPos(pos);

        holder.gv.setAdapter(testAdapter);
        holder.gv.setOnItemClickListener(this);
        if (pos == 4) {
            holder.gv.setId(0);
        }
        //        holder.gv.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
        ViewGroup.LayoutParams layoutParams = holder.gv.getLayoutParams();
        if (pos == 14) {
            layoutParams.height = 680;
            holder.gv.setLayoutParams(layoutParams);
            holder.gv.setNumColumns(1);
        }
        if (pos == 10) {
            layoutParams.height = 350;
            holder.gv.setLayoutParams(layoutParams);
            holder.gv.setNumColumns(1);
        }
        if (pos == 11 || pos == 7) {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.gv.setLayoutParams(layoutParams);
        }


        holder.tv.setText(moduleBeanArrayList.get(position).getTitle());
        if (holder.more.getText() == "") {
            holder.more.setVisibility(View.GONE);
        } else {
            holder.more.setText(moduleBeanArrayList.get(position).getTitle_more());
        }
        //         imageLoader.displayImage(moduleBeanArrayList.get(position).getPicurl(),holder.iv);
        DisplaySingle.getInstance().show(moduleBeanArrayList.get(position).getPicurl(), holder.iv);
    }

    @Override
    public int getItemCount() {
        return moduleBeanArrayList == null ? 0 : moduleBeanArrayList.size();
    }


    private MyInterface myInterface;

    public MyInterface getMyInterface() {
        return myInterface;
    }

    public void setMyInterface(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //        Toast.makeText(context, "hehe" + i, Toast.LENGTH_SHORT).show();


        //
        //        Log.d("MyRVAdapter", "view:" + view.getTag());
        //        Log.d("MyRVAdapter", "adapterView:" + adapterView.getTag());
        //        Log.d("MyRVAdapter", "i:" + i);
        //        Log.d("MyRVAdapter", "l:" + l);
        //        Log.d("MyRVAdapter", "getViewHolder(adapterView):" + getViewHolder(adapterView));
        //        Log.d("MyRVAdapter", "getViewHolder(adapterView):" + getViewHolder(adapterView).hashCode());
        //        Log.d("MyRVAdapter", "getViewHolder(adapterView):" + getViewHolder(adapterView).getItemId());
        TextView textView = (TextView) view.findViewById(R.id.tv_item_base_listId);
        String tvUrl = URLVlaues.SONGLIST_DETAIL_Front + textView.getText() + URLVlaues.SONGLIST_DETAIL_BEHIND;
        Log.d("MyRVAdapter", "adapterView.getId():" + adapterView.getId());
        switch (adapterView.getId()) {
            case 0:
                Log.d("MyRVAdapter", tvUrl);

                SongListFrag songListFrag = new SongListFrag();
                songListFrag.setUrl(tvUrl);
                event.setFragment(songListFrag);
                EventBus.getDefault().post(event);
                break;
            case 1:
                Log.d("MyRVAdapter", textView.getText().toString());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ibtn_item_adv_all:

                //                MainActivity.repleseFrag(new SongerFrag());

                //                Toast.makeText(context, "歌手", Toast.LENGTH_SHORT).show();

                event.setFragment(new SongerFrag());
                EventBus.getDefault().post(event);

                break;
            case R.id.ibtn_item_adv_classify:
                //                Toast.makeText(context, "歌曲分类", Toast.LENGTH_SHORT).show();
                event.setFragment(new SongClassify());
                EventBus.getDefault().post(event);

                break;
            case R.id.ibtn_item_adv_command:

                Toast.makeText(context, "电台", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibtn_item_adv_vip:

                Log.d("MyRVAdapter", "VIP");
                break;

        }

    }


    class EntryViewHolder extends RecyclerView.ViewHolder {

        ImageButton vip;
        ImageButton allSong;
        ImageButton classify;
        ImageButton btnCommand;

        public EntryViewHolder(View itemView) {
            super(itemView);
            allSong = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_all);
            classify = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_classify);
            btnCommand = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_command);
            vip = (ImageButton) itemView.findViewById(R.id.ibtn_item_adv_vip);
        }
    }

    class FocusViewHolder extends RecyclerView.ViewHolder {
        //        TextView day;
        ConvenientBanner cb;

        public FocusViewHolder(View itemView) {
            super(itemView);
            cb = (ConvenientBanner) itemView.findViewById(R.id.frag_recommend_cb);
            //            day = (TextView) itemView.findViewById(R.id.tv_item_adv_day);
        }
    }

    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            //            imageLoader.displayImage(data,imageView);
            //            Picasso.with(context).load(data).into(imageView);
            DisplaySingle.getInstance().show(data, imageView);

        }
    }

    class Mix1ViewHolder extends RecyclerView.ViewHolder {
        private final TextView more;
        private final GridView gv;
        private final TextView tv;
        private final ImageView iv;

        public Mix1ViewHolder(View itemView) {
            super(itemView);
            more = (TextView) itemView.findViewById(R.id.tv_more_item_rv_recommend);
            gv = (GridView) itemView.findViewById(R.id.gv_item_rv_recommend);
            tv = (TextView) itemView.findViewById(R.id.tv_song_ic_recommend_song);
            iv = (ImageView) itemView.findViewById(R.id.iv_ic_recommend_song);
        }
    }

}
