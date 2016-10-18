package com.example.dllo.baidumusic.musicbean.mrecommbean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class RecsongBean {


    private List<Recsong> result;

    public List<Recsong> getResult() {
        return result;
    }

    public void setResult(List<Recsong> result) {
        this.result = result;
    }

    public static class Recsong {

        private String resource_type_ext;
        private String learn;
        private String del_status;
        private String korean_bb_song;
        private String versions;
        private String title;
        private String bitrate_fee;
        private String song_id;
        private String has_mv_mobile;
        private String pic_premium;
        private String author;

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getLearn() {
            return learn;
        }

        public void setLearn(String learn) {
            this.learn = learn;
        }

        public String getDel_status() {
            return del_status;
        }

        public void setDel_status(String del_status) {
            this.del_status = del_status;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getHas_mv_mobile() {
            return has_mv_mobile;
        }

        public void setHas_mv_mobile(String has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public String getPic_premium() {
            return pic_premium;
        }

        public void setPic_premium(String pic_premium) {
            this.pic_premium = pic_premium;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
