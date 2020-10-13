package com.sky.hiwise.algorithms.leetcode.array;

public class MaxDistanceClosestPerson849 {

    /**
     * 849. 到最近的人的最大距离
     * 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
     * 至少有一个空座位，且至少有一人坐在座位上。
     * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
     * 返回他到离他最近的人的最大距离。
     * 示例 1：
     * 输入：[1,0,0,0,1,0,1]
     * 输出：2
     * 解释：
     * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
     * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
     * 因此，他到离他最近的人的最大距离是 2 。
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int prev = -1, feature = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                while(feature < n && seats[feature] == 0 || feature < i) {
                    feature ++;
                }
                int left = prev == -1 ? n : i - prev;
                int right = feature == n ? n : feature - i;
                ans = Math.max(ans, Math.min(left, right));
            }
        }
        return ans;
    }
}
