package com.sky.hiwise.algorithms.leetcode.graph.uf;

import java.util.HashMap;
import java.util.Map;

public class BabyNamesLCCI {
    /**
     * 面试题 17.07. 婴儿名字
     * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
     * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
     * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。
     * 设计一个算法打印出每个真实名字的实际频率。
     * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
     * 在结果列表中，选择 字典序最小 的名字作为真实名字。
     * 示例：
     * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
     * 输出：["John(27)","Chris(36)"]
     */
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> nameFreqMap = new HashMap<>();
        Map<String, String> unionMap = new HashMap<>();
        for (String name : names) {
            int idx1 = name.indexOf('(');
            int idx2 = name.indexOf(')');
            int freq = Integer.parseInt(name.substring(idx1 + 1, idx2));
            nameFreqMap.putIfAbsent(name.substring(0, idx1), freq);
        }

        for (String syn : synonyms) {
            int idx = syn.indexOf(',');
            String name1 = syn.substring(1, idx);
            String name2 = syn.substring(idx + 1, syn.length() - 1);
            while (unionMap.containsKey(name1)) {
                name1 = unionMap.get(name1);
            }

            while (unionMap.containsKey(name2)) {
                name2 = unionMap.get(name2);
            }

            //union
            if (!name1.equals(name2)) {
                int freq = nameFreqMap.getOrDefault(name1, 0) + nameFreqMap.getOrDefault(name2, 0);
                String realName = name1.compareTo(name2) < 0 ? name1 : name2;
                String nickName = name1.compareTo(name2) < 0 ? name2 : name1;
                unionMap.put(nickName, realName);
                nameFreqMap.remove(nickName);
                nameFreqMap.put(realName, freq);
            }
        }

        String[] ans = new String[nameFreqMap.size()];
        int index = 0;
        for (String name : nameFreqMap.keySet()){
            StringBuilder sb = new StringBuilder(name);
            sb.append("(")
            .append(nameFreqMap.get(name))
            .append(")");
            ans[index++] = sb.toString();
        }
        return ans;
    }
    /**
     * 解题思路：
     * 首先通过哈希表建立并查集，哈希表的键值对都是字符串，然后将一个相连的并查集合并；
     * 利用一个哈希表进行计数，计数的时候将值都累加到根元素。
     * 最后进行输出，输出的时候从头遍历，如果当前元素是一个根元素，那就输出。
     * 注意点：
     * 1.在合并的时候，为了满足题意，将字典序小的根元素作为新的根元素。
     * 作者：lippon
     * 链接：https://leetcode-cn.com/problems/baby-names-lcci/solution/java-bing-cha-ji-tong-yong-zuo-fa-by-lippon/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
