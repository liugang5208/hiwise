package com.sky.hiwise.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;

public class ContainsDuplicate217 {

    public static void main(String[] args) {
        ContainsDuplicate217 cd = new ContainsDuplicate217();
        int []nums = {0,1,1,2,2,3,3,4};
        System.out.println(cd.containsDuplicate(nums));
    }

    /**
     * 217. 存在重复
     给定一个整数数组，判断是否存在重复元素。
     如果任何值在数组中出现至少两次，函数应该返回 true。如果每个元素都不相同，则返回 false。
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }

        //排序后查看相邻元素
//        Arrays.sort(nums);
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] == nums[i - 1]) {
//                return true;
//            }
//        }

        //还可以用双指针法,一个一个对比
//        for(int i = 0; i < nums.length; i++){
//            for(int j = i+1 ;j < nums.length; j++){
//                if(nums[i] == nums[j])
//                    return true;
//            }
//        }

        //HashMap 原理一致
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (hashMap.containsKey(nums[i])) {
//                return true;
//            } else {
//                hashMap.put(nums[i], i);
//            }
//        }
        return false;
    }

    /**
     * 219. 存在重复 II
     给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
     使 nums [i] = nums [j]，并且 i 和 j 的绝对差值最大为 k。
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;
            else
                map.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;
            else
                map.put(nums[i], i);
        }
        return false;
    }

}
