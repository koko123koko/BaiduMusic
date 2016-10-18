package com.example.dllo.baidumusic.mfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.musicbean.MSGBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.div.CircleImageView;

import java.util.List;

/**
 * Created by dllo on 16/10/3.
 */
public class MSGAdapter extends RecyclerView.Adapter {


    List<MSGBean.MsgBean> msgBeanArrayList;

    private Context context;

    public MSGAdapter(Context context) {
        this.context = context;
    }

    public void setMsgBeanArrayList(List<MSGBean.MsgBean> msgBeanArrayList) {
        this.msgBeanArrayList = msgBeanArrayList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg, parent, false);

        MsgViewHolder msgViewHolder = new MsgViewHolder(view);


        return msgViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MsgViewHolder msgViewHolder = (MsgViewHolder)holder;
//        msgViewHolder.disc.setText(msgBeanArrayList.get(position).getComment_num());
//        msgViewHolder.content.setText(msgBeanArrayList.get(position).getContent().getTitle());

//        msgViewHolder.time.setText(msgBeanArrayList.get(position).getCtime());
        Log.d("MSGAdapter", "msgBeanArrayList.size():" + msgBeanArrayList.size());
    }

    @Override
    public int getItemCount() {
        return msgBeanArrayList == null ? 0 : msgBeanArrayList.size();
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {


        TextView agree;
        CircleImageView ci;
        TextView disc;
        TextView content;
        TextView name;
        TextView time;
        TextView topic;
        TextView songName;
        ImageView iv;
        ImageButton add;

        public MsgViewHolder(View itemView) {
            super(itemView);

            agree = (TextView) itemView.findViewById(R.id.agree_msg);
            ci = (CircleImageView) itemView.findViewById(R.id.ci_msg);
            disc = (TextView) itemView.findViewById(R.id.discuss_msg);
            content = (TextView) itemView.findViewById(R.id.tv_msg_content);
            name = (TextView) itemView.findViewById(R.id.tv_msg_name);
            time = (TextView) itemView.findViewById(R.id.tv_msg_time);
            topic = (TextView) itemView.findViewById(R.id.tv_msg_topic);
            songName = (TextView) itemView.findViewById(R.id.tv_msg_song_name);
            iv = (ImageView) itemView.findViewById(R.id.iv_msg_song);
            add = (ImageButton) itemView.findViewById(R.id.ib_msg_add);


        }
    }
}
