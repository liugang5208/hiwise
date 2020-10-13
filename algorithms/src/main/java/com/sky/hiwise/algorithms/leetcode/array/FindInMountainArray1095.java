package com.sky.hiwise.algorithms.leetcode.array;

public class FindInMountainArray1095 {

    /**
     * 1095. 山脉数组中查找目标值
     * （这是一个 交互式问题 ）
     * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
     *
     * 如果不存在这样的下标 index，就请返回 -1。
     * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
     * 首先，A.length >= 3
     * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
     * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
     * MountainArray.length() - 会返回该数组的长度
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int left = 0, right = mountainArr.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int topIndex = left;
        int ans = binarySearch(mountainArr, 0, topIndex, target, true);
        if (ans != -1) {
            return ans;
        }
        return binarySearch(mountainArr, topIndex, mountainArr.length() - 1, target, false);
    }

    public int binarySearch(MountainArray mountainArr, int left, int right, int target, boolean order) {
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) < target) {
                if (order) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else  {
                if (order) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}

class MountainArrayImpl implements MountainArray {
    private int[] arr;
    private int size;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
        this.size = this.arr.length;
    }

    @Override
    public int get(int index) {
        return this.arr[index];
    }

    @Override
    public int length() {
        return this.size;
    }
}

interface MountainArray {
    public int get(int index);
    public int length();
}
