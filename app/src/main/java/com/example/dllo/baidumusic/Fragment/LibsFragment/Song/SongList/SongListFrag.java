package com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList;

import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcelable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.Service.SoundService;
import com.example.dllo.baidumusic.VolleyRequest.DisplaySingle;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;
import com.example.dllo.baidumusic.div.CircleImageView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/10/8.
 */
public class SongListFrag extends BaseFragment {

    private ImageView iv;
    private RelativeLayout fl;
    private RelativeLayout fltop;

    public String Url;
    private RecyclerView rv;
    private CircleImageView ci;
    private ImageView more;
    private ImageView pic;
    private ImageView play;
    private ImageView ivReturn;
    private TextView favorite;
    private TextView count;
    private TextView name;
    private TextView tag;
    private TextView title;


    private ServiceConnection serviceConnection;
    private boolean mBound = false;
    private TextView share;


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;

    }


    @Override
    protected void initData() {


        sendGet();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ShareSDK.initSDK(getContext());
        //        iv.setImageBitmap();
        //        Bitmap backgroud = changeBackgroundImage(bitmap);
        //        Drawable drawable = new BitmapDrawable(backgroud);
        //        Drawable drawable1 = new BitmapDrawable(backgroud);
        //        fl.setBackground(drawable);
        //        fltop.setBackground(drawable1);
        Log.d("SongListFrag", Url);
        //
        //        MyRVAdapter myRVAdapter = new MyRVAdapter(getContext());
        //        myRVAdapter.setMyInterface(new MyInterface() {
        //            @Override
        //            public Object GetUrl(String url) {
        //                Log.d("SongListFrag233", url);
        //                return null;
        //            }
        //        });
        //
        //        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //        rv.setLayoutManager(manager);
        //        rv.setAdapter(myRVAdapter);

        //
        //        Drawable drawable =iv.getDrawable();
        //        fl.setBackground(drawable);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });


    }

    @Override
    protected void initVIew() {

        iv = bindView(R.id.iv_song_list_back);
        fl = bindView(R.id.rl_item_song_list_frag);
        fltop = bindView(R.id.fl_item_top_frag);
        rv = bindView(R.id.rv_item_song_list);

        ci = bindView(R.id.ci_item_song_list_frag);
        more = bindView(R.id.iv_item_song_list_frag_more);
        pic = bindView(R.id.iv_item_song_list_frag_pic);
        play = bindView(R.id.iv_item_song_list_frag_play);
        ivReturn = bindView(R.id.iv_item_song_list_frag_retrun);

        favorite = bindView(R.id.tv_item_song_list_favorite);
        count = bindView(R.id.tv_item_song_list_frag_count);
        name = bindView(R.id.tv_item_song_list_frag_name);
        tag = bindView(R.id.tv_item_song_list_frag_tag);
        title = bindView(R.id.tv_item_song_list_frag_title);

        share = bindView(R.id.tv_item_song_list_share_playlist);

        rv.setItemAnimator(new DefaultItemAnimator());

//        ShareSDK.initSDK(mContext,"d6a1118259c4b383e93ca11f");

    }

    @Override
    protected int setLayout() {
        return R.layout.item_song_list_frag;
    }

    public Bitmap changeBackgroundImage(Bitmap sentBitmap) {
        if (Build.VERSION.SDK_INT > 16) {
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            final RenderScript rs = RenderScript.create(getContext());
            final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(25.0f);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        return null;
    }
    private SongListBean songListBean;
    private void sendGet() {
        //        String url = URLVlaues.SONG_HOT;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<SongListBean> gsonRequest = new GsonRequest<SongListBean>(Url, SongListBean.class, new Response.Listener<SongListBean>() {


            private int i;
            private List<SongInfoBean> songInfoBeanList;

            @Override
            public void onResponse(final SongListBean response) {
                //

                songListBean =  response;
                songInfoBeanList = new ArrayList<>();
                for (i = 0; i < response.getContent().size(); i++) {

                    String songId = response.getContent().get(i).getSong_id();
                    String url = URLVlaues.PLAY_FRONT + songId + URLVlaues.PLAY_BEHIND;

                    StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            response = response.substring(1, response.length() - 2);
                            Gson gson = new Gson();
                            SongInfoBean songInfoBean = gson.fromJson(response,SongInfoBean.class);
                            songInfoBeanList.add(songInfoBean);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    VolleySington.getInstance().addRequest(stringRequest);

//                    Handler handler = new Handler() {
//
//
//                        @Override
//                        public void handleMessage(Message msg) {
//                            super.handleMessage(msg);
//
//
//                            if (msg.arg1 == 101 ) {
//
//                                SongInfoBean songInfoBean = (SongInfoBean) msg.obj;
//                                songInfoBeanList.add(songInfoBean);
//                            }
//
//                        }
//                    };
//
//                    MyPool.getInstance().getExecutorService().execute(new MyRunnable(url, handler));
                }

                title.setText(response.getTitle());
                favorite.setText(response.getCollectnum());
                tag.setText(response.getTag());
                DisplaySingle.getInstance().show(response.getPic_w700(), pic);
                Bitmap bitmap = ImageLoader.getInstance().loadImageSync(response.getPic_w700());

                Bitmap back = changeBackgroundImage(bitmap);
                iv.setImageBitmap(back);

                SongListAdapter songListAdapter = new SongListAdapter(mContext);

                songListAdapter.setContentBeen((ArrayList<SongListBean.ContentBean>) response.getContent());
                final LinearLayoutManager manager = new LinearLayoutManager(mContext);


                songListAdapter.setMyItemClickListener(new MyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("SongListFrag", "songInfoBeanList.get(position).getBitrate():" + songInfoBeanList.get(position).getBitrate().getFile_link());

                        Log.d("SongListFrag", "position:" + position);
                        Intent intent = new Intent(mContext, SoundService.class);
                        intent.putExtra("playing",true);
                        intent.putExtra("position",position);
                        intent.putParcelableArrayListExtra("songInfo", (ArrayList<? extends Parcelable>) songInfoBeanList);
                        mContext.startService(intent);


                    }


                    @Override
                    public void onItemLongClick(View view, int position) {
                        Log.d("SongListFrag", "position:" + position);
                    }
                });

//                View footView = LayoutInflater.from(mContext).inflate(R.layout.item_list_foot,null);
//
//                songListAdapter.setFootView(footView);
                rv.setLayoutManager(manager);


                rv.setAdapter(songListAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RecommendFragment", "error:" + error);
            }
        });
        VolleySington.getInstance().addRequest(gsonRequest);

    }



    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        if(songListBean != null) {
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
            oks.setTitle(songListBean.getTitle());
            // titleUrl是标题的网络链接，QQ和QQ空间等使用
            oks.setTitleUrl(songListBean.getUrl());
            // text是分享文本，所有平台都需要这个字段
            oks.setText(songListBean.getDesc());
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl(songListBean.getPic_300());
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl(songListBean.getUrl());

            // 启动分享GUI
            oks.show(getContext());
        }
    }
}
