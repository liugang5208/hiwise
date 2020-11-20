package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class AvoidFloodInCity1488 {
    /**
     * 1488. 避免洪水泛滥
     * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨的时候，如果第 n 个湖泊是空的，那么它就会装满水，否则这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
     * 给你一个整数数组 rains ，其中：
     * rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
     * rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
     * 请返回一个数组 ans ，满足：
     * ans.length == rains.length
     * 如果 rains[i] > 0 ，那么ans[i] == -1 。
     * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
     * 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
     * 请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生（详情请看示例 4）。
     * 示例 1：
     * 输入：rains = [1,2,3,4]
     * 输出：[-1,-1,-1,-1]
     * 解释：第一天后，装满水的湖泊包括 [1]
     * 第二天后，装满水的湖泊包括 [1,2]
     * 第三天后，装满水的湖泊包括 [1,2,3]
     * 第四天后，装满水的湖泊包括 [1,2,3,4]
     * 没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
     * 示例 2：
     * 输入：rains = [1,2,0,0,2,1]
     * 输出：[-1,-1,2,1,-1,-1]
     * 解释：第一天后，装满水的湖泊包括 [1]
     * 第二天后，装满水的湖泊包括 [1,2]
     * 第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
     * 第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
     * 第五天后，装满水的湖泊包括 [2]。
     * 第六天后，装满水的湖泊包括 [1,2]。
     * 可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
     */
    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            int r = rains[i];
            if (r == 0) {
                queue.add(i);
                continue;
            }
            if (map.containsKey(r)){
                int beforeIdx = map.get(r);
                if (beforeIdx == queue.peek()) {
                    return new int[rains.length];
                }
                ans[beforeIdx] = r;
                queue.poll();
            }
            map.put(r, i);
            ans[i] = -1;
        }
        return ans;
    }

    /**
     * 解题思路：
     * 将晴天的日期全部记录到 set<int> zero 中
     * 使用 unordered_map<int, int> water 来记录每个湖泊上一次下雨的日期
     * 遇到晴天时先不用管抽哪个湖
     * 当下雨时，湖泊已经水满了时，我们可以查询到上次下雨的日期
     * 通过这个日期在晴天记录中查找对应的晴天日期
     * 51. 在有序数据中查找，用二分啦
     * 如果找到了，就可以使用那一天抽水，找不到就不可避免的洪水了
     *
     * 作者：ikaruga
     * 链接：https://leetcode-cn.com/problems/avoid-flood-in-the-city/solution/avoid-flood-in-the-city-by-ikaruga/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
