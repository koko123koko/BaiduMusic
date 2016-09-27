package com.example.dllo.baidumusic.VolleyRequest;

/**
 * Created by dllo on 16/5/23.
 */
public class URLVlaues {

    public static final String RECOMMAND_ALL= "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.plaza.index&cuid=8ADCB33F64DBB1F5314036551C922491";
    public static final String RECOMMAND = "http://url.cn/28a65ZG";


    //歌单 -- 最热
    public static final String SONG_HOT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.ugcdiy.getChanneldiy&param=ehU89KYswcMRWNDWGfdd01ugcS8%2B1c891aJ47TFOGx%2FWu9YwjM9BVMZC4pzzHqXFSEbPI3z3BoOyi7hsZ2v%2FjKuV8DCOoHQKVKSdOa4T7hCsNrhDFklfo6iniJSvzBQl&timestamp=1474958544&sign=6432275fd0a0bdbb6678be4fd5764be9";

    //视频 -- 最新
    public static final String MV_NEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    //ktv
    public static final String KTV = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.learn.now&page_size=50";




    //首页轮播图url
    public static final String RECOMMAND_CAROUSE = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.plaza.getFocusPic&format=json&from=ios&version=5.2.3&from=ios&channel=appstore";
    //首页热门推荐url
    public static final String RECOMMAND_HOT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.getHotGeDanAndOfficial&num=4&version=5.2.3&from=ios&channel=appstore";
    //首页热门歌单url
    public static final String MUSICSTORE_TOP =
            "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";
    //歌单
    public static final String ALL_SONGLIST =
            "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";
    //歌单列表—热门歌单与推荐点击后传入参数
    public static final String SONGLIST_DETAIL_Front = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    public static final String SONGLIST_DETAIL_BEHIND = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    //
    public static final String TOP_SONG_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    public static final String TOP_SONG_BEHIND = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    //歌单列表—轮播图点击后传入参数
    public static final String RECOMMAND_CAROUSE_SONG_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.album.getAlbumInfo&album_id=";
    public static final String RECOMMAND_CAROUSE_SONG_BEHIND = "&format=json&from=ios&version=5.2.5&from=ios&channel=appstore";
    //播放页面
    public static final String PLAY_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    public static final String PLAY_BEHIND = "&_=1413017198449";

}
