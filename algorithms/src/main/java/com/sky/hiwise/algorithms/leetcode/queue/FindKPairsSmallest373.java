package com.sky.hiwise.algorithms.leetcode.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsSmallest373 {

    /**
     * 373. 查找和最小的K对数字
     * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
     * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
     * 示例 1:
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * 示例 2:
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> {
            return sum(e2) - sum(e1);
        });
        int preNum1 = Integer.MAX_VALUE;
        int preNum2 = Integer.MAX_VALUE;
        for(int num1 : nums1) {
            for(int num2 : nums2) {
                if (num1 > preNum1 && num2 > preNum2) {
                    break;
                }
                int[] arr = new int[]{num1, num2};
                if (pq.size() < k) {
                    pq.add(arr);
                } else if (pq.size() >= k && sum(arr) < sum(pq.peek())) {
                    pq.poll();
                    pq.add(arr);
                    preNum1 = num1;
                    preNum2 = num2;
                }
            }
        }

        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int[] arr = pq.remove();
            temp.add(arr[0]);
            temp.add(arr[1]);
            ans.add(temp);
        }
        return ans;
    }

    private int sum(int[] arr) {
        return arr[0] + arr[1];
    }
}
