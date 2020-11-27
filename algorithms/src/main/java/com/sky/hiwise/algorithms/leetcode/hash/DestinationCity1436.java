package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationCity1436 {
    /**
     * 1436. 旅行终点站
     * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
     * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
     * 示例 1：
     * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
     * 输出："Sao Paulo"
     * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
     */
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for(List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        String key = paths.get(0).get(0);
        while (map.containsKey(key)) {
            key = map.get(key);
        }
        return key;
    }

    /**
     * 按照出度和入度的思路，操作map次数更多 效率更低
     * @param paths
     * @return
     */
    public String destCity1(List<List<String>> paths) {
        Map<String, Integer> map = new HashMap<>();
        for(List<String> path : paths) {
            map.put(path.get(0), map.getOrDefault(path.get(0), 0) + 1);
            map.put(path.get(1), map.getOrDefault(path.get(1), 0));
        }

        String ans = "";
        for(Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(0)) {
                ans = (String) entry.getKey();
            }
        }
        return ans;
    }
}
