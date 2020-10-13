package com.sky.hiwise.algorithms.leetcode.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencySort451 {

    /**
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     * 示例 1:
     * 输入:
     * "tree"
     * 输出:
     * "eert"
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * 示例 2:
     * 输入:
     * "cccaaa"
     * 输出:
     * "cccaaa"
     * 解释:
     * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * 示例 3:
     * 输入:
     * "Aabb"
     * 输出:
     * "bbAa"
     * 解释:
     * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
     * 注意'A'和'a'被认为是两种不同的字符。
     * @param s
     * @return
     */
    public static String frequencySort(String s) {
        char[] chs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int maxTimes = 0;
        for(char c : chs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxTimes = maxTimes > map.get(c) ? maxTimes : map.get(c);
        }
        int bucket = 0;
        ArrayList<Character>[] buckets = new ArrayList[maxTimes + 1];
        for (char key : map.keySet()) {
            //buckets
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }
        System.out.println(buckets);
        StringBuilder sb = new StringBuilder();
        for (int i = maxTimes; i >= 0; i--) {
            if (buckets[i] != null) {
                for(char c : buckets[i]) {
                    for(int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }

        }
        return sb.toString();
    }
    /**
     * 遍历字符数组，将每个字符的和它对应的频次存入哈希表中。
     * 新建一个桶，将每个字符存入索引为它的频次的那个桶中。
     * 由于桶的索引本身就是自增的，因此这样就直接利用桶完成了对每个字符按照它的出现次数进行了从大到小的排序。
     * 倒着遍历桶，将每个桶里的元素取出来，并按照它的频次存入要返回的结果中。
     */

    public static void main(String[] args) {
        String test = "tree";
        System.out.println(frequencySort(test));
    }

}
