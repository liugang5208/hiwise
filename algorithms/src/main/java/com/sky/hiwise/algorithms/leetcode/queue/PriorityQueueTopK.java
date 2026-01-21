package com.sky.hiwise.algorithms.leetcode.queue;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityQueueTopK {

    /**
     * 347 前 K 个高频元素
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * 说明：
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.comparingInt(map::get)
        );
        for(int key: map.keySet()){
            if(pq.size() < k)
                pq.add(key);
            else if(map.get(key) > map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty())
            res.add(pq.remove());
        return res;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {


//        int[] nums = {1, 1, 1, 2, 2, 3};
//        int k = 2;
//        printList((new PriorityQueueTopK()).topKFrequent(nums, k));

//        int[] nums = {23,76,2,23,4,6,25,236,4,5,127,558,23,6};
//        printList((new PriorityQueueTopK()).topK(nums, 5));
        int[] a = {1,3,5,7,2,4,6,8};
        smallestK(a, 4);
    }
    /**
     * 方法 1：堆
     * 想法
     * k = 1 时问题很简单，线性时间内就可以解决。只需要用哈希表维护元素出现频率，每一步更新最高频元素即可。
     * 当 k > 1 就需要一个能够根据出现频率快速获取元素的数据结构，这就是优先队列。
     * 首先建立一个元素值对应出现频率的哈希表。在 Java 中使用 HashMap，但需要手工填值。在 Python 中提供一个字典结构用作哈希表和在 collections 库中的 Counter 方法去构建我们需要的哈希表。
     * 这个步骤需要 O(N)O(N) 时间其中 NN 是列表中元素个数。
     * 第二步建立堆，堆中添加一个元素的复杂度是 O(\log(k))O(log(k))，要进行 NN 次复杂度是 O(N)O(N)。
     * 最后一步是输出结果，复杂度为 O(k\log(k))O(klog(k))。
     * 在 Python 中可以使用 heapq 库中的 nlargest 方法，可以在相同时间内完成，但只需要一行代码解决。
     * 复杂度分析
     * 时间复杂度：O(N\log(k))O(Nlog(k))。Counter 方法的复杂度是 O(N)O(N)，建堆和输出的复杂度是 O(N \log(k))O(Nlog(k))。因此总复杂度为 O(N + N \log(k)) = O(N \log(k))O(N+Nlog(k))=O(Nlog(k))。
     * 空间复杂度：O(N)O(N)，存储哈希表的开销。
     * 注释
     * 根据复杂度分析，方法对于小 k 的情况是很优的。但是如果 k 值很大，我们可以将算法改成删除频率最低的若干个元素。
     */

    public List<Integer> topK(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            if (pq.size() < k) {
                pq.add(val);
            } else if (val > pq.peek()) {
                pq.remove();
                pq.add(val);
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }


    /**
     * 面试题40. 最小的k个数
     * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
     * 面试题 17.14. 最小K个数
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     *
     * 示例：
     *
     * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
     * 输出： [1,2,3,4]
     * @param arr
     * @param k
     * @return
     */
    public static int[] smallestK(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        //最小的K个元素需要建立最大堆 peek堆顶元素为最大值
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for(int num : arr) {
            if (pq.size() < k) {
                pq.add(num);
            } else if (pq.peek() > num) {
                pq.remove();
                pq.add(num);
            }
        }
        int[] res = new int[pq.size()];
        int i = 0;
        while(!pq.isEmpty()) {
            res[i++] = pq.remove();
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/smallest-k-lcci/
     * 面试题 17.14. 最小K个数
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * 示例：
     * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
     * 输出： [1,2,3,4]
     * 提示：
     * 0 <= len(arr) <= 100000
     * 0 <= k <= min(100000, len(arr))
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK1714(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k) {
                pq.offer(arr[i]);
            } else if (pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            ans[i++] = pq.poll();
        }
        return ans;
    }


}
