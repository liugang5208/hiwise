package com.sky.hiwise.algorithms.leetcode.string;

public class ReverseLeftWords {
    /**
     *剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     */
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        int len = s.length();
        reverse(chars, n, len - 1);
        reverse(chars, 0, len - 1);
        reverse(chars, len - n, len - 1);
        return new String(chars);
    }

    public void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 方法二：三次翻转，原地 AC 本题。
     * 我们把需要向左旋转的字符串部分叫做 s1，剩余不用处理的部分叫做 s2，所以有： s = s1 + s2。假设 s = "abcdefg", k = 2。那么 s1 = "ab"，s2 = "cdefg"。
     * 我们先翻转 s2，则有：s2 = "gfedc"，s = "abgfedc"。
     * 再翻转整个 s，则有：s = "cdefgba"。注意，此时 s1 已经在 s 的尾部了，只不过其内部顺序是反的，所以我们还需要对 s1 进行一次翻转。
     * 再翻转 s1，则有：s1 = "ab"，s = "cdefgab"。
     * 返回 s 即可。所以三次翻转，本题就能完成。
     * 作者：superkakayong
     * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/solution/zi-jie-ti-ku-jian-58-ii-jian-dan-zuo-xua-vkmb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println((new ReverseLeftWords()).reverseLeftWords(s, 2));
    }
}
