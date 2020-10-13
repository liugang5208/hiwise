package com.sky.hiwise.algorithms.leetcode.dynamic;

public class InterleavingString97 {

    /**
     * 97. 交错字符串
     * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
     * 示例 1：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * 示例 2：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if(m+n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m && s1.charAt(i-1) == s3.charAt(i-1); i++) {
            dp[i][0] = true;
        }
        for (int j = 1 ; j <= n && s2.charAt(j-1) == s3.charAt(j-1); j++) {
            dp[0][j] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j]= (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[m][n];
    }

    /**
     * 不知道大家对不同路径的题还有没有印象，本题也可以利用其思想求解：
     * target 的每个字符都是从 s1（向下）或者 s2（向右）拿到的，所以只要判断是否存在这条 target 路径即可；
     *
     * 于是可定义 boolean[][] dp ，dp[i][j] 代表 s1 前 i 个字符与 s2 前 j 个字符拼接成 s3 的 i+j 字符，也就是存在目标路径能够到达 i ,j ；
     * 状态方程：
     *
     * 边界 1：dp[0][0] = true;
     * 边界 2：if i=0 : dp[0]dp[j] = s2[0-j) equals s3[0,j) 遇到 false 后面可以直接省略
     * 边界 3：if j=0 : dp[i]dp[0] = s1[0-i) equals s3[0,i) 遇到 false 后面可以直接省略
     *
     * 其他情况，到达（i，j）可能由（i-1,j）点向下一步，选择 s1[i-1] 到达；也可能由 （i,j-1） 点向右一步，选择 s2[j-1] 到达；
     * dp[i,j] = (dp[i-1][j] &&s3[i+j-1] == s1[i-1]) || (dp[i][j-1] && s3[i+j-1] == s2[j-1])
     *
     * 作者：gousiqi
     * 链接：https://leetcode-cn.com/problems/interleaving-string/solution/lei-si-lu-jing-wen-ti-zhao-zhun-zhuang-tai-fang-ch/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
