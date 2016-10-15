package com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.VolleyRequest.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/10.
 */
public class SongListAdapter extends RecyclerView.Adapter {

    private List<SongListBean.ContentBean> contentBeen;

    private Context context;

    private MyItemClickListener myItemClickListener;

    private List<SongInfoBean> songInfoBeanList;
    private ListView lv;


//    private View FootView;
//
//    public void setFootView(View footView) {
//        FootView = footView;
//
//        notifyItemInserted(contentBeen.size()+1);
//    }
//
//    public View getFootView() {
//        return FootView;
//    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public SongListAdapter(Context context) {
        this.context = context;
    }

    public void setSongInfoBeanList(List<SongInfoBean> songInfoBeanList) {
        this.songInfoBeanList = songInfoBeanList;
        notifyDataSetChanged();
    }

    public void setContentBeen(ArrayList<SongListBean.ContentBean> contentBeen) {
        this.contentBeen = contentBeen;

//        for (int i = 0; i < contentBeen.size(); i++) {
////            AsynUrl asynUrl = new AsynUrl();
////            asynUrl.execute(contentBeen.get(i).getSong_id());
//
//        }

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        SongListViewHolder songListViewHolder = new SongListViewHolder(view);
        TypedValue typedValue = new TypedValue();
        MyApp.getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        view.setBackgroundResource(typedValue.resourceId);

        return songListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final SongListViewHolder songListViewHolder = (SongListViewHolder) holder;
        songListViewHolder.author.setText(contentBeen.get(position).getAuthor());
        songListViewHolder.songName.setText(contentBeen.get(position).getTitle());
//        songListViewHolder.songid.setText(songInfoBeanList.get(position).getBitrate().getFile_link());

        if (contentBeen.get(position).getHas_mv() == 0) {
            songListViewHolder.mv.setVisibility(View.GONE);
        } else {
            songListViewHolder.mv.setVisibility(View.VISIBLE);
        }
        if (contentBeen.get(position).getHigh_rate().equals("320")) {
            songListViewHolder.sq.setVisibility(View.VISIBLE);
        } else {
            songListViewHolder.sq.setVisibility(View.GONE);
        }
        if (contentBeen.get(position).getIs_ksong().equals("1")) {
            songListViewHolder.mike.setVisibility(View.VISIBLE);
        } else {
            songListViewHolder.mike.setVisibility(View.GONE);
        }
        songListViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemClickListener != null) {
                    myItemClickListener.onItemClick(songListViewHolder.ll, position);
                }
            }
        });

        songListViewHolder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (myItemClickListener != null) {
                    myItemClickListener.onItemLongClick(songListViewHolder.ll, position);
                }

                return true;
            }
        });

        songListViewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });


    }

    private void showPopupWindow(View parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.popup_item, null);

        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(parent,
                Gravity.BOTTOM, 0, 0);

        lv = (ListView) view.findViewById(R.id.lv_popup_item);

        PopupAdapter popupAdapter = new PopupAdapter(context);

        lv.setAdapter(popupAdapter);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("SongListAdapter", "消失");
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentBeen == null ? 0 : contentBeen.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mike;
        private final ImageView more;
        //        private final TextView songid;
        LinearLayout ll;
        TextView songName;
        TextView author;
        ImageView mv;
        ImageView sq;

        public SongListViewHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.tv_item_song_list_song_name);
            author = (TextView) itemView.findViewById(R.id.tv_item_song_list_author);
            mv = (ImageView) itemView.findViewById(R.id.iv_icon_mv);
            sq = (ImageView) itemView.findViewById(R.id.iv_icon_sq);
            mike = (ImageView) itemView.findViewById(R.id.iv_item_song_list_mike);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_item_list);
            more = (ImageView) itemView.findViewById(R.id.iv_item_song_list_more);
            //            songid = (TextView) itemView.findViewById(R.id.songid_gone);
        }
    }

//    class AsynUrl extends AsyncTask<String, Void, SongInfoBean> {
//
//        private SongInfoBean songInfoBean = null;
//
//        @Override
//        protected SongInfoBean doInBackground(String... params) {
//            try {
//                URL url1 = new URL(params[0]);
//                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
//                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    InputStream is = connection.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader br = new BufferedReader(isr);
//
//                    String line = "";
//                    String str = new String();
//                    while ((str = br.readLine()) != null) {
//                        line += str;
//                    }
//
//                    br.close();
//                    isr.close();
//                    is.close();
//
//                    line = line.substring(1, line.length() - 2);
//                    Gson gson = new Gson();
//                    songInfoBean = gson.fromJson(line, SongInfoBean.class);
//                    //                    MediaPlayer mediaPlayer = new MediaPlayer();
//                    //                    Log.d("AsynUrl", songInfoBean.getBitrate().getFile_link());
//                    //                    mediaPlayer.setDataSource(mContext, Uri.parse("http://yinyueshiting.baidu.com/data2/music/64334230/64334230.mp3?xcode=cfcd9b5c0d02774359dc68219d22c373"));
//                    //                    mediaPlayer.prepare();
//
//                }
//                connection.disconnect();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return songInfoBean;
//        }
//
//        @Override
//        protected void onPostExecute(SongInfoBean songInfoBean) {
//            super.onPostExecute(songInfoBean);
//
//            songInfoBeanList.add(songInfoBean);
//
//        }
//
//    }
}
