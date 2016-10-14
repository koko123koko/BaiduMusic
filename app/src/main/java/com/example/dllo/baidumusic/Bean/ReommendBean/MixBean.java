package com.example.dllo.baidumusic.Bean.ReommendBean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class MixBean {

    private List<Mix> result;

    public List<Mix> getResult() {
        return result;
    }

    public void setResult(List<Mix> result) {
        this.result = result;
    }

    public static class Mix {

        private String desc;
        private String pic;
        private String type_id;
        private int type;
        private String title;
        private int tip_type;
        private String author;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTip_type() {
            return tip_type;
        }

        public void setTip_type(int tip_type) {
            this.tip_type = tip_type;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
