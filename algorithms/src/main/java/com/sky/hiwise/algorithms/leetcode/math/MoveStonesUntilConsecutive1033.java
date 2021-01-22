package com.sky.hiwise.algorithms.leetcode.math;

import java.util.Arrays;

public class MoveStonesUntilConsecutive1033 {
    /**
     * 1033. 移动石子直到连续
     * 三枚石子放置在数轴上，位置分别为 a，b，c。
     * 每一回合，我们假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。
     * 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
     * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
     * 示例 1：
     * 输入：a = 1, b = 2, c = 5
     * 输出：[1, 2]
     * 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
     * 示例 2：
     * 输入：a = 4, b = 3, c = 2
     * 输出：[0, 0]
     * 解释：我们无法进行任何移动。
     */
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        int min = 0;
        int left = arr[1] - arr[0], right = arr[2] - arr[1];
        if (left == 1 && right == 1) {
            min = 0;
        } else if (left == 1 || right == 1 || arr[1] + 1 == arr[2] - 1 || arr[1] - 1 == arr[0] + 1) {
            // 如果有任意两个石子相邻，最少次数为 1
            min = 1;
        } else {
            min = 2;
        }
        return new int[]{min, left + right - 2};
    }
    /**
     * 这道题 max 不是难点，min 需要分情况讨论。
     * 假设三个石子已经有序，位置分别为 x,y,z
     * min = 0
     * 如果 x + 1 = y,y + 1 = z比如 1,2,3 那么 min 为 0
     * min = 1
     * 如果 x + 1 = y || y + 1 = z 比如 1,2,4 那么 min 为 1
     * 如果 y - x > 1 && z - y > 1 ，此时比较复杂。按照正常的思路，x 右移一次 + z 左移一次，min是 2 。但是事实上如果 z - 1 = y + 1 || x + 1 = y - 1 比如 1,3,5 或 2,4,7 直接把左边的石子或右边的石子移动到对边即可。也就是：
     * 1,3,5 : 1 --> 4
     * 2,4,7 : 7 --> 3
     * 剩下的情况就是 min 为 2 了
     * 作者：shepard2020
     * 链接：https://leetcode-cn.com/problems/moving-stones-until-consecutive/solution/xiang-jie-min-ru-he-que-ding-by-dan-huan-icum/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
