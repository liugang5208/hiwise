package com.sky.hiwise.algorithms.leetcode.array;

public class RemoveDuplicates26 {
    public static void main(String[] args) {

        RemoveDuplicates26 rd = new RemoveDuplicates26();
        int []num = {0,1,1,2,2,3,3,4};
        System.out.println(rd.removeDuplicates(num));

    }

    /**
     * 第 26 题
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     示例 1:
     给定数组 nums = [1,1,2],
     函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;

        //判断无输入
        int number = 0;//标记计数
        for (int i = 0; i < nums.length ; i++) {
            if ( nums[i] != nums[number] ) {
                number ++;
                if (number != i) {
                    nums[number] = nums[i];
                }
            }
        }
        number += 1; //标记+1即为数字个数
        return number;
    }

    /**
     * 80. 删除排序数组中的重复项 II
     给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     示例 1: 给定 nums = [1,1,1,2,2,3],
     函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     你不需要考虑数组中超出新长度后面的元素。
     示例 2:给定 nums = [0,0,1,1,1,1,2,3,3],
     函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
     你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return
     */
    public int removeDuplicates80(int[] nums) {

        if (nums.length <= 2)
            return nums.length;
        boolean isTwice = false;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1 && nums[i+1] == nums[i]) {
                if (!isTwice) {
                    isTwice = true;
                    nums[index++] = nums[i];
                }
            } else {
                isTwice = false;
                nums[index++] = nums[i];
            }
        }
        return index;

//        int index = 2;
//        for(int i=2; i < nums.length; i++){
//            if(nums[index - 2] != nums[i]){
//                nums[index++] = nums[i];
//            }
//        }
//        return index;
    }

}
