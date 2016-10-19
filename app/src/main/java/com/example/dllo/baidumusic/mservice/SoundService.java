package com.example.dllo.baidumusic.mservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.mbus.InfoBeanEvent;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.example.dllo.baidumusic.mutil.CommonUtils;
import com.example.dllo.baidumusic.recevie.NotificatoinBrocastReceiver;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/10/12.
 */
public class SoundService extends Service implements SoundServiceInterface {


    public static final String SERVICE_NAME = "com.example.dllo.baidumusic.mservice.SoundService";

    private MediaPlayer mediaPlayer;
    private List<SongInfoBean> songInfoBeanList;
    private NotificatoinBrocastReceiver mNotificatoinBrocastReceiver;

    private NotificationManager mNotificationManager;
    private Notification mNotification;

    public final static int PAUSED = 0;
    public final static int PLAYING = 1;
    private int state = PLAYING;
    private RemoteViews mRemoteViews;
    private SoundServiceBinder iBinder;
    private InfoBeanEvent event;
    private int mPosition = 0;

    public int getState() {
        return state;
    }

    public List<SongInfoBean> getSongInfoBeanList() {
        return songInfoBeanList;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        registerBrocastReceiver();

    }

    public synchronized void playNext() {
        Log.d("SoundService", "mPosition:" + mPosition);
        if ((mPosition + 1) == songInfoBeanList.size()) {
            mPosition = 0;
        } else {
            mPosition++;
        }

        playFetched(songInfoBeanList.get(mPosition).getBitrate().getFile_link());


    }

    public int changeState() {

        switch (state) {
            case PAUSED:

                play();
                state = PLAYING;
                break;
            case PLAYING:
                pause();
                state = PAUSED;
                break;
        }
        return state;

    }

    public int getPlayingPosition(){
        if (mediaPlayer != null)
            return mediaPlayer.getCurrentPosition();
        else
            return 0;
    }

    public int getPlayingDuration(){

        return mediaPlayer.getDuration();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boolean playing = intent.getBooleanExtra("playing", false);
        mPosition = intent.getIntExtra("position", 0);
        Log.d("SoundService", "mPosition:" + mPosition);
        songInfoBeanList = intent.getParcelableArrayListExtra("songInfo");
        //        if(iBinder != null){
        //            iBinder.setArrayListBean(songInfoBeanList);
        //        }

//        event = new InfoBeanEvent();
//        event.setSongInfoBeanList(songInfoBeanList);
//        event.setPosition(mPosition);
//        EventBus.getDefault().post(event);

        if (songInfoBeanList != null) {
            mediaPlayer = new MediaPlayer();
            String url = songInfoBeanList.get(mPosition).getBitrate().getFile_link();

            Log.d("SoundService", url);
            if (playing) {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(this, Uri.parse(url));
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                mediaPlayer.pause();
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mp.isPlaying() == false) {
                        Log.d("SoundService", "音乐播放完毕");
                        playNext();
                    }

                }


            });

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                     SongInfoBean.SonginfoBean songInfoBean = songInfoBeanList.get(mPosition).getSonginfo();
                    int duration = mediaPlayer.getDuration();
                    iBinder.setTotalTime(CommonUtils.timeFormatMs2Str(duration));
                    iBinder.SetBean(songInfoBeanList.get(mPosition));
                    iBinder.setMusicArtist(songInfoBean.getAuthor());
                    iBinder.setArrayListBean(songInfoBeanList);
                    iBinder.setMusicTitle(songInfoBean.getTitle());
                    iBinder.setMusicPic(songInfoBean.getPic_small());
                    iBinder.setCurrentPosition(mPosition);
                    iBinder.setLRC(songInfoBean.getLrclink());
                    startSeekBarTracker(mediaPlayer.getDuration());
                    play();

                    showBrocast();

                }
            });

            mSeekBarHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what){
                        case 0:
                            int ms = (int) msg.obj;
                            if (iBinder != null || ms == 0){
                                iBinder.setCurrentTime(CommonUtils.timeFormatMs2Str(ms));
                            }
                            break;
                        case 1:

                            break;
                    }
                    return false;
                }
            });


        }
        return super.onStartCommand(intent, flags, startId);
    }

    private AsyncTask<Integer,Void,Void> mSeekBarTracker;
    private Handler mSeekBarHandler;

    /**
     * 追踪 MediaPlayer 的播放状态，更新 Activity 的 SeekBar
     * @param duration
     */
    public void startSeekBarTracker (int duration) {
        if (mSeekBarTracker != null){
            mSeekBarTracker.cancel(true);
        }
        mSeekBarTracker = null;
        mSeekBarTracker = new AsyncTask<Integer, Void, Void>() {

            int ms = 0;
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    while(mediaPlayer != null && mediaPlayer.getCurrentPosition() < params[0]){
                        if (state == PLAYING){
                            ms = mediaPlayer.getCurrentPosition();
                            //                            mSeekBar.setProgress(ms);
                            // send to ui thread
                            mSeekBarHandler.obtainMessage(0, ms).sendToTarget();
                        }
                        Thread.sleep(100);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }
        };
        mSeekBarTracker.execute(duration);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        stopSelf();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //        return new SoundServiseBinder();
        iBinder = new SoundServiceBinder(this, this);
        return iBinder;
    }

    @Override
    public void addMusicToQueue(SongInfoBean bean) {
        if (songInfoBeanList == null) {
            songInfoBeanList = new ArrayList<>();
        }
        songInfoBeanList.add(bean);
    }

    @Override
    public void addMusicToQueue(List<SongInfoBean> songs) {
        if (songInfoBeanList == null) {
            songInfoBeanList = new ArrayList<>();
        }
        songInfoBeanList.addAll(songs);
    }

    @Override
    public void removeMusicFromQueue(SongInfoBean bean) {
        songInfoBeanList.remove(bean);
    }

    @Override
    public void removeMusicFromQueue(List<SongInfoBean> songs) {
        songInfoBeanList.removeAll(songs);
    }

    @Override
    public void skipToPoint(int point) {
        mediaPlayer.seekTo(point);
    }

    public int getCurrentPosintion(){

        return mPosition;
    }

    @Override
    public void play(int position) {
        this.mPosition = position;
        playFetched(songInfoBeanList.get(mPosition).getBitrate().getFile_link());

    }

    private void playFetched(String file_link) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, Uri.parse(file_link));
            mediaPlayer.prepareAsync();
//            mediaPlayer.prepare();
//            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void play() {

        mediaPlayer.start();

        if (iBinder != null) {
            iBinder.play();
        }
        if (mNotification != null){
            if (state == PAUSED) {
                mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_pause);

            } else {
                mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_play);

            }
            mNotificationManager.notify(1,mNotification);
        }
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
        if (iBinder != null) {
            iBinder.paused();
        }
        if (mNotification != null){
            if (state == PAUSED) {
                mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_pause);

            } else {
                mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_play);

            }
            mNotificationManager.notify(1,mNotification);
        }
    }

    public boolean registerBrocastReceiver() {

        mNotificatoinBrocastReceiver = new NotificatoinBrocastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotificatoinBrocastReceiver.PLAY_NEXT);
        intentFilter.addAction(NotificatoinBrocastReceiver.PLAY_PAUSE);
        intentFilter.addAction(NotificatoinBrocastReceiver.PLAYER_CANCEL);

        registerReceiver(mNotificatoinBrocastReceiver, intentFilter);
        mNotificatoinBrocastReceiver.registerMusicPlayerService(SoundService.this);

        return true;
    }

    public void showBrocast() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle("Playing")
                .setAutoCancel(true);
        //        mNotification.flags |= Notification.FLAG_NO_CLEAR;
        //
        RemoteViews remoteViews = getExpandView();
        builder.setContent(remoteViews);

        mNotification = builder.build();
//                mNotificationManager.notify(1,mNotification);

        startForeground(1, mNotification);

    }

    public RemoteViews getExpandView() {

        mRemoteViews = new RemoteViews(getPackageName(), R.layout.item_notification_view);

        String pic = songInfoBeanList.get(mPosition).getSonginfo().getPic_small();

        ImageLoader.getInstance().loadImage(pic,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mRemoteViews.setImageViewBitmap(R.id.iv_notification,loadedImage);
                if (mNotification != null){

                    mNotificationManager.notify(1,mNotification);
                }
            }
        });


        mRemoteViews.setTextViewText(R.id.tv_song_notification, songInfoBeanList.get(mPosition).getSonginfo().getTitle());

        mRemoteViews.setTextViewText(R.id.tv_author_notification, songInfoBeanList.get(mPosition).getSonginfo().getAuthor());

        if (state == PLAYING) {
            mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_pause);

        } else {
            mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_play);

        }
        Intent playIntent = new Intent(NotificatoinBrocastReceiver.PLAY_PAUSE);
        PendingIntent pIntentPlay = PendingIntent.getBroadcast(getApplicationContext(), 0, playIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_pause, pIntentPlay);


        Intent nextIntent = new Intent(NotificatoinBrocastReceiver.PLAY_NEXT);
        PendingIntent pIntentNext = PendingIntent.getBroadcast(getApplicationContext(), 0, nextIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_next, pIntentNext);

        Intent cancelIntent = new Intent(NotificatoinBrocastReceiver.PLAYER_CANCEL);
        PendingIntent pIntentCancel = PendingIntent.getBroadcast(getApplicationContext(), 0, cancelIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_close, pIntentCancel);

        return mRemoteViews;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mSeekBarTracker != null)
            mSeekBarTracker.cancel(true);
        mSeekBarTracker = null;


        return true;
    }

//    public int seekToLrc(int time){
//        return
//    }
}
