package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PondSizes1619 {

    /**
     * 面试题 16.19. 水域大小
     * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
     * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
     * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     * 示例：
     * 输入：
     * [
     *   [0,2,1,0],
     *   [0,1,0,1],
     *   [1,1,0,1],
     *   [0,1,0,1]
     * ]
     * 输出： [1,2,4]
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private int R;
    private int C;
    private int[][] land;
    private boolean[][] visited;
    public int[] pondSizes(int[][] land) {
        R = land.length;
        C = land[0].length;
        visited = new boolean[R][C];
        List<Integer> ans = new ArrayList<>();
        this.land = land;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (land[i][j] == 0 && !visited[i][j])  {
                    ans.add(dfs(i, j));
                }
            }
        }
        Collections.sort(ans);
        int len = ans.size();
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int num = 1;
        for(int d = 0; d < 8; d++) {
            int newX = x + dirs[d][0];
            int newY = y + dirs[d][1];
            if (inArea(newX, newY) && !visited[newX][newY] && land[newX][newY] == 0) {
                num += dfs(newX, newY);
            }
        }
        return num;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
