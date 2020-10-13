package com.sky.hiwise.algorithms.leetcode.array;

import org.springframework.util.StringUtils;

import java.util.HashMap;

public class TwoSum167 {

    public static void main(String[] args) {
        TwoSum167 ts = new TwoSum167();
//        int []numbers = {2, 7, 11, 15};
//        int target = 9;
//        ArrayUtil.print(ts.twoSum(numbers, target));
//        String s = "A man, a plan, a canal: Panama";
//        System.out.println(ts.isPalindrome(s));

        String test = "  hello world!  ";
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     说明:
     ● 返回的下标值（index1 和 index2）不是从零开始的。
     ● 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     示例:
     输入: numbers = [2, 7, 11, 15], target = 9
     输出: [1,2]
     解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        int res[] = new int[2];
        while(start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                res[0] = start + 1;
                res[1] = end + 1;
                return res;
            } else if (sum > target) {
                end --;
            } else if (sum < target) {
                start ++;
            }
        }
        return res;
    }

    /**
     * 1. 两数之和
     给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
     示例:给定 nums = [2, 7, 11, 15], target = 9
     因为 nums[0] + nums[1] = 2 + 7 = 9  所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        int []res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (map.containsKey(target - nums[j]) && map.get(target - nums[j]) != j) {
                res[0] = j;
                res[1] = map.get(target - nums[j]);
            }
        }
        return res;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int [] res = new int[2];
        if(numbers==null||numbers.length<2)
            return res;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++){
            if(!map.containsKey(target-numbers[i])){
                map.put(numbers[i],i);
            }else{
                res[0]= map.get(target-numbers[i]);
                res[1]= i;
                break;
            }
        }
        return res;
    }

    /**
     * 344. 反转字符串
     请编写一个函数，其功能是将输入的字符串反转过来。
     示例：输入：s = "hello" 返回："olleh"
     * @param s
     * @return
     */
    public String reverseString(String s) {
//        StringBuilder sb = new StringBuilder(s);
//        return sb.reverse().toString();

        if (s.isEmpty()) {
            return s;
        }
        int start = 0;
        int end = s.length() - 1;
        char[] word = s.toCharArray();
        while (start < end) {
            char temp = word[start];
            word[start] = word[end];
            word[end] = temp;
            start ++;
            end --;
        }
        return new String(word);
    }

    /**
     * 345. 反转字符串中的元音字母
     编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     示例 1：给定 s = "hello", 返回 "holle".
     示例 2：给定 s = "leetcode", 返回 "leotcede".
     注意:元音字母不包括 "y".
     * @param s
     * @return
     */
    public String reverseVowels(String s) {

        if (s.isEmpty()) {
            return s;
        }

        int start = 0;
        int end = s.length() - 1;
        char[] word = s.toCharArray();
        while (start < end) {
            while (start < end && !isVowel(word[start])) {
                start ++;
            }
            while (start < end && !isVowel(word[end])) {
                end --;
            }

            if (start < end) {
                char temp = word[start];
                word[start] = word[end];
                word[end] = temp;
            }
            start ++;
            end --;
        }
        return new String(word);
    }

    /**
     * 541. 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * 示例:
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacdfeg"
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] sa = s.toCharArray();
        for (int start = 0; start < sa.length; start += 2*k) {
            int i = start, j = Math.min(start + k - 1, sa.length - 1);
            while(i < j) {
                char temp = sa[i];
                sa[i++] = sa[j];
                sa[j--] = temp;
            }
        }
        return new String(sa);
    }

    private boolean isVowel(char c) {
        return c == 'a' | c == 'e' | c == 'i' | c == 'o' | c == 'u' |
                c == 'A' | c == 'E' | c == 'I' | c == 'O' | c == 'U';
    }
}
