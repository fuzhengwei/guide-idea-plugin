package cn.bugstack.guide.idea.plugin.test;

import cn.bugstack.guide.idea.plugin.domain.model.Node;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiTest {

    private static Pattern explainPattern = Pattern.compile("(\\w{1,3}\\.)(.*?)(?=\\w{1,3}\\.|$)");

    @Test
    public void test() throws IOException {
        Node wordsTree = new Node();
        Pattern compile = Pattern.compile("^[A-Za-z]+$");
        BufferedReader br = new BufferedReader(new InputStreamReader(ApiTest.class.getResourceAsStream("/dictionary/word02.txt"), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            String[] wordInfo = line.split("#");
            if (!compile.matcher(wordInfo[0]).matches()) {
                continue;
            }
            simplifyExplanations(wordInfo);
        }

        System.out.println(wordsTree);

    }

    private static void simplifyExplanations(String[] wordInfo) {
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

    }


}
