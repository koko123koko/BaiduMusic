package com.example.dllo.baidumusic.Bean.ReommendBean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 */
public class RecommBean {

    private ResultBean result;
    private int error_code;
    private List<ModuleBean> module;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<ModuleBean> getModule() {
        return module;
    }

    public void setModule(List<ModuleBean> module) {
        this.module = module;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public static class ResultBean{

        DiyBean diy;
        EntryBean entry;
        FocusBean focus;
        MixBean mix_1,mix_22,mix_5,mod_7,ad_small,mod_26,mix_9;
//        ModuleBean module;
        RadioBean radio;
        RecsongBean recsong;
        SceneBean scene;


        public DiyBean getDiy() {
            return diy;
        }

        public void setDiy(DiyBean diy) {
            this.diy = diy;
        }

        public EntryBean getEntry() {
            return entry;
        }

        public void setEntry(EntryBean entry) {
            this.entry = entry;
        }

        public FocusBean getFocus() {
            return focus;
        }

        public void setFocus(FocusBean focus) {
            this.focus = focus;
        }

        public MixBean getMix_1() {
            return mix_1;
        }

        public void setMix_1(MixBean mix_1) {
            this.mix_1 = mix_1;
        }

        public MixBean getMix_22() {
            return mix_22;
        }

        public void setMix_22(MixBean mix_22) {
            this.mix_22 = mix_22;
        }

        public MixBean getMix_5() {
            return mix_5;
        }

        public void setMix_5(MixBean mix_5) {
            this.mix_5 = mix_5;
        }

        public MixBean getMod_7() {
            return mod_7;
        }

        public void setMod_7(MixBean mod_7) {
            this.mod_7 = mod_7;
        }

        public MixBean getAd_small() {
            return ad_small;
        }

        public void setAd_small(MixBean ad_small) {
            this.ad_small = ad_small;
        }

        public MixBean getMod_26() {
            return mod_26;
        }

        public void setMod_26(MixBean mod_26) {
            this.mod_26 = mod_26;
        }

        public MixBean getMix_9() {
            return mix_9;
        }

        public void setMix_9(MixBean mix_9) {
            this.mix_9 = mix_9;
        }

//        public ModuleBean getModule() {
//            return module;
//        }
//
//        public void setModule(ModuleBean module) {
//            this.module = module;
//        }

        public RadioBean getRadio() {
            return radio;
        }

        public void setRadio(RadioBean radio) {
            this.radio = radio;
        }

        public RecsongBean getRecsong() {
            return recsong;
        }

        public void setRecsong(RecsongBean recsong) {
            this.recsong = recsong;
        }

        public SceneBean getScene() {
            return scene;
        }

        public void setScene(SceneBean scene) {
            this.scene = scene;
        }
    }
}
