package com.sky.hiwise.algorithms.leetcode.interview;

public class ColorFill0810 {

    /**
     * 面试题 08.10. 颜色填充
     * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
     * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的横坐标为 sr 纵坐标为 sc。需要填充的新颜色为 newColor 。
     * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
     * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
     * 示例：
     * 输入：
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * 输出：[[2,2,2],[2,2,0],[2,0,1]]
     * 解释:
     * 初始坐标点位于图像的正中间，坐标 (sr,sc)=(1,1) 。
     * 初始坐标点周围区域上所有符合条件的像素点的颜色都被更改成 2 。
     * 注意，右下角的像素没有更改为 2 ，因为它不属于初始坐标点的周围区域。
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    private int R, C, color, origin;
    private int[][] image;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        R = image.length;
        C = image[0].length;
        color = newColor;
        this.image = image;
        visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (r == sr && c == sc) {
                    origin = image[r][c];
                    dfs(r, c);
                }
            }
        }
        return image;
    }

    public void dfs(int x, int y) {
        image[x][y] = color;
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int newX = x + dirs[d][0];
            int newY = y + dirs[d][1];
            if (inArea(newX, newY) && image[newX][newY] == origin && !visited[newX][newY]) {
                dfs(newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
