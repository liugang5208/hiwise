package com.sky.hiwise.algorithms.leetcode.math;

public class TwoIntegerSum371 {
    /**
     * 371. 两整数之和
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
     * 示例 1：
     * 输入：a = 1, b = 2
     * 输出：3
     * 示例 2：
     * 输入：a = 2, b = 3
     * 输出：5
     */
    public int getSum(int a, int b) {
        while(b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
    /**
     * 方法一：位运算
     * 预备知识
     * 有符号整数通常用补码来表示和存储，补码具有如下特征：
     * 正整数的补码与原码相同；负整数的补码为其原码除符号位外的所有位取反后加 11。
     * 可以将减法运算转化为补码的加法运算来实现。
     * 符号位与数值位可以一起参与运算。
     * 思路和算法
     * 虽然题目只要求了不能使用运算符 \texttt{+}+ 和 \texttt{-}-，但是原则上来说也不宜使用类似的运算符 \texttt{+=}+= 和 \texttt{-=}-= 以及 \texttt{sum}sum 等方法。于是，我们使用位运算来处理这个问题。
     * 首先，考虑两个二进制位相加的四种情况如下：
     * 0 + 0 = 0
     * 0 + 1 = 1
     * 1 + 0 = 1
     * 1 + 1 = 0 (进位)
     * 可以发现，对于整数 aa 和 bb：
     * 在不考虑进位的情况下，其无进位加法结果为 \texttt{a} \oplus \texttt{b}a⊕b。
     * 而所有需要进位的位为 \texttt{a \& b}a & b，进位后的进位结果为 \texttt{(a \& b) << 1}(a & b) << 1。
     * 于是，我们可以将整数 aa 和 bb 的和，拆分为 aa 和 bb 的无进位加法结果与进位结果的和。因为每一次拆分都可以让需要进位的最低位至少左移一位，又因为 aa 和 bb 可以取到负数，所以我们最多需要 \log (max\_int)log(max_int) 次拆分即可完成运算。
     * 因为有符号整数用补码来表示，所以以上算法也可以推广到 00 和负数
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sum-of-two-integers/solution/liang-zheng-shu-zhi-he-by-leetcode-solut-c1s3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
