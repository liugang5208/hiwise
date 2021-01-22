package com.sky.hiwise.algorithms.leetcode.graph.toposort;

import java.util.*;

public class SortItemsGroupsRespectDependency1203 {
    /**
     * 1203. 项目管理
     * 有 n 个项目，每个项目或者不属于任何小组，或者属于 m 个小组之一。group[i] 表示第 i 个小组所属的小组，如果第 i 个项目不属于任何小组，则 group[i] 等于 -1。
     * 项目和小组都是从零开始编号的。可能存在小组不负责任何项目，即没有任何项目属于这个小组。
     * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
     * 同一小组的项目，排序后在列表中彼此相邻。
     * 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。
     * 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。
     * 示例 1：
     * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
     * 输出：[6,3,4,1,5,2,0,7]
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        //有 n 个项目 m 个小组
        // 第 1 步：数据预处理，给没有归属于一个组的项目编上组号
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // 第 2 步：实例化组和项目的邻接表
        List<Integer>[] groupAdj = new ArrayList[m];
        List<Integer>[] itemAdj = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            groupAdj[i] = new ArrayList<>();
        }
        for (int j = 0; j < n; j++) {
            itemAdj[j] = new ArrayList<>();
        }

        // 第 3 步：建图和统计入度数组
        int[] groupIndegree = new int[m];
        int[] itemIndegree = new int[n];
        for (int i = 0; i < group.length; i++) {
            int currentGroup = group[i];
            for (int beforeItem : beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (currentGroup != beforeGroup) {
                    groupAdj[beforeGroup].add(currentGroup);
                    groupIndegree[currentGroup] ++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int beforeItem : beforeItems.get(i)) {
                itemAdj[beforeItem].add(i);
                itemIndegree[i]++;
            }
        }

        // 第 4 步：得到组和项目的拓扑排序结果
        List<Integer> groupsList = topologicalSort(groupAdj, groupIndegree, m);
        if (groupsList.size() == 0) {
            return new int[0];
        }
        List<Integer> itemsList = topologicalSort(itemAdj, itemIndegree, n);
        if (itemsList.size() == 0) {
            return new int[0];
        }
        //第 5 步：根据项目的拓扑排序结果，项目到组的多对一关系，建立组到项目的一对多关系
        // key：组，value：在同一组的项目列表
        Map<Integer, List<Integer>> groups2Items = new HashMap<>();
        for (Integer item : itemsList) {
            groups2Items.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        // 第 6 步：把组的拓扑排序结果替换成为项目的拓扑排序结果
        List<Integer> ans = new ArrayList<>();
        for (Integer groupId : groupsList) {
            List<Integer> items = groups2Items.getOrDefault(groupId, new ArrayList<>());
            ans.addAll(items);
        }

        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    //拓扑排序 利用队列 不断遍历入度为0的节点
    private List<Integer> topologicalSort(List<Integer>[] adj, int[] indegree, int len) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res.add(curr);
            for (Integer next : adj[curr]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        if (res.size() == indegree.length) {
            return res;
        }
        return new ArrayList<>();
    }

//    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
//        // 第 1 步：数据预处理，给没有归属于一个组的项目编上组号
//        for (int i = 0; i < group.length; i++) {
//            if (group[i] == -1) {
//                group[i] = m;
//                m++;
//            }
//        }
//
//        // 第 2 步：实例化组和项目的邻接表
//        List<Integer>[] groupAdj = new ArrayList[m];
//        List<Integer>[] itemAdj = new ArrayList[n];
//        for (int i = 0; i < m; i++) {
//            groupAdj[i] = new ArrayList<>();
//        }
//        for (int i = 0; i < n; i++) {
//            itemAdj[i] = new ArrayList<>();
//        }
//
//        // 第 3 步：建图和统计入度数组
//        int[] groupsIndegree = new int[m];
//        int[] itemsIndegree = new int[n];
//
//        int len = group.length;
//        for (int i = 0; i < len; i++) {
//            int currentGroup = group[i];
//            for (int beforeItem : beforeItems.get(i)) {
//                int beforeGroup = group[beforeItem];
//                if (beforeGroup != currentGroup) {
//                    groupAdj[beforeGroup].add(currentGroup);
//                    groupsIndegree[currentGroup]++;
//                }
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (Integer item : beforeItems.get(i)) {
//                itemAdj[item].add(i);
//                itemsIndegree[i]++;
//            }
//        }
//
//        // 第 4 步：得到组和项目的拓扑排序结果
//        List<Integer> groupsList = topologicalSort(groupAdj, groupsIndegree, m);
//        if (groupsList.size() == 0) {
//            return new int[0];
//        }
//        List<Integer> itemsList = topologicalSort(itemAdj, itemsIndegree, n);
//        if (itemsList.size() == 0) {
//            return new int[0];
//        }
//
//        // 第 5 步：根据项目的拓扑排序结果，项目到组的多对一关系，建立组到项目的一对多关系
//        // key：组，value：在同一组的项目列表
//        Map<Integer, List<Integer>> groups2Items = new HashMap<>();
//        for (Integer item : itemsList) {
//            groups2Items.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
//        }
//
//        // 第 6 步：把组的拓扑排序结果替换成为项目的拓扑排序结果
//        List<Integer> res = new ArrayList<>();
//        for (Integer groupId : groupsList) {
//            List<Integer> items = groups2Items.getOrDefault(groupId, new ArrayList<>());
//            res.addAll(items);
//        }
//        return res.stream().mapToInt(Integer::valueOf).toArray();
//    }
//
//    private List<Integer> topologicalSort(List<Integer>[] adj, int[] inDegree, int n) {
//        List<Integer> res = new ArrayList<>();
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            if (inDegree[i] == 0) {
//                queue.offer(i);
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            Integer front = queue.poll();
//            res.add(front);
//            for (int successor : adj[front]) {
//                inDegree[successor]--;
//                if (inDegree[successor] == 0) {
//                    queue.offer(successor);
//                }
//            }
//        }
//
//        if (res.size() == n) {
//            return res;
//        }
//        return new ArrayList<>();
//    }

}
