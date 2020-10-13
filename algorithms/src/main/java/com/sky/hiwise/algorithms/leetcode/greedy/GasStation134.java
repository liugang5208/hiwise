package com.sky.hiwise.algorithms.leetcode.greedy;

public class GasStation134 {

    /**
     * 134. 加油站
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * 说明:
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * 示例 1:
     * 输入:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * 输出: 3
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0, currTank = 0, startPos = 0;
        for (int i = 0; i < gas.length; i++) {
            currTank += gas[i] - cost[i];
            totalTank += gas[i] - cost[i];
            if (currTank < 0) {
                currTank = 0;
                startPos = i + 1;
            }
        }
        return totalTank >= 0 ? startPos : -1;
    }
    /**
     * 算法
     *
     * 那么现在算法是很直接明了的：
     *
     * 初始化 total_tank 和 curr_tank 为 0 ，并且选择 0 号加油站为起点。
     *
     * 遍历所有的加油站：
     *
     * 每一步中，都通过加上 gas[i] 和减去 cost[i] 来更新 total_tank 和 curr_tank 。
     *
     * 如果在 i + 1 号加油站， curr_tank < 0 ，将 i + 1 号加油站作为新的起点，同时重置 curr_tank = 0 ，让油箱也清空。
     *
     * 如果 total_tank < 0 ，返回 -1 ，否则返回 starting station。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
