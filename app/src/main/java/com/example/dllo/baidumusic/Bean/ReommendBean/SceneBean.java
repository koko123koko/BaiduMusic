package com.example.dllo.baidumusic.Bean.ReommendBean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class SceneBean {


    private SceneResultBean result;

    public SceneResultBean getResult() {
        return result;
    }

    public void setResult(SceneResultBean result) {
        this.result = result;
    }

    public static class SceneResultBean {
        private List<Scene> action;

        public List<Scene> getAction() {
            return action;
        }

        public void setAction(List<Scene> action) {
            this.action = action;
        }
    }


    public static class Scene {


        private String icon_ios;
        private String scene_name;
        private String bgpic_android;
        private String icon_android;
        private String scene_model;
        private String scene_desc;
        private String bgpic_ios;
        private String scene_id;

        public String getIcon_ios() {
            return icon_ios;
        }

        public void setIcon_ios(String icon_ios) {
            this.icon_ios = icon_ios;
        }

        public String getScene_name() {
            return scene_name;
        }

        public void setScene_name(String scene_name) {
            this.scene_name = scene_name;
        }

        public String getBgpic_android() {
            return bgpic_android;
        }

        public void setBgpic_android(String bgpic_android) {
            this.bgpic_android = bgpic_android;
        }

        public String getIcon_android() {
            return icon_android;
        }

        public void setIcon_android(String icon_android) {
            this.icon_android = icon_android;
        }

        public String getScene_model() {
            return scene_model;
        }

        public void setScene_model(String scene_model) {
            this.scene_model = scene_model;
        }

        public String getScene_desc() {
            return scene_desc;
        }

        public void setScene_desc(String scene_desc) {
            this.scene_desc = scene_desc;
        }

        public String getBgpic_ios() {
            return bgpic_ios;
        }

        public void setBgpic_ios(String bgpic_ios) {
            this.bgpic_ios = bgpic_ios;
        }

        public String getScene_id() {
            return scene_id;
        }

        public void setScene_id(String scene_id) {
            this.scene_id = scene_id;
        }
    }
}
