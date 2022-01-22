package cn.bugstack.guide.idea.plugin.domain.service;

import cn.bugstack.guide.idea.plugin.domain.model.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractWordManage {

    /**
     * 单词树结构
     * <p>
     * a->b
     * a->b->c
     * a->b->d
     * a->b->d->e
     */
    protected final Node wordsTree = new Node();

    protected int RESULT_LIMIT = 15;

    private final Pattern compile = Pattern.compile("^[A-Za-z]+$");

    private final Pattern explainPattern = Pattern.compile("(\\w{1,3}\\.)(.*?)(?=\\w{1,3}\\.|$)");

    public AbstractWordManage() {
        loadFile("/dictionary/word01.txt");
        loadFile("/dictionary/word02.txt");
        loadFile("/dictionary/word03.txt");
    }

    private void loadFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path), StandardCharsets.UTF_8));

            String line;
            while ((line = br.readLine()) != null) {
                String[] wordInfo = line.split("#");
                if (!compile.matcher(wordInfo[0]).matches()) {
                    continue;
                }

                wordInfo[0] = wordInfo[0].toLowerCase().trim();
                StringBuilder sb = new StringBuilder();
                if (wordInfo.length == 2) {
                    Matcher matcher = explainPattern.matcher(wordInfo[1]);
                    boolean hasMatch = matcher.find();
                    String singleExplain = wordInfo[1];
                    do {
                        if (hasMatch) {
                            singleExplain = matcher.group(2);
                            sb.append(matcher.group(1));
                        }

                        sb.append(Arrays.stream(singleExplain.split("[；;,，]")).min(Comparator.comparingInt(String::length)).get());
                        wordInfo[1] = sb.toString();
                    } while (hasMatch = matcher.find());
                }

                insert(wordsTree, wordInfo[0].toLowerCase().trim(), wordInfo.length == 2 ? wordInfo[1] : "");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insert(Node root, String words, String explain) {
        char[] chars = words.toCharArray();
        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (root.slot[charIndex] == null) {
                root.slot[charIndex] = new Node();
            }
            root = root.slot[charIndex];
            root.c = aChar;
            root.prefix++;
        }

        root.explain = explain;
        root.count++;

    }

    protected static void collect(Node node, String pre, List<Node> queue, int resultLimit) {
        if (node == null) {
            return;
        }

        if (node.count > 0) {
            node.word = pre;
            queue.add(node);
            if (queue.size() >= resultLimit) {
                return;
            }
        }

        for (int i = 0; i < node.slot.length; i++) {
            char c = (char) ('a' + i);
            if (node.slot[i] != null) {
                collect(node.slot[i], pre + c, queue, resultLimit);
            }
        }

    }

}
