package com.sky.hiwise.algorithms.leetcode.trie;

import java.util.HashMap;
import java.util.Map;

public class MapSumPairs677 {

    /**
     * 677. 键值映射
     * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
     * MapSum() 初始化 MapSum 对象
     * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
     * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
     * 示例：
     * 输入：
     * ["MapSum", "insert", "sum", "insert", "sum"]
     * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
     * 输出：
     * [null, null, 3, null, 5]
     * 解释：
     * MapSum mapSum = new MapSum();
     * mapSum.insert("apple", 3);
     * mapSum.sum("ap");           // return 3 (apple = 3)
     * mapSum.insert("app", 2);
     * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
     * 提示：
     * 1 <= key.length, prefix.length <= 50
     * key 和 prefix 仅由小写英文字母组成
     * 1 <= val <= 1000
     * 最多调用 50 次 insert 和 sum
     */
    class MapSum {

        Map<String, Integer> map;
        Map<String, Integer> prefixMap;
        public MapSum() {
            map = new HashMap<>();
            prefixMap = new HashMap<>();
        }

        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            for (int i = 1; i <= key.length(); i++) {
                String prefixCurr = key.substring(0, i);
                prefixMap.put(prefixCurr, prefixMap.getOrDefault(prefixCurr, 0) + delta);
            }
        }

        public int sum(String prefix) {
            return prefixMap.getOrDefault(prefix, 0);
        }
    }
    /**
     * 方法二：前缀哈希映射
     * 思路与算法
     * 我们可以用哈希表存储所有可能前缀的值。当我们得到一个新的 \texttt{key-val}key-val 键值，我们将 \textit{key}key 的每个前缀 \textit{prefix}[i]prefix[i] 都在哈希表中进行存储，
     * 我们需要更新每个前缀 \textit{prefix}[i]prefix[i] 对应的值。我们计算出它对应的值的增加为 \textit{delta}delta，计算方式如下：
     * 如果键 \textit{key}key 不存在，则此时 \textit{delta}delta 等于 \textit{val}val。
     * 如果键 \textit{key}key 存在，则此时键 \textit{key}key 对应得前缀的值都增加 \textit{val} - \textit{map}[\textit{key}]val−map[key]，
     * 其中 \textit{map}[\textit{key}]map[key] 表示键 \textit{key}key 当前对应的值。
     * 我们在完成前缀的值改写后，同时要更新键 \textit{key}key 对应的值为 \textit{val}val。
     * 求 \texttt{sum}sum 时,我们直接利用哈希表查找给定的前缀对应的值即可。
     */
}
