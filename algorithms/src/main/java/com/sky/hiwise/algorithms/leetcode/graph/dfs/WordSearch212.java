package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.*;

public class WordSearch212 {
    /**
     * 212. 单词搜索 II
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
     * 示例 1：
     * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
     * 输出：["eat","oath"]
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        R = board.length;
        C = board[0].length;
        Set<String> ans = new HashSet<>();
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dfs(board, i, j, trie, ans);
            }
        }
        return new ArrayList<>(ans);
    }

    public void dfs(char[][] board, int i, int j, Trie trie, Set<String> ans) {
        char ch = board[i][j];
        if (!trie.children.containsKey(ch)) {
            return;
        }
        trie = trie.children.get(ch);
        if (!trie.word.equals("")) {
            ans.add(trie.word);
        }
        board[i][j] = '#';
        for (int[] dir : dirs) {
            int nexti = i + dir[0], nextj = j + dir[1];
            if (inArea(nexti, nextj)) {
                dfs(board,  nexti, nextj, trie, ans);
            }
        }
        board[i][j] = ch;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


    class Trie {
        public String word;
        public Map<Character, Trie> children;

        public Trie() {
            this.word = "";
            children = new HashMap<>();
        }
        public void insert(String word) {
            Trie curr = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new Trie());
                }
                curr = curr.children.get(c);
            }
            curr.word = word;
        }
    }
    /**
     * 方法一：回溯 + 字典树
     * 预备知识
     * 前缀树（字典树）是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。前缀树可以用 O(|S|)O(∣S∣) 的时间复杂度完成如下操作，其中 |S|∣S∣ 是插入字符串或查询前缀的长度：
     * 向前缀树中插入字符串 \textit{word}word；
     * 查询前缀串 \textit{prefix}prefix 是否为已经插入到前缀树中的任意一个字符串 \textit{word}word 的前缀；
     * 前缀树的实现可以参考「208. 实现 Trie (前缀树) 的官方题解」。
     * 思路和算法
     * 根据题意，我们需要逐个遍历二维网格中的每一个单元格；然后搜索从该单元格出发的所有路径，找到其中对应 \textit{words}words 中的单词的路径。因为这是一个回溯的过程，所以我们有如下算法：
     * 遍历二维网格中的所有单元格。
     * 深度优先搜索所有从当前正在遍历的单元格出发的、由相邻且不重复的单元格组成的路径。因为题目要求同一个单元格内的字母在一个单词中不能被重复使用；
     * 所以我们在深度优先搜索的过程中，每经过一个单元格，都将该单元格的字母临时修改为特殊字符（例如 #），以避免再次经过该单元格。
     * 如果当前路径是 \textit{words}words 中的单词，则将其添加到结果集中。如果当前路径是 wordswords 中任意一个单词的前缀，则继续搜索；反之，如果当前路径不是 wordswords 中任意一个单词的前缀，则剪枝。
     * 我们可以将 \textit{words}words 中的所有字符串先添加到前缀树中，而后用 O(|S|)O(∣S∣) 的时间复杂度查询当前路径是否为 \textit{words}words 中任意一个单词的前缀。
     * 在具体实现中，我们需要注意如下情况：
     * 因为同一个单词可能在多个不同的路径中出现，所以我们需要使用哈希集合对结果集去重。
     * 在回溯的过程中，我们不需要每一步都判断完整的当前路径是否是 wordswords 中任意一个单词的前缀；而是可以记录下路径中每个单元格所对应的前缀树结点，每次只需要判断新增单元格的字母是否是上一个单元格对应前缀树结点的子结点即可。
     */

}
