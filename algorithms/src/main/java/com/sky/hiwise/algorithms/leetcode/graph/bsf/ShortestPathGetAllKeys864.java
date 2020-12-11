package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathGetAllKeys864 {
    /**
     * 864. 获取所有钥匙的最短路径
     * 给定一个二维网格 grid。 "." 代表一个空房间， "#" 代表一堵墙， "@" 是起点，（"a", "b", ...）代表钥匙，（"A", "B", ...）代表锁。
     * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
     * 假设 K 为钥匙/锁的个数，且满足 1 <= K <= 6，字母表中的前 K 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
     * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
     * 示例 1：
     * 输入：["@.a.#","###.#","b.A.B"]
     * 输出：8
     * 示例 2：
     * 输入：["@..aA","..B#.","....b"]
     * 输出：6
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    public int shortestPathAllKeys(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        Node start = null;
        int keys = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0;j < C; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    start = new Node(i, j, 0, 0);
                } else if (c >= 'a' && c <= 'f') {
                    keys |= (1 << (c - 'a'));
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][] visited = new boolean[R][C][64];
        visited[start.x][start.y][0] = true;
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.key == keys) {
                return curr.dir;
            }
            for (int d = 0; d < 4; d++) {
                int nextX = curr.x + dirs[d][0], nextY = curr.y + dirs[d][1];
                if (inArea(nextX, nextY)
                        && grid[nextX].charAt(nextY) != '#'
                        && !visited[nextX][nextY][curr.key]
                ) {
                    char nextChar = grid[nextX].charAt(nextY);
                    if (nextChar >= 'A'
                            && nextChar <= 'F'
                            && (curr.key & (1 << (nextChar - 'A'))) == 0) {
                        continue;
                    }
                    Node nextNode = new Node(nextX, nextY, curr.key, curr.dir + 1);
                    if (nextChar >= 'a' && nextChar <= 'f') {
                        nextNode.key |= (1 << (nextChar - 'a'));
                    }
                    visited[nextX][nextY][nextNode.key] = true;
                    queue.add(nextNode);
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    /**
     * 解法一 广度优先搜索
     * 题目要求找到所有的钥匙。将给出的网格当作图，用图遍历的方法进行求解。这样的思路，本身不难想到。
     * 例如：有三把钥匙a,b,c
     * "@..c.",
     * "BD.#C",
     * "a.Adb"
     * 在这个例子中，要拿到钥匙a，要先拿到钥匙b和d。必然要拿到钥匙c。
     * 不论是bfs和dfs算法，都有节点去重的环节。一个节点遍历后，将其置为已遍历。之后就不会再次遍历此节点，保证算法能正确退出。
     * 考虑上面的例子。通过c和C，拿到b，d后。在二维图上，这一路径都被标志为已遍历。算法中，无法按原路返回了。即无法从b，d再退到c。
     * 但是要拿到a，必须 从b，d再退到c。
     * 这就产生矛盾了。要保证算法正确退出，必须要对已经过的节点，设置为已遍历。又要求从b，d按原路返回到c。
     * 解决办法。
     * 从c到b，d，是为了拿b，d。在拿到b，d前，是没有b，d的。
     * 当拿到b，d后，返回c时，就多了b和d的信息。
     * 因此，在二维图的基础上，加上已拥有的钥匙信息。将图从二维扩充到三维图，这就解决了上面的矛盾。
     * 尽管从b，d回到c，在二维图上是按原路返回，但在三维图上，却不是遍历已经遍历过的节点。
     * 三维图节点：
     * (x,y,k)——二维坐标和已拿到的钥匙信息。这是将钥匙对应到整数k的低6个bit。6个bit中，每个bit对应一个钥匙。
     * d——起始节点到(x,y,k)的最短距离。
     *     struct node{
     *         int x;
     *         int y;
     *         int k;
     *         int d;
     *         node(int _x=0,int _y=0,int _k=0,int _d=0):x(_x),y(_y),k(_k),d(_d){}
     *     };
     * 节点去重数组:vis[x][y][k]：1——(x,y,k)已遍历过， 0——(x,y,k)没有 遍历过 。
     * 初始节点：(x0,y0,0)—— x0,y0是@所在的行列。
     * 目标节点：k中包含所有钥匙的节点。
     * 在三维图上用bfs算法，求初始节点到目标节点的最短路径长度。
     * 作者：jason-2
     * 链接：https://leetcode-cn.com/problems/shortest-path-to-get-all-keys/solution/yin-shi-tu-shang-de-yan-du-you-xian-sou-suo-suan-f/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Node {
        public int x, y, key, dir;
        public Node(int x, int y, int key, int dir) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.dir = dir;
        }
    }
}
