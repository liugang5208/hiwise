package com.sky.hiwise.algorithms.leetcode.math;

public class ExcelSheetColumnTitle168 {
    /**
     * 168. Excel表列名称
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     * 例如，
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     * 示例 1:
     *
     * 输入: 1
     * 输出: "A"
     * 示例 2:
     *
     * 输入: 28
     * 输出: "AB"
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
