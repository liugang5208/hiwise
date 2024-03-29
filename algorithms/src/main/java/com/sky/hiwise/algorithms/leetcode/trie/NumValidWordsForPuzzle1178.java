package com.sky.hiwise.algorithms.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumValidWordsForPuzzle1178 {
    /**
     * 1178. 猜字谜
     * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
     * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
     * 单词 word 中包含谜面 puzzle 的第一个字母。
     * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
     * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
     * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
     * 示例：
     * 输入：
     * words = ["aaaa","asas","able","ability","actt","actor","access"],
     * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
     * 输出：[1,1,3,2,4,0]
     * 解释：
     * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
     * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
     * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
     * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
     * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
     * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
     * 提示：
     * 1 <= words.length <= 10^5
     * 4 <= words[i].length <= 50
     * 1 <= puzzles.length <= 10^4
     * puzzles[i].length == 7
     * words[i][j], puzzles[i][j] 都是小写英文字母。
     * 每个 puzzles[i] 所包含的字符都不重复。
     */
    TrieNode root;
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        root = new TrieNode();
        for (String word : words) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    sb.append(arr[i]);
                }
            }
            addTrie(root, sb.toString());
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            char required = puzzle.charAt(0);
            char[] puzzleChars = puzzle.toCharArray();
            Arrays.sort(puzzleChars);
            ans.add(findNum(new String(puzzleChars), required, root, 0));
        }
        return ans;
    }

    // 在回溯的过程中枚举 puzzle 的所有子集并统计答案
    // find(puzzle, required, cur, pos) 表示 puzzle 的首字母为 required, 当前搜索到字典树上的 cur 节点，并且准备枚举 puzzle 的第 pos 个字母是否选择（放入子集中）
    // find 函数的返回值即为谜底的数量
    private int findNum(String puzzle, char required, TrieNode curr, int pos) {
        if (curr == null) {
            return 0;
        }
        // 整个 puzzle 搜索完毕，返回谜底的数量
        if (pos == 7) {
            return curr.freq;
        }
        // 选择第 pos 个字母
        int ret = findNum(puzzle, required, curr.child[puzzle.charAt(pos) - 'a'], pos + 1);
        // 当 puzzle.charAt(pos) 不为首字母时，可以不选择第 pos 个字母
        if (puzzle.charAt(pos) != required) {
            ret += findNum(puzzle, required, curr, pos + 1);
        }
        return ret;
    }

    private void addTrie(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.child[ch - 'a'] == null) {
                curr.child[ch - 'a'] = new TrieNode();
            }
            curr = curr.child[ch - 'a'];
        }
        curr.freq++;
    }

    class TrieNode {
        int freq;
        TrieNode[] child;
        public TrieNode() {
            freq = 0;
            child = new TrieNode[26];
        }
    }
}
