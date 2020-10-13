package com.sky.hiwise.algorithms.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammaticNumber247 {
    /**
     * [LeetCode] Strobogrammatic Number II 对称数之二
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     * Find all strobogrammatic numbers that are of length = n.
     * For example,
     * Given n = 2, return ["11","69","88","96"].
     * Hint:
     * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
     */
    /**
     * 这道题是之前那道Strobogrammatic Number的拓展，那道题让我们判断一个数是否是对称数，
     * 而这道题让我们找出长度为n的所有的对称数，我们肯定不能一个数一个数的来判断，那样太不高效了，
     * 而且OJ肯定也不会答应。
     * 题目中给了提示说可以用递归来做，而且提示了递归调用n-2，而不是n-1。
     * 我们先来列举一下n为0,1,2,3,4的情况：
     * n = 0:   none
     * n = 1:   0, 1, 8
     * n = 2:   11, 69, 88, 96
     * n = 3:   101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986
     * n = 4:   1001, 6009, 8008, 9006, 1111, 6119, 8118, 9116, 1691, 6699, 8698, 9696, 1881, 6889, 8888, 9886, 1961, 6969, 8968, 9966
     * 我们注意观察n=0和n=2，可以发现后者是在前者的基础上，每个数字的左右增加了[1 1], [6 9], [8 8], [9 6]，
     * 看n=1和n=3更加明显，在0的左右增加[1 1]，变成了101, 在0的左右增加[6 9]，变成了609, 在0的左右增加[8 8]，变成了808, 在0的左右增加[9 6]，变成了906,
     * 然后在分别在1和8的左右两边加那四组数，我们实际上是从m=0层开始，一层一层往上加的，
     * 需要注意的是当加到了n层的时候，左右两边不能加[0 0]，因为0不能出现在两位数及多位数的开头，在中间递归的过程中，需要有在数字左右两边各加上0的那种情况，参见代码如下：
     */

    public static List<String> findStrobogrammatic(int n) {
        return find(n, n);
    }

    private static List<String> find(int m, int n) {
        if (m == 0) {
            return Arrays.asList("");
        }
        if (m == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> temps = find(m - 2, n);
        List<String> res = new ArrayList<>();
        for(String str : temps) {
            if (m != n) {
                res.add("0" + str + "0");
            }
            res.add("1" + str + "1");
            res.add("8" + str + "8");
            res.add("6" + str + "9");
            res.add("9" + str + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> list = findStrobogrammatic(4);
        for(String str : list){
            System.out.println(str);
        }
    }
}
