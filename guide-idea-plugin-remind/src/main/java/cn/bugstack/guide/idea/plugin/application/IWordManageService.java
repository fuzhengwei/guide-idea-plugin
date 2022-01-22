package cn.bugstack.guide.idea.plugin.application;

import cn.bugstack.guide.idea.plugin.domain.model.Node;

import java.util.List;

public interface IWordManageService {

    List<Node> searchPrefix(String prefix);

}
