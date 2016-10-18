package com.example.dllo.baidumusic.musicbean.mrecommbean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class DiyBean {


    private List<Diy> result;

    public List<Diy> getResult() {
        return result;
    }

    public void setResult(List<Diy> result) {
        this.result = result;
    }

  public static class Diy{
        private int position;
        private String tag;
        private String pic;
        private String title;
        private int collectnum;
        private String type;
        private int listenum;
        private String listid;
        private List<?> songidlist;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(int collectnum) {
            this.collectnum = collectnum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getListenum() {
            return listenum;
        }

        public void setListenum(int listenum) {
            this.listenum = listenum;
        }

        public String getListid() {
            return listid;
        }

        public void setListid(String listid) {
            this.listid = listid;
        }

        public List<?> getSongidlist() {
            return songidlist;
        }

        public void setSongidlist(List<?> songidlist) {
            this.songidlist = songidlist;
        }
    }


}
