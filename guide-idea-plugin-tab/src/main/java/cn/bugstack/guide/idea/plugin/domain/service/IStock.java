package cn.bugstack.guide.idea.plugin.domain.service;

import cn.bugstack.guide.idea.plugin.domain.model.vo.Data;
import cn.bugstack.guide.idea.plugin.domain.model.vo.GoPicture;

import java.util.List;

public interface IStock {

    List<Data> queryPresetStockData(List<String> gids);

    GoPicture queryGidGoPicture(String gid);
}
