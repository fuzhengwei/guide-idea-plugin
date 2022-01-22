package cn.bugstack.guide.idea.plugin.domain.service;

import cn.bugstack.guide.idea.plugin.application.IWordManageService;
import cn.bugstack.guide.idea.plugin.domain.model.Node;
import com.intellij.openapi.components.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WordManageServiceImpl extends AbstractWordManage implements IWordManageService {

    @Override
    public List<Node> searchPrefix(String prefix) {
        Node root = wordsTree;
        char[] chars = prefix.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (charIndex > root.slot.length || charIndex < 0 || root.slot[charIndex] == null) {
                return Collections.emptyList();
            }
            sb.append(aChar);
            root = root.slot[charIndex];
        }

        ArrayList<Node> list = new ArrayList<>();
        if (root.prefix != 0) {
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
                    char c = (char) (i + 'a');
                    collect(root.slot[i], String.valueOf(sb) + c, list, RESULT_LIMIT);
                    if (list.size() >= RESULT_LIMIT) {
                        return list;
                    }
                }

            }
        }

        return list;
    }

}
