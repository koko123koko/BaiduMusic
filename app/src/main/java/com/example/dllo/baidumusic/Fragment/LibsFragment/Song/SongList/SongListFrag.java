package com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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
import com.example.dllo.baidumusic.Base.BaseFragment;
import com.example.dllo.baidumusic.MyThreadPool.MyPool;
import com.example.dllo.baidumusic.MyThreadPool.MyRunnable;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.Service.SoundService;
import com.example.dllo.baidumusic.VolleyRequest.DisplaySingle;
import com.example.dllo.baidumusic.VolleyRequest.GsonRequest;
import com.example.dllo.baidumusic.VolleyRequest.URLVlaues;
import com.example.dllo.baidumusic.VolleyRequest.VolleySington;
import com.example.dllo.baidumusic.div.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

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

        rv.setItemAnimator(new DefaultItemAnimator());

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

    private void sendGet() {
        //        String url = URLVlaues.SONG_HOT;
        Log.d("RecommendFragment", "sendGet");
        GsonRequest<SongListBean> gsonRequest = new GsonRequest<SongListBean>(Url, SongListBean.class, new Response.Listener<SongListBean>() {

            private List<SongInfoBean> songInfoBeanList;

            @Override
            public void onResponse(final SongListBean response) {
                //

                songInfoBeanList = new ArrayList<>();
                for (int i = 0; i < response.getContent().size(); i++) {

                    String songId = response.getContent().get(i).getSong_id();
                    String url = URLVlaues.PLAY_FRONT + songId + URLVlaues.PLAY_BEHIND;
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);


                            if (msg.arg1 == 101) {

                                SongInfoBean songInfoBean = (SongInfoBean) msg.obj;
                                songInfoBeanList.add(songInfoBean);
                            }

                        }
                    };

                    MyPool.getInstance().getExecutorService().execute(new MyRunnable(url, handler));
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

    private void bindMusicService() {




//        defineServiceConnection();
//        Intent intent = new Intent(mContext, MusicplayerService.class);
//
//        mContext.bindService(intent, serviceConnection , Context.BIND_AUTO_CREATE);


    }

    private void defineServiceConnection() {

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                mBound = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
