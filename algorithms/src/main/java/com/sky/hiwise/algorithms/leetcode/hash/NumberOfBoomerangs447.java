package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs447 {

    /**
     * 447. 回旋镖的数量
     * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     * 返回平面上所有回旋镖的数量。
     * 示例 1：
     * 输入：points = [[0,0],[1,0],[2,0]]
     * 输出：2
     * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     */
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for(int[] p : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] q : points) {
                int a = p[0] - q[0], b = p[1] - q[1];
                int dis = a * a + b * b;
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
               ans += entry.getValue() * (entry.getValue() - 1);
            }
        }
        return ans;
    }
    /**
     * 对于每个回旋镖三元组而言，本质上我们在统计给定i 的情况下，与i 距离相等的 (j, k) 组合个数为多少。
     * 我们可以使用哈希表进行预处理，在统计以 i 为三元组第一位的回旋镖个数前，先计算出 i 和其余点的距离，并以 { 距离 : 个数 } 的形式进行存储，然后分别对所有的距离进行累加计数。
     * 在计算距离时为了避免使用 sqrt，我们直接使用 x^2 + y^2来代指两点间的距离。
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/number-of-boomerangs/solution/gong-shui-san-xie-shu-ju-jie-gou-yun-yon-evu2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        Long a = Long.MAX_VALUE;
        System.out.println(a);
    }
}
