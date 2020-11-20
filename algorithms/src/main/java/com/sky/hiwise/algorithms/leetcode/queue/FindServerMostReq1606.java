package com.sky.hiwise.algorithms.leetcode.queue;

import java.util.*;

public class FindServerMostReq1606 {
    /**
     * 1606. 找到处理最多请求的服务器
     * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
     *
     * 第 i （序号从 0 开始）个请求到达。
     * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
     * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
     * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
     * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
     *
     * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
     * 示例 1：
     * 输入：k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
     * 输出：[1]
     * 解释：
     * 所有服务器一开始都是空闲的。
     * 前 3 个请求分别由前 3 台服务器依次处理。
     * 请求 3 进来的时候，服务器 0 被占据，所以它呗安排到下一台空闲的服务器，也就是服务器 1 。
     * 请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
     * 服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
     */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(int i = 0; i < k; i++) {
            treeMap.put(i, 0);
        }
        int[] num = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            //接收第i个请求时，把已运行截止的请求从优先队列中删掉，并释放对应的服务器（丢进treeMap）
            while (!pq.isEmpty() && pq.peek().time < arrival[i]) {
                treeMap.put(pq.peek().server, 0);
                pq.poll();
            }
            //找第i%k服务器是否空闲，如果不空闲找它后面的空闲的服务器【treeMap特性，ceilingKey()返回>=i%k最小的空闲服务器号】
            Integer index = treeMap.ceilingKey(i % k);
            if (index == null) {
                //如果>=i%k找不到，那么从0开始找
                index = treeMap.ceilingKey(-1);
            }

            if (index != null) {
                //把该请求的截止时间丢进优先队列，注意load是持续时间，所以要-1
                pq.add(new Node(index, arrival[i] + load[i] - 1));
                treeMap.remove(index);
                num[index]++;
            }
        }
        int max = 0;
        for (int i = 0; i < k; i++) {
            max = Math.max(num[i], max);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (num[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 解题思路
     * TreeMap以key值进行排序，每次插入排序耗时LogN，查询耗时LogN，这个特性非常棒。
     *
     * 题目加一点难度：去掉条件（严格递增）
     * 解答：只需预处理成严格递增
     *
     * 作者：simon123-t
     * 链接：https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/solution/shuang-bai-you-xian-dui-lie-treemapbu-xu-yao-si-ka/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Node implements Comparable<Node> {
        public int server;
        public int time;
        public Node(int server, int time) {
            this.server = server;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
