package com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.baidumusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/14.
 */
public class PopupAdapter extends BaseAdapter{

    List<SongInfoBean> songInfoBeanArrayList;
    Context context;

    public PopupAdapter(Context context) {
        this.context = context;
    }

    public void setSongInfoBeanArrayList(ArrayList<SongInfoBean> songInfoBeanArrayList) {
        this.songInfoBeanArrayList = songInfoBeanArrayList;
    }

    @Override
    public int getCount() {
        return songInfoBeanArrayList == null ? 0 :songInfoBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return songInfoBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PopViewHolder popViewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popup,parent,false);
            popViewHolder = new PopViewHolder(convertView);
            convertView.setTag(popViewHolder);


        } else {
            popViewHolder = (PopViewHolder)convertView.getTag();
        }

        popViewHolder.author.setText(songInfoBeanArrayList.get(position).getSonginfo().getAuthor());
        popViewHolder.song.setText(songInfoBeanArrayList.get(position).getSonginfo().getTitle());
        popViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "功能未实现", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class PopViewHolder{

        private final TextView author;
        private final TextView song;
        private final ImageView del;

        public PopViewHolder(View itemView) {
            author = (TextView) itemView.findViewById(R.id.tv_popup_item_author);
            song = (TextView) itemView.findViewById(R.id.tv_popup_item_song);
            del = (ImageView) itemView.findViewById(R.id.del_item_popup);

        }
    }
}
