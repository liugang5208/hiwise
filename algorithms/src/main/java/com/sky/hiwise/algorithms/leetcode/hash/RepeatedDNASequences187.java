package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequences187 {
    /**
     * 187. 重复的DNA序列
     * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
     * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
     * 示例 1：
     * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * 输出：["AAAAACCCCC","CCCCCAAAAA"]
     * 示例 2：
     * 输入：s = "AAAAAAAAAAAAA"
     * 输出：["AAAAAAAAAA"]
     * 提示：
     * 0 <= s.length <= 105
     * s[i] 为 'A'、'C'、'G' 或 'T'
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i + 10 <= n; i++) {
            String seq = s.substring(i, i + 10);
            int cnt = map.getOrDefault(seq, 0);
            if (cnt == 1) {
                ans.add(seq);
            }
            map.put(seq, cnt + 1);
        }
        return ans;
    }
}
