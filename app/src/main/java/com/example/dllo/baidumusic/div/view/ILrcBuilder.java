package com.example.dllo.baidumusic.div.view;


import com.example.dllo.baidumusic.div.view.impl.LrcRow;

import java.util.List;

/**
 * 解析歌词，得到LrcRow的集合
 */
public interface ILrcBuilder {
    List<LrcRow> getLrcRows(String rawLrc);
}
