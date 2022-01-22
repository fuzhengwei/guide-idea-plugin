package cn.bugstack.guide.idea.plugin.domain.model;

/**
 * @description: 单词链节点
 * @author: 小傅哥，微信：fustack
 * @date: 2022/1/22
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Node {

    /** 形成一个链 */
    public Node[] slot = new Node[26];

    /** 字母 */
    public char c;

    /** 数量 */
    public int count;

    /** 前缀 */
    public int prefix;

    /** 单词 */
    public String word;

    /** 单词解释 */
    public String explain;

}
