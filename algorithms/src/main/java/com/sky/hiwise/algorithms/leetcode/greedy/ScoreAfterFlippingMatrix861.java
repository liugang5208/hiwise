package com.sky.hiwise.algorithms.leetcode.greedy;

public class ScoreAfterFlippingMatrix861 {

    /**
     * 861. 翻转矩阵后的得分
     * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     * 返回尽可能高的分数。
     * 示例：
     * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * 输出：39
     * 解释：
     * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
     * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;
        int ans = m * (1 << (n - 1));
        for (int j = 1; j < n; j++) {
            int oneCount = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 1) {
                    oneCount += A[i][j];
                } else {
                    oneCount += (1 - A[i][j]);
                }
            }
            int k = Math.max(oneCount, m - oneCount);
            ans += k * (1 << (n - j - 1));
        }
        return ans;
    }
    /**
     * 实际编写代码时，我们无需修改原矩阵，而是可以计算每一列对总分数的「贡献」，从而直接计算出最高的分数。假设矩阵共有 mm 行 nn 列，计算方法如下：
     * 对于最左边的列而言，由于最优情况下，它们的取值都为 11，因此每个元素对分数的贡献都为 2^{n-1}2 n−1
     *  ，总贡献为 m \times 2^{n-1}m×2
     * 对于第 jj 列（j>0j>0，此处规定最左边的列是第 00 列）而言，我们统计这一列 0,10,1 的数量，令其中的最大值为 kk，则 kk 是列翻转后的 11 的数量，
     * 该列的总贡献为 k \times 2^{n-j-1}k×2
     *  。需要注意的是，在统计 0,10,1 的数量的时候，要考虑最初进行的行反转。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/score-after-flipping-matrix/solution/fan-zhuan-ju-zhen-hou-de-de-fen-by-leetc-cxma/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int matrixScore1(int[][] A) {
        int m = A.length, n = A[0].length;
        int ans = 0;

        //翻转所有行，保证第一列全为1
        for (int i = 0; i < m; i++) {
            if (A[i][0] == 0) {
                //翻转该行
                for (int j = 0; j < n; j++) {
                    A[i][j] =  1 - A[i][j];
                }
            }
        }

        //从第二列开始，检查该列的1的数量是否大于等于0，如果不是，则翻转该列
        for (int j = 1; j < n; j++) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][j] == 1) {
                    count++;
                }
            }
            if (count < (m + 1) / 2) {
                for (int i = 0; i < m; i++) {
                    A[i][j] =  1 - A[i][j];
                }
            }
        }

        for (int j = 0; j < n; j++) {
            int temp = 1 << (n - j - 1);
            for (int i = 0; i < m; i++) {
                ans += temp * A[i][j];
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[][] A = new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println((new ScoreAfterFlippingMatrix861()).matrixScore(A));
    }



}
