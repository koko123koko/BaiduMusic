package com.example.dllo.baidumusic.musicbean.mrecommbean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class EntryBean {

   private List<Entry> result;

    public List<Entry> getResult() {
        return result;
    }

    public void setResult(List<Entry> result) {
        this.result = result;
    }

    public static class Entry{
        private String day;
        private String title;
        private String icon;
        private String jump;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getJump() {
            return jump;
        }

        public void setJump(String jump) {
            this.jump = jump;
        }
    }

}
