package com.sky.hiwise.algorithms.leetcode.trie;

import java.util.*;

public class LongestWordInDictionary720 {

    /**
     * 720. 词典中最长的单词
     * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
     *
     * 若无答案，则返回空字符串。
     *
     * 示例 1:
     *
     * 输入:
     * words = ["w","wo","wor","worl", "world"]
     * 输出: "world"
     * 解释:
     * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
     * 示例 2:
     *
     * 输入:
     * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * 输出: "apple"
     * 解释:
     * "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
     * @param words
     * @return
     */
    public String longestWord1(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        for (String word: words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }
        }
        return ans;
    }

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Trie trie = new Trie();
        int index = 0;
        for (String word : words) {
            trie.insert(word, ++index);
        }
        trie.words = words;
        return trie.dfs();
    }

    private class Node {
        public int end;
        public TreeMap<Character, Node> next;
        public Node(){
            next = new TreeMap<>();
        }
    }

    private class Trie {
        private Node root;
        String[] words;
        public Trie() {
            root = new Node();
        }

        public void insert(String word, int index) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.next.get(c) == null) {
                    curr.next.put(c, new Node());
                }
                curr = curr.next.get(c);
            }
            curr.end = index;
        }

        public String dfs() {
            String ans = "";
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                            ans = word;
                        }
                    }
                    for (Node next : node.next.values()) {
                        queue.add(next);
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"cat","banana","dog","nana","walk","walker","dogwalker"};
        System.out.println((new LongestWordInDictionary720()).longestWord(words));
    }

}