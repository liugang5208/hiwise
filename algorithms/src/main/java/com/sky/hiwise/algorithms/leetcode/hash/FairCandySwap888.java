package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FairCandySwap888 {
    /**
     * 888. 公平的糖果棒交换
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
     * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
     * 示例 1：
     * 输入：A = [1,1], B = [2,2]
     * 输出：[1,2]
     * 示例 2：
     * 输入：A = [1,2], B = [2,3]
     * 输出：[1,2]
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> setA = new HashSet<>();
        for (int a : A) {
            setA.add(a);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (setA.contains(x)) {
                ans[0] = x;
                ans[1] = y;
            }
        }
        return ans;
    }

    /**
     * 方法一：哈希表
     * 思路及算法
     *
     * 记爱丽丝的糖果棒的总大小为 \textit{sumA}sumA，鲍勃的糖果棒的总大小为 \textit{sumB}sumB。设答案为 \{x,y\}{x,y}，即爱丽丝的大小为 xx 的糖果棒与鲍勃的大小为 yy 的糖果棒交换，则有如下等式：
     * sumA−x+y=sumB+x−y
     * 化简，得：
     * x = y + \frac{\textit{sumA} - \textit{sumB}}{2}
     * x=y+
     * 2
     * sumA−sumB
     * 即对于 BB 中的任意一个数 y'y
     *  ，只要 AA 中存在一个数 x'x
     *  ，满足 x' = y' + \dfrac{\textit{sumA} - \textit{sumB}}{2}x
     *  =y
     *  +
     * 2
     * sumA−sumB
     *  ，那么 \{x',y'\}{x
     *  ,y
     * ′
     *  } 即为一组可行解。
     * 为了快速查询 AA 中是否存在某个数，我们可以先将 AA 中的数字存入哈希表中。然后遍历 BB 序列中的数 y'
     *  ，在哈希表中查询是否有对应的 x'
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/fair-candy-swap/solution/gong-ping-de-tang-guo-jiao-huan-by-leetc-tlam/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param B
     * @return
     */

    public int[] fairCandySwap1(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        int i = 0, j = 0;
        int[] ans = new int[2];
        while (i < A.length && j < B.length) {
            int temp = A[i] - B[j];
            if (temp == delta) {
                ans[0] = A[i];
                ans[1] = B[j];
                return ans;
            } else if (temp > delta) {
                j++;
            } else {
                i--;
            }
        }
        return ans;
    }
    /**
     * 解题思路
     * 数学 + 双指针
     * sumA - sumB 差值其实就是交换需要弥补的差距
     * 定义需要取出来的是 xA 和 xB， 那么它们差值 xA - xB = (sumA - sumB) / 2
     * 思路就是：
     * 按照从小到大的排序A和B
     * 双指针，去遍历A和B， 考虑三种情况
     * xA-xB == (sumA - sumB) / 2 找到答案，返回即可
     * xA-xB > (sumA - sumB) / 2 , 则增大xB
     * xA-xB < (sumA - sumB) / 2 , 则增大xA
     * 作者：ffreturn
     * 链接：https://leetcode-cn.com/problems/fair-candy-swap/solution/cji-hu-shuang-bai-de-shuang-zhi-zhen-jie-a68h/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
