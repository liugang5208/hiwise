package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.LinkedList;

public class RemoveKDigits402 {

    /**
     * 402. 移掉K位数字
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     * 注意:
     * num 的长度小于 10002 且 ≥ k。
     * num 不会包含任何前导零。
     * 示例 1 :
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char digit : num.toCharArray()) {
            while (stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k--;
            }
            stack.add(digit);
        }

        for (int i = 0; i < k; i++) {
            stack.removeLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (char digit : stack) {
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();

    }
    /**
     * 对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小，例如，对于 A = 1axxx，B = 1bxxx，如果 a > b 则 A > B。
     * 知道了这个以后，我们可以想到，在删除数字时应该从左向右迭代。
     * 确定了迭代的顺序以后，就必须制定如何消除数字的标准，以便获得最小值。
     * 上述的规则使得我们通过一个接一个的删除数字，逐步的接近最优解。
     * 这个问题可以用贪心算法来解决。上述规则阐明了我们如何接近最终答案的基本逻辑。
     * 一旦我们从序列中删除一个数字，剩下的数字就形成了一个新的问题，我们可以继续使用这个规则。
     * 我们会注意到，在某些情况下，规则对任意数字都不适用，即单调递增序列。在这种情况下，我们只需要删除末尾的数字来获得最小数。
     * 我们可以利用栈来实现上述算法，存储当前迭代数字之前的数字。
     * 对于每个数字，如果该数字小于栈顶部，即该数字的左邻居，则弹出堆栈，即删除左邻居。否则，我们把数字推到栈上。
     * 我们重复上述步骤（1），直到任何条件不再适用，例如堆栈为空（不再保留数字）。或者我们已经删除了 k 位数字。
     * 在上述主循环之外，我们需要处理一些情况，以使解决方案更加完整：
     * 当我们离开主循环时，我们删除了 m 个数字，这比要求的要少，即（m<k）。在极端情况下，我们不会删除循环中单调递增序列的任何数字，即 m==0。
     * 在这种情况下，我们只需要从序列尾部删除额外的 k-m 个数字。
     * 一旦我们从序列中删除 k 位数字，可能还有一些前导零。要格式化最后的数字，我们需要去掉前导零。
     * 我们最终可能会从序列中删除所有的数字。在这种情况下，我们应该返回零，而不是空字符串。
     */
}
