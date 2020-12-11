package com.sky.hiwise.algorithms.leetcode.greedy;

public class TaskScheduler621 {

    /**
     * 621. 任务调度器
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的 最短时间 。
     * 示例 1：
     * 输入：tasks = ["A","A","A","B","B","B"], n = 2
     * 输出：8
     * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
     * 示例 2：
     * 输入：tasks = ["A","A","A","B","B","B"], n = 0
     * 输出：6
     * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
     * ["A","A","A","B","B","B"]
     * ["A","B","A","B","A","B"]
     * ["B","B","B","A","A","A"]
     * ...
     * 诸如此类
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] taskCount = new int[26];
        //字符最多的 计数值
        int maxC = 0;
        //字符最多的任务个数
        int maxCountTask = 0;
        for (char t : tasks) {
            taskCount[t - 'a'] ++;
            maxC = Math.max(taskCount[t - 'a'], maxC);
        }

        for (int i = 0; i < 26; i++) {
            if (taskCount[i] == maxC) {
                maxCountTask++;
            }
        }
        return Math.max(tasks.length, (maxC - 1) * (n + 1) + maxCountTask);
    }
    /**
     * 建立大小为 n+1 的桶子，个数为任务数量最多的那个任务，比如下图，等待时间 n=2，A 任务个数 6 个，我们建立 6 桶子，每个容量为 3：
     * 我们可以把一个桶子看作一轮任务
     *可以看到 C 其实并没有对总体时间产生影响，因为它被安排在了其他任务的冷却期间；而 B 和 A 数量相同，这会导致最后一个桶子中，我们需要多执行一次 B 任务，现在我们需要的时间是 (6-1)*3+2=17
     * 前面两种情况，总结起来：总排队时间 = (桶个数 - 1) * (n + 1) + 最后一桶的任务数
     * 这样剩下就很好处理了，我们只需要算两个数：
     *
     * 记录最大任务数量 N，看一下任务数量并列最多的任务有多少个，即最后一个桶子的任务数 X，计算 NUM1=(N-1)*(n+1)+x
     * NUM2=tasks.size()
     * 输出其中较大值即可
     * 因为存在空闲时间时肯定是 NUM1 大，不存在空闲时间时肯定是 NUM2>=NUM1
     *
     * 作者：popopop
     * 链接：https://leetcode-cn.com/problems/task-scheduler/solution/tong-zi-by-popopop/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
