package com.sky.hiwise.algorithms.leetcode.search;


import com.sky.hiwise.algorithms.leetcode.array.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class

Intersection349 {

    public static void main(String[] args) {
        int []nums1 = {1,2,2,1};
        int []nums2 = {2};

        Intersection349 intersection349 = new Intersection349();
        ArrayUtil.print(intersection349.intersection1(nums1, nums2));
        String str1 = "abcdk";
        String str2 = "abcdefghijk";
        System.out.println(intersection349.isInclude(str1,str2));
    }


    /**
     * 349. 两个数组的交集
     给定两个数组，写一个函数来计算它们的交集。
     例子:
     给定 num1= [1, 2, 2, 1], nums2 = [2, 2], 返回 [2].
     提示:
     ● 每个在结果中的元素必定是唯一的。
     ● 我们可以不考虑输出结果的顺序。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set1 = new HashSet<>();
        for(int i: nums1){
            set1.add(i);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for(int i: nums2){
            if(set1.contains(i)){
                set2.add(i);
            }
        }

        int[] result = new int[set2.size()];
        int i=0;
        for(int n : set2){
            result[i++] = n;
        }

        return result;
    }


    public int[] intersection1(int[] nums1, int[] nums2) {

        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        int []res = new int[Math.min(nums1.length, nums2.length)];
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!set.contains(nums1[i])) {
                set.add(nums1[i]);
            }
        }

        for (int j = 0; j < nums2.length; j++) {
            if (set.contains(nums2[j])) {
                res[count++] = nums2[j];
                set.remove(nums2[j]);
            }
        }

        int []results = new int[count];
        for (int i = 0; i < count; i++) {
            results[i] = res[i];
        }

        return results;
    }


    /**
     * 两个排序数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) {
            return null;
        }
        int len = nums1.length < nums2.length ? nums1.length : nums2.length;
        int[] result = new int[len];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int t = 0;
        while(i < nums1.length && j < nums2.length) {

            while(i+1 < nums1.length && nums1[i] == nums1[i+1])
                i++;
            while(j+1 < nums2.length && nums2[j] == nums2[j+1])
                j++;

            if(nums1[i] == nums2[j]) {
                result[t++] = nums1[i];
                i++;
                j++;
            } else if(nums1[i] < nums2[j]) {
                i++;
            }else {
                j++;
            }
        }
        result = Arrays.copyOf(result, t);
        return result;
    }

    /**
     * 392. 判断子序列
     给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
     字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     示例 1:
     s = "abc", t = "ahbgdc"
     返回 true.
     示例 2:
     s = "axc", t = "ahbgdc"
     返回 false.
     后续挑战 :
     如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
//        char []ss = s.toCharArray();
//        char []tt = t.toCharArray();
//        int i = 0, j = 0;
//        while(i<ss.length && j<tt.length){
//            if(ss[i]  == tt[j]){
//                i++;
//            }
//            j++;
//        }
//        return i==s.length();
        int pos = -1;
        for (char c : s.toCharArray()) {
            pos = t.indexOf(c, pos + 1);
            if (pos == -1) return false;
        }
        return true;
    }

    /**
     * 同字符串是否包含
     * str1 =
     */
    public boolean isInclude(String s, String t) {
        int num = 0;
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for(Character c : ss) {
            set.add(c);
            num++;
        }

        for (Character character : tt) {
            if (set.contains(character)) {
                //set.remove(character);
                num--;
            }
            if (num == 0) {
                break;
            }
        }

        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 350. 两个数组的交集 II
     题目描述提示帮助提交记录社区讨论阅读解答
     给定两个数组，写一个方法来计算它们的交集。
     例如:
     给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
     注意：
     输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     我们可以不考虑输出结果的顺序。
     跟进:
     如果给定的数组已经排好序呢？你将如何优化你的算法？
     如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int []res = new int[Math.min(nums1.length, nums2.length)];
        HashMap<Integer, Integer> map1 = new HashMap<>();

        for (int i : nums1) {
            if (map1.containsKey(i)) {
                map1.put(i, map1.get(i) + 1);
            } else {
                map1.put(i, 1);
            }
        }

        int index = 0;
        for (int j : nums2) {
            Integer count = map1.get(j);
            if (map1.containsKey(j) && count > 0) {
                res[index++] = j;
                if (--count == 0) {
                    map1.remove(j);
                } else {
                    map1.put(j, count);
                }
            }
        }

        return Arrays.copyOf(res, index);
    }

    /**
     * 我的方法：hash，扫一遍nums1和nums2。
     复杂度：O(m+n)
     AC了，但速度不快。排在前面的似乎先排序，再扫数组。
     2.如果nums1小很多，
     //用hash，把nums2扫一遍。
     排序，再扫描。
     不排序，二分搜索。
     3.用hash，分段加载nums2扫描。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 > len2 ? len2 : len1;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] nums = new int[len];
        int k = 0;
        int curr1, curr2 = 0;
        for(int i = 0, j = 0; i < len1 && j < len2;) {
            curr1 = nums1[i];
            curr2 = nums2[j];
            if(curr1 == curr2) {
                nums[k] = curr1;
                k += 1;
                i += 1;
                j += 1;
            } else if(curr1 < curr2) {
                i += 1;
            } else {
                j += 1;
            }
        }

        return Arrays.copyOfRange(nums, 0, k);
    }

    /**
     * 242. 有效的字母异位词
     给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     例如，
     s = "anagram"，t = "nagaram"，返回 true     s = "rat"，t = "car"，返回 false
     注意:假定字符串只包含小写字母。
     提升难度:输入的字符串包含 unicode 字符怎么办？你能能否调整你的解法来适应这种情况？
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i : ss) {
            if (map1.containsKey(i)) {
                map1.put(i, map1.get(i) + 1);
            } else {
                map1.put(i, 1);
            }
        }

        for (int j : tt) {
            Integer count = map1.get(j);
            if (map1.containsKey(j) && count > 0) {
                if (--count == 0) {
                    map1.remove(j);
                } else {
                    map1.put(j, count);
                }
            }
        }
        return map1.isEmpty();
    }

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] sIntArray = new int[26];
        int[] tIntArray = new int[26];

        for (char c : s.toCharArray()) {
            sIntArray[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            tIntArray[c - 'a']++;
        }

        for (int i = 0; i < tIntArray.length; i++) {
            if(tIntArray[i] != sIntArray[i]) {
                return false;
            }
        }
        return true;
    }
}
