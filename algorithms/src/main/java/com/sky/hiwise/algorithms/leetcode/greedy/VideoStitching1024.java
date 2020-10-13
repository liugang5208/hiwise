package com.sky.hiwise.algorithms.leetcode.greedy;

public class VideoStitching1024 {

    /**
     * 1024. 视频拼接
     * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
     * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。
     * 我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
     * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
     * 示例 1：
     * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
     * 输出：3
     * 解释：
     * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
     * 然后，按下面的方案重制比赛片段：
     * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
     * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
     * 示例 2：
     * 输入：clips = [[0,1],[1,2]], T = 5
     * 输出：-1
     * 解释：
     * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
     * 示例 3：
     * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
     * 输出：3
     * 解释：
     * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[101];
        int ans = 0, mx = 0, pre = 0;
        for (int[] clip : clips) {
            dp[clip[0]] = Math.max(dp[clip[0]], clip[1]);
        }
        for (int i = 0; i < T; i++) {
            mx = Math.max(mx, dp[i]);
            if (i == pre) { //这里表示上一个引入的片段已经走到了头，我们需要引入一个新片段
                ans ++;   //片段数++
                pre = mx;   // 引入的新片段我们不关心是那个，只关心这个片段最远能到哪
                //其实这个mx就是上次引入片段后在片段内的最大dp值，因为开始时间早于上一个引入片段的结束时间，所以一定是重叠的
            }
            if (i == mx) {
                return -1; // 表示当前最远已经到了头，返回-1
            }
        }
        return ans;
    }
    /**
     * 第一步dp数组的生成我们可以理解为对输入的数据进行了一个整理，为了求所给的时间片段能不能拼接成一个完整的视频，之后我们可以进行以下工作：
     * 从开始点出发，从0时刻扫描到T时刻，扫描过程中用mx变量来记录当前可以到达的最远时刻
     * 这里我们很容易就得出扫描进行不下去的一种情况，就是可以到达的最远时刻就等于当前时刻
     * 这相当于两边的路没有接上，自然无法完成该任务，返回false即可。
     * 如果扫描到T时刻，则说明一路畅通，所给的视频片段能够拼成完整视频，返回true即可。
     * 引入当前最远时间pre，使用惰性更新来求解
     * 好了，我们已经求出所给的时间片段能不能拼接成一个完整的视频这个子任务了，离题目要求的需片段的最小数目只有一步之遥。
     * 要求这个其实很简单，可以采用贪心的思想（惰性更新）：
     * 只要没超出上一个视频片段，我们就不引入新的片段。
     * 如果超过了上一个视频片段，我们将引入一个可以到达时间最远的片段
     * 具体的，我们使用pre来记录上次引入新片段时可以到达时间最远的片段
     * 同时用ans来记录一共引入了多少片段。
     * 作者：dongzengjie
     * 链接：https://leetcode-cn.com/problems/video-stitching/solution/0msshuang-bai-shi-yong-duo-xing-geng-xin-ce-lue-on/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
