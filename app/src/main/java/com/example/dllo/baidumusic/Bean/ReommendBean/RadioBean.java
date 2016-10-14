package com.example.dllo.baidumusic.Bean.ReommendBean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class RadioBean {

    private List<Radio> result;

    public List<Radio> getResult() {
        return result;
    }

    public void setResult(List<Radio> result) {
        this.result = result;
    }

    public static class Radio{

    private String desc;
    private String itemid;
    private String title;
    private String album_id;
    private String type;
    private String channelid;
    private String pic;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    }
}
