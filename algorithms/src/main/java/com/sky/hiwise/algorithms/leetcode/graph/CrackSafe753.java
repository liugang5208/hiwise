package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.HashSet;
import java.util.Set;

public class CrackSafe753 {
    /**
     * 753. 破解保险箱
     * 有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。
     * 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。
     * 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符.
     * 请返回一个能打开保险箱的最短字符串。
     * 示例1:
     * 输入: n = 1, k = 2
     * 输出: "01"
     * 说明: "10"也可以打开保险箱。
     * 示例2:
     * 输入: n = 2, k = 2
     * 输出: "00110"
     * 说明: "01100", "10011", "11001" 也能打开保险箱。
     *
     * 如何在一个最短的串内枚举所有的n位k进制数排列
     * 其实这个问题是一个在数学中早已被研究透彻的问题了。
     * 这种序列称之为 de Bruijn序列。
     */
    Set<String> seen = new HashSet<>();
    StringBuilder ans = new StringBuilder();
    public int K;
    public String crackSafe(int n, int k) {
        if (n == 0 || k == 0) {
            return "";
        }
        K = k;
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            start.append("0");
        }
        dfs(start.toString());
        ans.append(start);
        return ans.toString();
    }

    public void dfs(String start) {
        for (int x = 0; x < K; x++) {
            String nei = start + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1));
                ans.append(x);
            }
        }
    }

    /**
     * 方法一：Hierholzer 算法
     * Hierholzer 算法可以在一个欧拉图中找出欧拉回路。
     * 我们将所有的 k - 1 位数作为节点，共有 k^{n - 1}k
     *   个节点。每个节点有 k 条入边和出边，如果当前节点对应的数为 a_1a_2\cdots a_ka
     *  ，那么它的第 k 条出边连向 a_2\cdots a_k ka
     *  k，第 k 条入边由 k a_1a_2\cdots a_{k-1}ka
     *   连向它，这样我们从一个节点顺着一条边（编号为 x）走到另一个节点，就相当于输入了数字 x。我们的目标是要经过所有的边，这是因为每一个节点以及它们的出边都对应了 k 个数，例如 k = 4, n = 3 时，节点分别为 00, 01, 02, ..., 32, 33，边分别为 0, 1, 2, 3，那么 00 和它的出边对应了 000, 001, 002, 003，32 和它的出边对应了 320, 321, 322, 323。一共有 k^{n-1} * k = k^nk
     * n−1
     *  ∗k=k
     * n
     *   个数，即为所有可能的密码。
     * 由于这个图的每个节点都有 k 条入边和出边，因此它一定存在一个欧拉回路，即可以从任意一个节点开始，一次性不重复地走完所有的边且回到该节点。因此我们可以用 Hierholzer 算法找出这条欧拉回路，设开始的节点对应的数位 init，欧拉回路中每条边的编号为 l1, l2, l3, ...，那么最终的字符串即为 init l1 l2 l3 ...。Hierholzer 算法如下：
     * 我们从任意节点 u 开始，任意地经过未经过的边，直到我们“无路可走”。可以发现，我们最终一定会停在节点 u，这是因为所有节点的入度和出度都相等。
     * 我们得到了一条从 u 开始到 u 结束的回路，这条回路上仍然有些节点有未经过的出边。我么从某个这样的节点 v 开始，把 v 看成 u，继续得到一条从 v 开始到 v 结束的回路，再嵌入之前的回路中，即 u -> ... -> v -> ... -> u 变为 u -> ... -> v -> ... -> v -> ... -> u。以此类推，直到没有未经过的边，此时我们就找到了一条欧拉回路。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/cracking-the-safe/solution/po-jie-bao-xian-xiang-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public static Integer a, b;
    public static void main(String[] args) {
        a = 1;
        b = 2;
        swap(a,b);
        System.out.println(a + " :" + b);

    }

    public static void swap(Integer A, Integer B) {
        Integer temp = A;
        A = B;
        B = temp;

    }

}
