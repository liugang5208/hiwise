package com.sky.hiwise.algorithms.leetcode.graph.uf;

import java.util.HashMap;
import java.util.Map;

public class MinHammingDistanceAfterSwap1722 {
    /**
     * 1722. 执行交换操作后的最小汉明距离
     * 给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，
     * 其中每个 allowedSwaps[i] = [ai, bi] 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。
     * 注意，你可以按 任意 顺序 多次 交换一对特定下标指向的元素。
     * 相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。
     * 形式上，其值等于满足 source[i] != target[i] （下标从 0 开始）的下标 i（0 <= i <= n-1）的数量。
     * 在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
     * 示例 1：
     * 输入：source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
     * 输出：1
     * 解释：source 可以按下述方式转换：
     * - 交换下标 0 和 1 指向的元素：source = [2,1,3,4]
     * - 交换下标 2 和 3 指向的元素：source = [2,1,4,3]
     * source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);
        for (int[] allow : allowedSwaps) {
            uf.union(allow[0], allow[1]);
        }
        Map<Integer, Map<Integer, Integer>> sMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> tMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            Map<Integer, Integer> sTemp = sMap.getOrDefault(root, new HashMap<>());
            sTemp.put(source[i], sTemp.getOrDefault(source[i], 0) + 1);
            sMap.put(root, sTemp);
            Map<Integer, Integer> tTemp = tMap.getOrDefault(root, new HashMap<>());
            tTemp.put(target[i], tTemp.getOrDefault(target[i], 0) + 1);
            tMap.put(root, tTemp);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) != i) {
                continue;
            }

            Map<Integer, Integer> sTemp = sMap.get(i);
            Map<Integer, Integer> tTemp = tMap.get(i);
            for (Map.Entry<Integer, Integer> entry : sTemp.entrySet()) {
                Integer key = entry.getKey();
                Integer count = entry.getValue();
                Integer countT = tTemp.get(key);
                if (countT == null) {
                    ans += count;
                } else {
                    // s中比t中多出来的个数，也是答案
                    if (count > countT) {
                        ans += count - countT;
                    }
                    tTemp.remove(key);
                }
            }
        }
        return ans;
    }

    /**
     * 题目分析
     * 假设allowedSwaps中包含(0,1)、(0,2)，那么是不是意味着位置1和2的元素可以互换呢？
     * 比如说，只允许交换(0,1)、(0,2)位置上的数，能否将abc 变成 acb？
     * // abc  (交换0,1)->
     * // bac  (交换0,2)->
     * // cab  (交换0,1)->
     * // acb
     * 答案是显然的。也就是说，0,1,2这些联通在一起的位置，他们各自位置上的数，因为可以任意两两交换，顺序变得不重要。
     * 基于上述思考，开始构建本题的解答过程：
     * 使用并查集记录这些互相联通的位置，构成一个个的联通分支。
     * 没有与其他位置联通的位置，单独就是一个联通分支。
     * 对于每一个联通分支（记录一组互相联通的位置），判断：
     * source中，这些位置的数构成一个集合S（允许有重复元素）
     * target中，也是这些位置的数构成一个集合T（允许有重复元素）
     * 将S和T中不同元素的个数累加到答案中。
     * 其中第3点，比较集合中不同元素的个数，可以遍历S中的每一个元素x：
     * 如果T中不存在x，那么S中的所有x都属于S和T不同的元素。
     * 如果T中也存在x，那么S中x的个数，比T中x的个数多出来的数量，也是答案。
     * 作者：liberg
     * 链接：https://leetcode-cn.com/problems/minimize-hamming-distance-after-swap-operations/solution/shuang-100-javaban-map-bing-cha-ji-by-li-n3op/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
