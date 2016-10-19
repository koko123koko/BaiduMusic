package com.example.dllo.baidumusic.mlrc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.div.view.ILrcBuilder;
import com.example.dllo.baidumusic.div.view.ILrcView;
import com.example.dllo.baidumusic.div.view.ILrcViewListener;
import com.example.dllo.baidumusic.div.view.impl.DefaultLrcBuilder;
import com.example.dllo.baidumusic.div.view.impl.LrcRow;
import com.example.dllo.baidumusic.mbus.InfoBeanEvent;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.example.dllo.baidumusic.mservice.SoundService;
import com.example.dllo.baidumusic.mservice.SoundServiceBinder;
import com.example.dllo.baidumusic.mservice.SoundServiceBinderCallBack;
import com.example.dllo.baidumusic.mutil.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/10/15.
 */
public class songLrcFrag extends BaseFragment {

    //自定义LrcView，用来展示歌词
    ILrcView mLrcView;
    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;
    //更新歌词的定时器
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;

    private SongInfoBean currentSong;
    private Intent intent;
    private ServiceConnection mServiceConnection;

    private SoundService mSoundService;
    private SoundServiceBinder mSoundServiceBinder;
    private boolean mBound;
    private int state;
    private int currentPosition;
    private List<SongInfoBean> songs;

    //    public void setCurrentSong(SongInfoBean currentSong) {
    //        this.currentSong = currentSong;
    //    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setSongInfoBeanList(InfoBeanEvent event) {
        if (event.getSongInfoBeanList() != null) {
            Log.d("LRCFrag", "进来了");
            bindMusicService();
            songs = event.getSongInfoBeanList();
            currentPosition = event.getPosition();
            currentSong = songs.get(currentPosition);
        }
    }

    String lrcUrl;

    @Override
    protected void initData() {

        bindMusicService();
        //从assets目录下读取歌词文件内容
        //        String lrc = getFromAssets(currentSong.getSonginfo().getLrclink());
//        if (lrcUrl != null) {
//            new AsynUrl().execute(currentSong.getSonginfo().getLrclink());
//        }
        //开始播放歌曲并同步展示歌词
        //        beginLrcPlay();

        //设置自定义的LrcView上下拖动歌词时监听
        mLrcView.setListener(new ILrcViewListener() {
            //当歌词被用户上下拖动的时候回调该方法,从高亮的那一句歌词开始播放
            public void onLrcSeeked(int newPosition, LrcRow row) {
                mSoundService.skipToPoint((int) row.time);
            }
        });

    }

    @Override
    protected void initVIew() {
        mLrcView = bindView(R.id.lrcview_lrc_item);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void bindMusicService() {
        defineServiceConnection(); // we define our service connection mConnection
        intent = new Intent(getActivity(), SoundService.class);
        //        intent.putExtra(MusicPlayerService.ACTIVITY_INDENTIFY, MusicPlayerService.FULLSCREEN_PLAYER_ACTIVITY);
        getActivity().bindService(intent, mServiceConnection
                , Context.BIND_AUTO_CREATE);
    }

    private void defineServiceConnection() {

        if (!CommonUtils.isServiceWorked(SoundService.SERVICE_NAME, getActivity())) {
            intent = new Intent(getActivity(), SoundService.class);
            intent.putExtra("playing", true);
            intent.putExtra("position", currentPosition);
            intent.putParcelableArrayListExtra("songInfo", (ArrayList<? extends Parcelable>) songs);
            getActivity().startService(intent);
        }

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, final IBinder service) {
                mSoundServiceBinder = (SoundServiceBinder) service;
                //                        if (songName != null) {
                //                            songName.setText(title);
                //                        }
                //                        if (author != null) {
                //                            author.setText(artist);
                //                        }
                //                        if (pic != null) {
                //                            DisplaySingle.getInstance().show(urlPic, pic);
                //                        }
                //                        if (songInfoBean != null){
                //                        }
                //                        if (songs != null){
                //                        }
                mSoundService = mSoundServiceBinder.getSoundService(new SoundServiceBinderCallBack() {
                    @Override
                    public void setImagePlay() {

                    }
                    @Override
                    public void setImagePaused() {

                    }
                    @Override
                    public void setCurrentTime(String time) {

                    }
                    @Override
                    public void setTotalTime(String time) {

                    }

                    @Override
                    public void setMusicTitle(String title) {

                    }

                    @Override
                    public void setMusicArtist(String artist) {

                    }

                    @Override
                    public void setMusicPic(String urlPic) {

                    }

                    @Override
                    public void setBean(SongInfoBean songInfoBean) {
                        currentSong = songInfoBean;
                    }

                    @Override
                    public void setArrayListBean(List<SongInfoBean> songInfoBeanList) {
                        songs = songInfoBeanList;
                    }

                    @Override
                    public void setCurrentPosition(int position) {
                        currentPosition = position;
                    }

                    @Override
                    public void setLRC(String lrc) {

                            lrcUrl = lrc;
                            new AsynUrl().execute(lrcUrl);

                    }



                });
                mBound = true;
                state = mSoundService.getState();
                //                seekBar.setMax(Integer.parseInt());
                //                Log.d("LRCFrag", "currentSong.getBitrate().getFile_duration():" + currentSong.getBitrate().getFile_duration()+"");

                //                currentSong = mSoundService.getSongInfoBeanList().get(currentPosition);
                //                end.setText(CommonUtils.timeFormatMs2Str(currentSong.getBitrate().getFile_duration()));
                //
                //
                //                //                currentSong.getBitrate().getFile_duration();
                //                seekBar.setMax(currentSong.getBitrate().getFile_duration());
                //                seekBar.setProgress(mSoundService.getPlayingPosition());
                //
                //
                //                mSoundService.startSeekBarTracker(currentSong.getBitrate().getFile_duration());


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;

            }
        };

    }

    @Override
    protected int setLayout() {
        return R.layout.lrc_item;
    }

    class AsynUrl extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = connection.getInputStream();
                    InputStreamReader inputReader = new InputStreamReader(is);
                    BufferedReader bufReader = new BufferedReader(inputReader);
                    String line = "";

                    while ((line = bufReader.readLine()) != null) {
                        if (line.trim().equals(""))
                            continue;
                        result += line + "\r\n";
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("songLrcFrag", s);
            //解析歌词构造器
            ILrcBuilder builder = new DefaultLrcBuilder();
            //解析歌词返回LrcRow集合
            List<LrcRow> rows = builder.getLrcRows(s);
            //将得到的歌词集合传给mLrcView用来展示
            mLrcView.setLrc(rows);

        }
    }

    /**
     * 从assets目录下读取歌词文件内容
     *
     * @param urlPath
     * @return
     */

    MediaPlayer mPlayer;

    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(currentSong.getBitrate().getFile_link());
            //准备播放歌曲监听
            //            mPlayer.setOnPreparedListener(new OnPreparedListener() {
            //                //准备完毕
            //                public void onPrepared(MediaPlayer mp) {
            //                    mp.start();
            //                    if(mTimer == null){
            //                        mTimer = new Timer();
            //                        mTask = new LrcTask();
            //                        mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
            //                    }
            //                }
            //            });
            //            //歌曲播放完毕监听
            //            mPlayer.setOnCompletionListener(new OnCompletionListener() {
            //                public void onCompletion(MediaPlayer mp) {
            //                    stopLrcPlay();
            //                }
            //            });
            //准备播放歌曲
            mPlayer.prepare();
            //开始播放歌曲
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
