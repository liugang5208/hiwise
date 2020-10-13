package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.*;

public class WordLadder126 {

    /**
     * 126. 单词接龙 II
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，
     * 找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     * 如果不存在这样的转换序列，返回一个空列表。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出:
     * [
     *   ["hit","hot","dot","dog","cog"],
     *   ["hit","hot","lot","log","cog"]
     * ]
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
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
        System.out.println(allPath);

        HashMap<String, List<String>> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        List<String> first = new ArrayList<>();
        first.add(beginWord);
        visited.put(beginWord, first);
        List<List<String>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            String curr = queue.remove();
            List<String> from = visited.get(curr);
            for(int i = 0; i < len; i++) {
                String nextWord = curr.substring(0, i) + "*" + curr.substring(i + 1);
                System.out.println("nextWord:" + nextWord);
                for (String matchWord : allPath.getOrDefault(nextWord, new ArrayList<>())) {
                    if (matchWord.equals(endWord)) {
                        List<String> temp = new ArrayList<>(from);
                        temp.add(matchWord);
                        ans.add(temp);
                    } else if (!visited.containsKey(matchWord)) {
                        List<String> temp1 = new ArrayList<>(from);
                        temp1.add(matchWord);
                        visited.put(matchWord, temp1);
                        queue.add(matchWord);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findLadders("a", "c", Arrays.asList("a", "b","c")));
    }
}
