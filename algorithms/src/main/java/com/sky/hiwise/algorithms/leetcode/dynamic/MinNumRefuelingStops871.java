package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinNumRefuelingStops871 {
    /**
     * 871. 最低加油次数
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
     * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
     * 示例 1：
     * 输入：target = 1, startFuel = 1, stations = []
     * 输出：0
     * 解释：我们可以在不加油的情况下到达目的地。
     * 示例 2：
     * 输入：target = 100, startFuel = 1, stations = [[10,100]]
     * 输出：-1
     * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
     * 示例 3：
     * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
     * 输出：2
     * 解释：
     * 我们出发时有 10 升燃料。
     * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
     * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
     * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
     * 我们沿途在1两个加油站停靠，所以返回 2 。
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        int[] dp = new int[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int t = i; t >= 0; t--) {
                if (dp[t] >= stations[i][0]) {
                    dp[t + 1] = Math.max(dp[t + 1], dp[t] + stations[i][1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 方法一： 动态规划
     * 思路
     * dp[i] 为加 i 次油能走的最远距离，需要满足 dp[i] >= target 的最小 i。
     * 算法
     * 依次计算每个 dp[i]，对于 dp[0]，就只用初始的油量 startFuel 看能走多远。
     * 每多一个加油站 station[i] = (location, capacity)，如果之前可以通过加 t 次油到达这个加油站，现在就可以加 t+1 次油得到 capcity 的油量。
     * 举个例子，原本加一次油可以行驶的最远距离为 15，现在位置 10 有一个加油站，有 30 升油量储备，那么显然现在可以加两次油行驶 45 距离。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/solution/zui-di-jia-you-ci-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    /**
     * 方法二：栈
     * 思路
     * 每驶过一个加油站，记住这个加油站有多少油。不需要立即决定要不要在这个加油站加油，如果后面有油量更多的加油站显然优先选择后面的加油。
     * 如果当前油量不够抵达下一个加油站，必须得从之前的加油站中找一个来加油，贪心选择最大油量储备的加油站就好了。
     * 算法
     * 定义 pq（优先队列）为一个保存了驶过加油站油量的最大堆，定义当前油量为 tank。
     * 如果当前油量为负数（意味着当前油量不够抵达当前位置），那就必须在驶过的加油站找一个油量储备最大来加油。
     * 如果在某个位置油量为负，且没有加油站可用了，那就不可能完成这个任务。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/solution/zui-di-jia-you-ci-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int minRefuelStopsStack(int target, int startFuel, int[][] stations) {
        int ans = 0, prev = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] station : stations) {
            int location = station[0];
            int cap = station[1];
            startFuel -= location - prev;
            while (!pq.isEmpty() && startFuel < 0) {
                startFuel += pq.poll();
                ans++;
            }
            if (startFuel < 0) {
                return -1;
            }
            pq.offer(cap);
            prev = location;
        }
        startFuel -= target - prev;
        while (!pq.isEmpty() && startFuel < 0) {
            startFuel += pq.poll();
            ans++;
        }
        if (startFuel < 0) {
            return -1;
        }
        return ans;
    }
}
