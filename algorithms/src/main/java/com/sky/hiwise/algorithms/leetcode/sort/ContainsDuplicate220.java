package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.TreeSet;

public class ContainsDuplicate220 {

    /**
     * 220. 存在重复元素 III
     * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
     * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，
     * 并且 i 和 j 之间的差的绝对值最大为 ķ。
     * 示例 1:
     * 输入: nums = [1,2,3,1], k = 3, t = 0
     * 输出: true
     * 示例 2:
     * 输入: nums = [1,0,1,1], k = 1, t = 2
     * 输出: true
     * 示例 3:
     * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出: false
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) {
                return true;
            }
            Integer g = set.floor(nums[i]);
            //nums[i] <= g + t
            if (g != null && nums[i] <= g + t) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 方法二 （二叉搜索树） 【通过】
     * 下面给出整个算法的伪代码：
     *
     * 初始化一颗空的二叉搜索树 set
     * 对于每个元素xx，遍历整个数组
     * 在 set 上查找大于等于xx的最小的数，如果s - x \leq ts−x≤t则返回 true
     * 在 set 上查找小于等于xx的最大的数，如果x - g \leq tx−g≤t则返回 true
     * 在 set 中插入xx
     * 如果树的大小超过了kk, 则移除最早加入树的那个数。
     * 返回 false
     * 我们把大于等于 xx 的最小的数 ss 当做 xx 在 BST 中的后继节点。同样的，
     * 我们能把小于等于 xx 最大的数 gg 当做 xx 在 BST 中的前继节点。ss 和 gg 这两个数是距离 xx 最近的数。
     * 因此只需要检查它们和 xx 的距离就能知道条件二是否满足了。
     * 思路
     * 如果窗口中维护的元素是有序的，只需要用二分搜索检查条件二是否是满足的就可以了。
     * 利用自平衡二叉搜索树，可以在对数时间内通过 插入 和 删除 来对滑动窗口内元素排序。
     * 算法
     * 方法一真正的瓶颈在于检查第二个条件是否满足需要扫描滑动窗口中所有的元素。因此我们需要考虑的是有没有比全扫描更好的方法。
     * 如果窗口内的元素是有序的，那么用两次二分搜索就可以找到 x+t和 x-t这两个边界值了。
     * 然而不幸的是，窗口中的元素是无序的。这里有一个初学者非常容易犯的错误，那就是将滑动窗口维护成一个有序的数组。
     * 虽然在有序数组中 搜索 只需要花费对数时间，但是为了让数组保持有序，我们不得不做插入和删除的操作，而这些操作是非常不高效的。
     * 想象一下，如果你有一个kk大小的有序数组，当你插入一个新元素xx的时候。虽然可以在O(logk)时间内找到这个元素应该插入的位置，
     * 但最后还是需要O(k)O(k)的时间来将xx插入这个有序数组。因为必须得把当前元素应该插入的位置之后的所有元素往后移一位。
     * 当你要删除一个元素的时候也是同样的道理。在删除了下标为ii的元素之后，还需要把下标ii之后的所有元素往前移一位。因此，这种做法并不会比方法一更好。
     * 为了能让算法的效率得到真正的提升，我们需要引入一个支持 插入，搜索，删除 操作的 动态 数据结构，那就是自平衡二叉搜索树。
     * 自平衡 这个词的意思是，这个树在随机进行插入,删除操作之后，它会自动保证树的高度最小。
     * 为什么一棵树需要自平衡呢？这是因为在二叉搜索树上的大部分操作需要花费的时间跟这颗树的高度直接相关。
     * 可以看一下下面这棵严重左偏的非平衡二叉搜索树。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii/solution/cun-zai-zhong-fu-yuan-su-iii-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
