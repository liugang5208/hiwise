package com.sky.hiwise.algorithms.leetcode.array;

public class RotateMatrix0107 {

    /**
     * 面试题 01.07. 旋转矩阵
     * 给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。
     * 不占用额外内存空间能否做到？
     * 示例 1:
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 第一次对角线交换，反转每个元素的下标，第二次第N列与倒数第N列交换，交换列下标。
     * 至于为什么这么做，可以自行观察一下旋转前后矩阵的相关元素的下标变化。
     * 可以观察到，反转后 的元素的下标变成了原来的元素下标x,y下标交换后且y坐标变为length-N的形式。
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            for(int j = i + 1; j < rows; j++) {
                matrix[i][j] ^= matrix[j][i];
                matrix[j][i] ^= matrix[i][j];
                matrix[i][j] ^= matrix[j][i];
            }
        }

        for(int i = 0; i < rows / 2; i++) {
            for(int j = 0; j < rows; j++) {
                matrix[j][i] ^= matrix[j][rows - 1 - i];
                matrix[j][rows - 1 - i] ^= matrix[j][i];
                matrix[j][i] ^= matrix[j][rows - 1 - i];
            }
        }
    }

    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     * https://leetcode-cn.com/problems/rotate-image/
     */
}
