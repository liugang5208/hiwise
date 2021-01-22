package com.sky.hiwise.algorithms.leetcode.graph.toposort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class CourseSchedule207 {
    /**
     * 207. 课程表
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * 示例 1:
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     * 提示：
     * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 1 <= numCourses <= 10^5
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        TreeSet<Integer>[] adj = new TreeSet[numCourses];
        int[] indegree = new int[numCourses];
        Arrays.fill(indegree, 0);
        for (int[] pre : prerequisites) {
            if (adj[pre[1]] == null) {
                adj[pre[1]] = new TreeSet<>();
            }
            adj[pre[1]].add(pre[0]);
            indegree[pre[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            if (adj[curr] != null) {
                for (int next : adj[curr]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        return count == numCourses;
    }

    /**
     * 总结：拓扑排序问题
     * 根据依赖关系，构建邻接表、入度数组。
     * 选取入度为 0 的数据，根据邻接表，减小依赖它的数据的入度。
     * 找出入度变为 0 的数据，重复第 2 步。
     * 直至所有数据的入度为 0，得到排序，如果还有数据的入度不为 0，说明图中存在环。
     *
     * 这种叫 有向无环图，把一个 有向无环图 转成 线性的排序 就叫 拓扑排序。
     * 有向图有 入度 和 出度 的概念：
     * 如果存在一条有向边 A --> B，则这条边给 A 增加了 1 个出度，给 B 增加了 1 个入度。
     * 所以，顶点 0、1、2 的入度为 0。顶点 3、4、5 的入度为 2。
     * 每次只能选你能上的课
     * 每次只能选入度为 0 的课，因为它不依赖别的课，是当下你能上的课。
     * 假设选了 0，课 3 的先修课少了一门，入度由 2 变 1。
     * 接着选 1，导致课 3 的入度变 0，课 4 的入度由 2 变 1。
     * 接着选 2，导致课 4 的入度变 0。
     * 现在，课 3 和课 4 的入度为 0。继续选入度为 0 的课……直到选不到入度为 0 的课。
     * 这很像 BFS
     * 让入度为 0 的课入列，它们是能直接选的课。
     * 然后逐个出列，出列代表着课被选，需要减小相关课的入度。
     * 如果相关课的入度新变为 0，安排它入列、再出列……直到没有入度为 0 的课可入列。
     * BFS 前的准备工作
     * 每门课的入度需要被记录，我们关心入度值的变化。
     * 课程之间的依赖关系也要被记录，我们关心选当前课会减小哪些课的入度。
     * 因此我们需要选择合适的数据结构，去存这些数据：
     * 入度数组：课号 0 到 n - 1 作为索引，通过遍历先决条件表求出对应的初始入度。
     * 邻接表：用哈希表记录依赖关系（也可以用二维矩阵，但有点大）
     * key：课号
     * value：依赖这门课的后续课（数组）
     * 怎么判断能否修完所有课？
     * BFS 结束时，如果仍有课的入度不为 0，无法被选，完成不了所有课。否则，能找到一种顺序把所有课上完。
     * 或者：用一个变量 count 记录入列的顶点个数，最后判断 count 是否等于总课程数。
     * 作者：xiao_ben_zhu
     * 链接：https://leetcode-cn.com/problems/course-schedule/solution/bao-mu-shi-ti-jie-shou-ba-shou-da-tong-tuo-bu-pai-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        int n = 2;
        int[][] prerequisites = new int[][]{{1,0}};
        System.out.println(CourseSchedule207.canFinish(n, prerequisites));
    }
}
