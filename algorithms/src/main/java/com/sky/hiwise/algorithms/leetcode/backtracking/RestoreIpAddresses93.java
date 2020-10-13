package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIpAddresses93 {

    /**
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * 示例:
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     * @param s
     * @param pos
     * @param cur
     * @param ans
     */

    // cur : 当前答案，以 String List的形式，最后再join成String形式 例如 [[255],[255],[111],[35]] -> 255.255.111.35
    // pos, 当前扫描到的s的位置， ans最终答案
    private void backtracking(String s, int pos, List<String> cur, List<String> ans) {
        if (cur.size() >= 4) {
            if (pos == s.length()) ans.add(String.join(".", cur));
            return;
        }
        // 分割得到ip地址的一段后，下一段只能在长度1-3范围内选择
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) break;
            String segment = s.substring(pos, pos+i);
            System.out.println("pos:" + pos + ",i:" + i + ",segment:" + segment);
            // 剪枝条件：不能以0开头，不能大于255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255)) continue;
            cur.add(segment);
            // 注意此处传的参数
            backtracking(s, pos+i, cur, ans);
            System.out.println("cur:" + cur);
            cur.remove(cur.size()-1);
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println((new RestoreIpAddresses93()).restoreIpAddresses("25525511135"));
    }


    /**
     * 方法一 ： 回溯(DFS)
     * 这是一个回溯函数backtrack(prev_pos = -1, dots = 3) 的算法，该函数使用上一个放置的点 prev_pos
     * 和待放置点的数量 dots 两个参数 :
     * 遍历三个有效位置curr_pos 以放置点。
     * 检查从上一个点到现在点中间的部分是否有效 :
     * 是 :
     * 放置该点。
     * 检查全部 3个点是否放好:
     * 是 :
     * 将结果添加到输出列表中。
     * 否 :
     * 继续放下一个点 backtrack(curr_pos, dots - 1)。
     * 回溯，移除最后一个点。
     */
    int n;
    String s;
    LinkedList<String> segments = new LinkedList<String>();
    ArrayList<String> output = new ArrayList<String>();

    public boolean valid(String segment) {
    /*
    Check if the current segment is valid :
    1. less or equal to 255
    2. the first character could be '0'
    only if the segment is equal to '0'
    */
        int m = segment.length();
        if (m > 3)
            return false;
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    public void update_output(int curr_pos) {
    /*
    Append the current list of segments
    to the list of solutions
    */
        String segment = s.substring(curr_pos + 1, n);
        if (valid(segment)) {
            segments.add(segment);
            output.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    public void backtrack(int prev_pos, int dots) {
    /*
    prev_pos : the position of the previously placed dot
    dots : number of dots to place
    */
        // The current dot curr_pos could be placed
        // in a range from prev_pos + 1 to prev_pos + 4.
        // The dot couldn't be placed
        // after the last character in the string.
        int max_pos = Math.min(n - 1, prev_pos + 4);
        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
            String segment = s.substring(prev_pos + 1, curr_pos + 1);
            if (valid(segment)) {
                segments.add(segment);  // place dot
                if (dots - 1 == 0)      // if all 3 dots are placed
                    update_output(curr_pos);  // add the solution to output
                else
                    backtrack(curr_pos, dots - 1);  // continue to place dots
                segments.removeLast();  // remove the last placed dot
            }
        }
    }

    public List<String> restoreIpAddresses2(String s) {
        n = s.length();
        this.s = s;
        backtrack(-1, 3);
        return output;
    }

}
