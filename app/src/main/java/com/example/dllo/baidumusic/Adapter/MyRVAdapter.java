package com.example.dllo.baidumusic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.dllo.baidumusic.Bean.RecommBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class MyRVAdapter extends RecyclerView.Adapter {

    private Context context;



    private RecommBean recommBean;
    private RecommBean.ResultBean.DiyBean diyBean;
    private RecommBean.ResultBean.Mix1Bean mix1Bean;
    private RecommBean.ResultBean.Mix5Bean mix5Bean;
    private RecommBean.ResultBean.Mix9Bean mix9Bean;
    private RecommBean.ResultBean.Mix22Bean mix22Bean;
    private RecommBean.ResultBean.Mod7Bean mod7Bean;
    private RecommBean.ResultBean.RadioBean radioBean;
    private RecommBean.ResultBean.RecsongBean recsongBean;
    private RecommBean.ResultBean.SceneBean sceneBean;
    private RecommBean.ResultBean.EntryBean entryBean;
    private RecommBean.ResultBean.FocusBean focusBean;

    private RecommBean.ResultBean.SceneBean.Scene.ActionBean actionBean;


    private String urlStr;
    private ArrayList<RecommBean.ModuleBean> moduleBeanArrayList;

    private ArrayList<String> focusArr;//轮播图
    private ArrayList<String> entryArr;//图标
    private ArrayList<RecommBean.ResultBean.Mix1Bean.Mix1> mix1ArrayList;
    private ArrayList<RecommBean.ResultBean.DiyBean.Diy> diyBeanArrayList;
    private List<RecommBean.ResultBean.Mix22Bean.Mix22> mix22List;
    private ArrayList<RecommBean.ResultBean.RecsongBean.Recsong> recsongArrayList;
    private ArrayList<RecommBean.ResultBean.Mix9Bean.Mix9> mix9ArrayList;
    private ArrayList<RecommBean.ResultBean.Mix5Bean.Mix5> mix5ArrayList;
    private ArrayList<RecommBean.ResultBean.RadioBean.Radio> radioArrayList;
    private ArrayList<RecommBean.ResultBean.Mod7Bean.Mod7> mod7ArrayList;
    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.ActionBean> actionBeanArrayList;
    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.EmotionBean> emotionBeanArrayList;
    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.OperationBean> operationBeanArrayList;
    private ArrayList<RecommBean.ResultBean.SceneBean.Scene.OtherBean> otherBeanArrayList;


    public void setModuleBeanArrayList(ArrayList<RecommBean.ModuleBean> moduleBeanArrayList) {
        this.moduleBeanArrayList = moduleBeanArrayList;
        Log.d("MyRVAdapter", "moduleBeanArrayList:" + moduleBeanArrayList);
        notifyDataSetChanged();
    }


    public MyRVAdapter(Context context) {
        this.context = context;

    }

    public void setRecommBean(RecommBean recommBean) {
        this.recommBean = recommBean;
        Log.d("MyRVAdapter", "recommBean:" + recommBean);
        init();
        notifyDataSetChanged();
    }

    private void init() {
        this.entryBean = recommBean.getResult().getEntry();
        this.focusBean = recommBean.getResult().getFocus();
        this.diyBean = recommBean.getResult().getDiy();
        this.mix1Bean = recommBean.getResult().getMix_1();
        this.mix5Bean = recommBean.getResult().getMix_5();
        this.mix9Bean = recommBean.getResult().getMix_9();
        this.mix22Bean = recommBean.getResult().getMix_22();
        this.mod7Bean = recommBean.getResult().getMod_7();
        this.radioBean = recommBean.getResult().getRadio();
        this.recsongBean = recommBean.getResult().getRecsong();
        this.sceneBean = recommBean.getResult().getScene();

        //1
        entryArr = new ArrayList<>();
        for (int i = 0; i < entryBean.getResult().size(); i++) {
            entryArr.add(entryBean.getResult().get(i).getIcon());
        }

        //2
        focusArr = new ArrayList<>();
        for (int i = 0; i < focusBean.getResult().size(); i++) {
            focusArr.add(focusBean.getResult().get(i).getRandpic());
        }
        //4
        diyBeanArrayList = new ArrayList<>();
        for (int i = 0; i < diyBean.getResult().size(); i++) {
            diyBeanArrayList.add(diyBean.getResult().get(i));
        }
        //6
        mix1ArrayList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mix1ArrayList.add(mix1Bean.getResult().get(i));

        }
        //7
        mix22List = new ArrayList<>();
        for (int i = 0; i < mix22Bean.getResult().size(); i++) {
            mix22List.add(mix22Bean.getResult().get(i));
        }

        //8
        actionBeanArrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            actionBeanArrayList.add(sceneBean.getResult().getAction().get(i));
        }
//        emotionBeanArrayList = new ArrayList<>();
//        for (int i = 0; i < sceneBean.getResult().getAction().size(); i++) {
//            emotionBeanArrayList.add(sceneBean.getResult().getEmotion().get(i));
//        }
//        operationBeanArrayList = new ArrayList<>();
//        for (int i = 0; i < sceneBean.getResult().getAction().size(); i++) {
//            operationBeanArrayList.add(sceneBean.getResult().getOperation().get(i));
//        }
//        otherBeanArrayList = new ArrayList<>();
//        for (int i = 0; i < sceneBean.getResult().getAction().size(); i++) {
//            otherBeanArrayList.add(sceneBean.getResult().getOther().get(i));
//        }




        //10
        recsongArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            recsongArrayList.add(recsongBean.getResult().get(i));
        }
        //11
        mix9ArrayList = new ArrayList<>();
        for (int i = 0; i < mix9Bean.getResult().size(); i++) {
            mix9ArrayList.add(mix9Bean.getResult().get(i));
        }
        //12
        mix5ArrayList = new ArrayList<>();
        for (int i = 0; i < mix5Bean.getResult().size(); i++) {
            mix5ArrayList.add(mix5Bean.getResult().get(i));
        }
        //13
        radioArrayList = new ArrayList<>();
        for (int i = 0; i < radioBean.getResult().size(); i++) {
            radioArrayList.add(radioBean.getResult().get(i));
        }
        //14
        mod7ArrayList = new ArrayList<>();
        for (int i = 0; i < mod7Bean.getResult().size(); i++) {
            mod7ArrayList.add(mod7Bean.getResult().get(i));
        }
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("MyRVAdapter", "position:" + position);
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
            case 4:
                View diyView = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                DiyViewHolder diyViewHolder = new DiyViewHolder(diyView);

                return diyViewHolder;
            case 6:
                View mix1View = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder mix1ViewHolder = new Mix1ViewHolder(mix1View);
                return mix1ViewHolder;
            case 7:
                View mix22View = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder mix22ViewHolder = new Mix1ViewHolder(mix22View);
                return mix22ViewHolder;

            case 8:
                View sceneView = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend,parent,false);
                Mix1ViewHolder scenViewHolder = new Mix1ViewHolder(sceneView);
                return scenViewHolder;
            case 10:
                View recsongView = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder recsongViewHolder = new Mix1ViewHolder(recsongView);
                return recsongViewHolder;
            case 11:
                View mix9View = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder mix9ViewHolder = new Mix1ViewHolder(mix9View);
                return mix9ViewHolder;
            case 12:
                View mix5View = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder mix5ViewHolder = new Mix1ViewHolder(mix5View);
                return mix5ViewHolder;
            case 13:
                View radioView = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder radioViewHolder = new Mix1ViewHolder(radioView);
                return radioViewHolder;
            case 14:
                View mod7View = LayoutInflater.from(context).inflate(R.layout.item_rv_recommend, parent, false);
                Mix1ViewHolder mod7ViewHolder = new Mix1ViewHolder(mod7View);
                return mod7ViewHolder;
            default:
                return null;


        }

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
                        .setPageIndicator(new int[]{R.mipmap.ic_dot_default_unselected, R.mipmap.ic_dot_default_selected});


                break;
            case 2:
                EntryViewHolder entryViewHolder = (EntryViewHolder) holder;
                if (entryBean != null) {
                    imgToLoader(entryArr.get(0), entryViewHolder.allSong);
                    imgToLoader(entryArr.get(1), entryViewHolder.classify);
                    imgToLoader(entryArr.get(2), entryViewHolder.btnCommand);
                    imgToLoader(entryArr.get(3), entryViewHolder.vip);
                }
                break;

            case 4:
                DiyViewHolder diyViewHolder = (DiyViewHolder) holder;

                BTestAdapter diyBaseAdapter = new BTestAdapter(context, diyBeanArrayList);
                diyBaseAdapter.setPos(4);

                diyViewHolder.gv.setAdapter(diyBaseAdapter);
                diyViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                diyViewHolder.more.setText(moduleBeanArrayList.get(position).getTitle_more());

                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), diyViewHolder.iv);
                break;
            case 6:
                Mix1ViewHolder mix1ViewHolder = (Mix1ViewHolder) holder;

                BTestAdapter mix1BaseAdapter = new BTestAdapter(context, mix1ArrayList);
                mix1BaseAdapter.setPos(6);
                mix1ViewHolder.gv.setAdapter(mix1BaseAdapter);

                mix1ViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                mix1ViewHolder.more.setText(moduleBeanArrayList.get(position).getTitle_more());
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), mix1ViewHolder.iv);
                break;
            case 7:
                Mix1ViewHolder mix22ViewHolder = (Mix1ViewHolder) holder;


                BTestAdapter bTestAdapter = new BTestAdapter(context, mix22List);
                bTestAdapter.setPos(7);
                mix22ViewHolder.gv.setAdapter(bTestAdapter);

                ViewGroup.LayoutParams layoutParams = mix22ViewHolder.gv.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mix22ViewHolder.gv.setLayoutParams(layoutParams);

                mix22ViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                mix22ViewHolder.more.setVisibility(View.GONE);
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), mix22ViewHolder.iv);
                break;

            case 8:
                Mix1ViewHolder sceneViewHolder = (Mix1ViewHolder) holder;

//                int random =(int) Math.random()*4;
                int random = 0;
                BTestAdapter  sceneAdapter  = new BTestAdapter(context, actionBeanArrayList);
//                switch (random) {
//                    case 0:
//
//                    break;
//                    case 1:
//                        sceneAdapter  = new BTestAdapter(context, operationBeanArrayList);
//                        break;
//                    case 2:
//                        sceneAdapter  = new BTestAdapter(context, otherBeanArrayList);
//                        break;
//                    case 3:
//                        sceneAdapter  = new BTestAdapter(context, emotionBeanArrayList);
//                        break;
//
//                }
                sceneAdapter.setPos(8);
                sceneViewHolder.gv.setAdapter(sceneAdapter);

                ViewGroup.LayoutParams sceneLayoutParams = sceneViewHolder.gv.getLayoutParams();
                sceneLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                sceneViewHolder.gv.setLayoutParams(sceneLayoutParams);

                sceneViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                sceneViewHolder.more.setText(moduleBeanArrayList.get(position).getTitle_more());
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), sceneViewHolder.iv);
                break;
            case 10:
                Mix1ViewHolder recsongViewHolder = (Mix1ViewHolder) holder;

                BTestAdapter recsongAdapter = new BTestAdapter(context, recsongArrayList);
                recsongAdapter.setPos(10);
                recsongViewHolder.gv.setAdapter(recsongAdapter);

                ViewGroup.LayoutParams recsongLayoutParams = recsongViewHolder.gv.getLayoutParams();
                recsongLayoutParams.height = 350;
                recsongViewHolder.gv.setLayoutParams(recsongLayoutParams);
                recsongViewHolder.gv.setNumColumns(1);

                recsongViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                recsongViewHolder.more.setText(moduleBeanArrayList.get(position).getTitle_more());
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), recsongViewHolder.iv);
                break;
            case 11:
                Mix1ViewHolder mix9ViewHolder = (Mix1ViewHolder) holder;

                BTestAdapter mix9BaseAdapter = new BTestAdapter(context, mix9ArrayList);
                mix9BaseAdapter.setPos(11);

                mix9ViewHolder.gv.setAdapter(mix9BaseAdapter);
                ViewGroup.LayoutParams mix9LayoutParams = mix9ViewHolder.gv.getLayoutParams();
                mix9LayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mix9ViewHolder.gv.setLayoutParams(mix9LayoutParams);
                mix9ViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                mix9ViewHolder.more.setVisibility(View.GONE);
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), mix9ViewHolder.iv);
                break;
            case 12:
                Mix1ViewHolder mix5ViewHolder = (Mix1ViewHolder) holder;

                BTestAdapter mix5BaseAdapter = new BTestAdapter(context, mix5ArrayList);
                mix5BaseAdapter.setPos(12);

                mix5ViewHolder.gv.setAdapter(mix5BaseAdapter);

                mix5ViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                mix5ViewHolder.more.setVisibility(View.GONE);
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), mix5ViewHolder.iv);
                break;
            case 13:
                Mix1ViewHolder radioViewHolder = (Mix1ViewHolder) holder;
                BTestAdapter radioBaseAdapter = new BTestAdapter(context, radioArrayList);
                radioBaseAdapter.setPos(13);
                radioViewHolder.gv.setAdapter(radioBaseAdapter);
                radioViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                radioViewHolder.more.setText(moduleBeanArrayList.get(position).getTitle_more());
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), radioViewHolder.iv);
                break;

            case 14:
                Mix1ViewHolder mod7ViewHolder = (Mix1ViewHolder) holder;

                BTestAdapter mod7Adapter = new BTestAdapter(context, mod7ArrayList);
                mod7Adapter.setPos(14);
                mod7ViewHolder.gv.setAdapter(mod7Adapter);

                ViewGroup.LayoutParams mod7LayoutParams = mod7ViewHolder.gv.getLayoutParams();
                mod7LayoutParams.height = 550;
                mod7ViewHolder.gv.setLayoutParams(mod7LayoutParams);
                mod7ViewHolder.gv.setNumColumns(1);

                mod7ViewHolder.tv.setText(moduleBeanArrayList.get(position).getTitle());
                mod7ViewHolder.more.setText(moduleBeanArrayList.get(position).getTitle_more());
                imgToLoader(moduleBeanArrayList.get(position).getPicurl(), mod7ViewHolder.iv);
                break;

        }


    }

    @Override
    public int getItemCount() {
        return moduleBeanArrayList == null ? 0 : moduleBeanArrayList.size();
    }

    public void imgToLoader(String url, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url,
                new com.android.volley.Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {


                        imageView.setImageBitmap(response);

                    }
                }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        VolleySington.getInstance().addRequest(imageRequest);
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

    class DiyViewHolder extends RecyclerView.ViewHolder {
        private final TextView more;
        private final GridView gv;
        private final TextView tv;
        private final ImageView iv;

        public DiyViewHolder(View itemView) {
            super(itemView);
            more = (TextView) itemView.findViewById(R.id.tv_more_item_rv_recommend);
            gv = (GridView) itemView.findViewById(R.id.gv_item_rv_recommend);
            tv = (TextView) itemView.findViewById(R.id.tv_song_ic_recommend_song);
            iv = (ImageView) itemView.findViewById(R.id.iv_ic_recommend_song);

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
            imgLoader(data);

        }

        public void imgLoader(String url) {
            ImageRequest imageRequest = new ImageRequest(url,
                    new com.android.volley.Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            imageView.setImageBitmap(response);

                        }
                    }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("LocalImageHolderView", "error:" + error);
                }
            });
            VolleySington.getInstance().addRequest(imageRequest);
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
