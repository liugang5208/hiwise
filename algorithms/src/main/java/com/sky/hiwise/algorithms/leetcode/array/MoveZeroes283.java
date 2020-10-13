package com.sky.hiwise.algorithms.leetcode.array;

public class MoveZeroes283 {

    public static void main(String[] args) {

        int []nums = {0, 1, 0, 3, 12};
        MoveZeroes283 mz = new MoveZeroes283();
        mz.moveZeroes(nums);

    }

    /**
     * 283. 移动零
     * 给定一个数组 nums, 编写一个函数将所有 0 移动到它的末尾，同时保持非零元素的相对顺序。
     例如， 定义 nums = [0, 1, 0, 3, 12]，调用函数之后， nums 应为 [1, 3, 12, 0, 0]。
     注意事项:
     1. 必须在原数组上操作，不要为一个新数组分配额外空间。
     2. 尽量减少操作总数。
     考虑所有元素均为非0元素的情况该如何处理？
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != 0) {
//                int temp = nums[index];
//                nums[index++] = nums[i];
//                nums[i] = temp;
//            }
            //优化
            if (nums[i] != 0) {
                if (index != i) {
                    int temp = nums[index];
                    nums[index++] = nums[i];
                    nums[i] = temp;
                } else {
                    index++;
                }
            }
        }
    }

}
