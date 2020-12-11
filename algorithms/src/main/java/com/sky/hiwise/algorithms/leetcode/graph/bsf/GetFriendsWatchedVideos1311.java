package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.*;

public class GetFriendsWatchedVideos1311 {
    /**
     * 1311. 获取你好友已观看的视频
     * 有 n 个人，每个人都有一个  0 到 n-1 的唯一 id 。
     * 给你数组 watchedVideos  和 friends ，其中 watchedVideos[i]  和 friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
     * Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。
     * 一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k 的好友观看过的视频。
     * 给定你的 id  和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。
     * 示例 1：
     * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
     * 输出：["B","C"]
     * 解释：
     * 你的 id 为 0（绿色），你的朋友包括（黄色）：
     * id 为 1 -> watchedVideos = ["C"]
     * id 为 2 -> watchedVideos = ["B","C"]
     * 你朋友观看过视频的频率为：
     * B -> 1
     * C -> 2
     */
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        visited[id] = true;
        for (int i = 0; i < level; i++) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int currFrd = queue.poll();
                for (int frd : friends[currFrd]) {
                    if (!visited[frd]) {
                        queue.add(frd);
                        visited[frd] = true;
                    }
                }
            }
        }
        Map<String, Integer> map = new HashMap<>();
        while(!queue.isEmpty()) {
            List<String> video = watchedVideos.get(queue.poll());
            for (String v : video) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }

        List<String> ans = new ArrayList<>(map.keySet());
        Collections.sort(ans, (o1, o2) -> {
            if (map.get(o1) == map.get(o2)) {
                return o1.compareTo(o2);
            } else {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        return ans;
    }

    public static void main(String[] args) {
        List<List<String>> watchedVideos = new ArrayList<>();
        watchedVideos.add(Arrays.asList("A", "B"));
        watchedVideos.add(Arrays.asList("C"));
        watchedVideos.add(Arrays.asList("B", "C"));
        watchedVideos.add(Arrays.asList("D"));
        int[][] friends = new int[][]{{1,2},{0,3},{0,3},{1,2}};
        int id = 0, level = 1;
        System.out.println((new GetFriendsWatchedVideos1311()).watchedVideosByFriends(watchedVideos, friends, id, level));
    }
}
