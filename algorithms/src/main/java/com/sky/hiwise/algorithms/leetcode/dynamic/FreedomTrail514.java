package com.sky.hiwise.algorithms.leetcode.dynamic;

public class FreedomTrail514 {

    /**
     * 514. 自由之路
     * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
     * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
     * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
     * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
     * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
     * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
     * 示例：
     * 输入: ring = "godding", key = "gd"
     * 输出: 4
     * 解释:
     *  对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
     *  对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
     *  当然, 我们还需要1步进行拼写。
     *  因此最终的输出是 4。
     */
    public int findRotateSteps(String ring, String key) {
        return 0;
    }

    /**
     * 思路
     * 我们的目标：对齐、拼出key中的每个字符，求总共的最少步数。
     *
     * 每个key字符对齐后都会按下按钮，按的次数固定，关键看旋转对齐key字符们的步数。
     *
     * 对于每个 key 字符，它在 ring 中可能有多个，我们每次选择对齐其中一个，每个都试。
     *
     * 对于下一个key字符，它在 ring 中可能也有多个，我们用dfs去尝试所有的选择：作出一个选择，基于当前选择，再做选择（往下递归）。
     *
     * 并且，每次对齐，有两种旋转方式，带来两种不同的旋转格数：
     *
     * 顺时针旋转，使它对齐12点
     * 逆时针旋转，使它对齐12点
     定义递归函数
     递归函数传入什么参数呢？即，用什么变量描述一个子问题呢？

     key[keyI]的keyI肯定是需要的，代表当前想对齐的 key 字符。

     key[keyI]被旋转到 12 点，要走多少刻度，与什么有关？

     与当前12点对齐哪一个 ring 字符有关。
     即，与当前12点对齐的ring[ringI]的ringI有关。
     起初12点对齐ring[0]，大问题是求：基于12点对齐ring[0]，实现对齐key[0]到key末尾字符，所需的最少步数

     递归函数的定义：基于当前12点对齐ring[ringI]，实现对齐key[keyI]到key末尾字符，所需的最少步数

     递归中的逻辑
     key 字符在 ring 中可能有多个，即，想对齐的目标 ring 索引可能有多个。
     用一个 map 保存每个 ring 字符在 ring 中出现的索引集合（数组）。
     key[keyI]是当前想对齐的key字符，在 map 中找到对应的索引数组，遍历这个索引数组，拿到：我们想对齐的目标 ring 索引targetI。
     每次迭代，枚举一个targetI，计算距离，基于这个targetI，继续递归。

     当前 12 点对齐的是ringI，它和targetI的距离，因旋转方向不同而有两种：

     取绝对值(ringI - targetI)
     len(ring) - 取绝对值(ringI - targetI)
     取较小者，继续对齐剩下的 key 字符：dfs(targetI, keyI+1)，代表：基于当前12点对齐ring[targetI]，实现对齐key[keyI+1]到key末尾字符，所需的最少步数。

     递归的结束条件
     当keyI指针越界时，没有需要对齐的 key 字符了，结束当前递归分支，返回步数 0。

     递归函数的逻辑：


     func dfs(ringI, keyI) int {
     设置递归结束条件
     定义 res 变量，当前子问题的解

     for 枚举出 keyI[keyI] 在 ring 中的字符索引：targetI { // 枚举出所有选项
     算出 ringI 和 targetI 的距离，有两种
     选出较小者
     该较小者 + 剩余key字符的递归结果 = 属于当前targetI的解 // 属于当前选项的解
     属于当前targetI的解，试图更新当前子问题的解 res // 在多个选项的解中，取优
     }

     return 当前子问题的解 res
     }

     作者：xiao_ben_zhu
     链接：https://leetcode-cn.com/problems/freedom-trail/solution/shou-hua-tu-jie-di-gui-ji-yi-hua-di-gui-514-zi-you/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
