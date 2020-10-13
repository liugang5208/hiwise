package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.net.Socket;
import java.util.*;

public class WordLadder127 {

    /**
     * 127. 单词接龙
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     *
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        HashMap<String, List<String>> allPath = new HashMap<>();
        wordList.forEach(word -> {
            for(int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> temps = allPath.getOrDefault(newWord, new ArrayList<>());
                temps.add(word);
                allPath.put(newWord, temps);
            }
        });

        HashMap<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String curr = queue.remove();
            int level = visited.get(curr);
            for(int i = 0; i < len; i++) {
                String nextWord = curr.substring(0, i) + "*" + curr.substring(i + 1);
                for (String matchWord : allPath.getOrDefault(nextWord, new ArrayList<>())) {
                    if (matchWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(matchWord)) {
                        visited.put(matchWord, level + 1);
                        queue.add(matchWord);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(ladderLength(beginWord,endWord,wordList));
    }
}
