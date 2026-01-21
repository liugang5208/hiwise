package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class ReverseList206 {

    /**
     * 206. 反转链表
     * 反转一个单链表。进阶:链表可以迭代或递归地反转。你能否两个都实现一遍？
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * // 递归的方式反转链表
     * // 时间复杂度: O(n)
     * // 空间复杂度: O(n) - 注意，递归是占用空间的，占用空间的大小和递归深度成正比：）
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {

        // 递归终止条件
        if(head == null|| head.next == null)
            return head;

        ListNode rhead = reverseList2(head.next);

        // head->next此刻指向head后面的链表的尾节点
        // head->next->next = head把head节点放在了尾部
        head.next.next = head;
        head.next = null;

        return rhead;
    }

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * 示例:
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 最终为 m 位置的前一点
        ListNode mPre = dummyHead;
        // 最终为 n 位置的后一点
        ListNode nNext;

        for (int i = 0; i < m - 1; i++) {
            mPre = mPre.next;
        }

        // 待反转的两点
        ListNode cur = mPre.next;
        ListNode next = cur.next;

        nNext = next;
        for(int i = 0; i < n - m; i++) {
            // 进行反转
            nNext = next.next;
            next.next = cur;
            // 移动到下一个反转处
            cur = next;
            next = nNext;
        }

        // 反转完毕，需要将两端与 mPre 和 nNext 连接
        mPre.next.next = nNext;
        mPre.next = cur;
        return dummyHead.next;
    }

    public ListNode reverseBetween1(ListNode head, int m, int n) {
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode p = res.next;
        ListNode front = res;
        int i = 1;
        while(i!=m) {
            front = p;
            p = p.next;
            i++;
        }
        ListNode end = p;
        ListNode q = p.next;
        p.next = null;
        while(i!=n) {
            ListNode r = q.next;
            q.next = p;
            p = q;
            q = r;
            i++;
        }
        front.next = p;
        end.next = q;
        return res.next;
    }

    /**
     * 25. k个一组翻转链表
     * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
     * 示例 :  给定这个链表：1->2->3->4->5  当 k = 2 时，应当返回: 2->1->4->3->5  当 k = 3 时，应当返回: 3->2->1->4->5
     * 说明 :你的算法只能使用常数的额外空间。你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode check = head, curr = head, pre = null, next = null;
        int canProceed = 0;
        int count = 0;
        while (canProceed < k && check != null) {
            check = check.next;
            canProceed ++;
        }
        if (canProceed == k) {
            while (count < k && curr != null) {
                next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
                count ++;
            }
            if (next != null) {
                // head 为链表翻转后的尾节点
                head.next = reverseKGroup(next, k);
            }
            // pre 为链表翻转后的头结点
            return pre;
        } else {
            //不满足翻转条件，直接返回 head 即可
            return head;
        }
    }

    /**
     * 147. 对链表进行插入排序
     * 对链表进行插入排序。
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     *
     * 插入排序算法：
     *   1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     *   2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     *   3. 重复直到所有输入数据插入完为止。
     * 示例 1：输入: 4->2->1->3  输出: 1->2->3->4
     *
     * 示例 2：输入: -1->5->3->4->0  输出: -1->0->3->4->5
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {

            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // head~pre是排好序的部分
        ListNode pre = head;
        // 第一个元素默认是有序的
        ListNode curr = head.next;

        while(curr != null) {
            // 寻找插入位置
            ListNode insertPre = findInsertIndexPre(dummyHead, curr);
            if (insertPre == pre) {
                pre = curr;
                curr = curr.next;
            } else {

                pre.next = curr.next;
                curr.next = insertPre.next;
                insertPre.next = curr;
                curr = pre.next;

            }
        }

        return dummyHead.next;
    }

    private ListNode findInsertIndexPre(ListNode head, ListNode curr) {
        while (head.next != curr) {
            if (head.next.val >= curr.val) {
                return head;
            }
            head = head.next;
        }
        return head;
    }

    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     */
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for(n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;
        return new_head;
    }
    /**
     * 方法 1：
     * 直觉
     * 链表中的点已经相连，一次旋转操作意味着：
     * 先将链表闭合成环
     * 找到相应的位置断开这个环，确定新的链表头和链表尾
     * 新的链表头在哪里？
     * 在位置 n-k 处，其中 n 是链表中点的个数，新的链表尾就在头的前面，位于位置 n-k-1。
     * 我们这里假设 k < n
     * 如果 k >= n 怎么处理？
     * k 可以被写成 k = (k // n) * n + k % n 两者加和的形式，其中前面的部分不影响最终的结果，因此只需要考虑 k%n 的部分，这个值一定比 n 小。
     * 算法
     * 算法实现很直接：
     * 找到旧的尾部并将其与链表头相连 old_tail.next = head，整个链表闭合成环，同时计算出链表的长度 n。
     * 找到新的尾部，第 (n - k % n - 1) 个节点 ，新的链表头是第 (n - k % n) 个节点。
     * 断开环 new_tail.next = None，并返回新的链表头 new_head。
     * 复杂度分析
     * 时间复杂度：O(N)，其中 NN 是链表中的元素个数
     * 空间复杂度：O(1)，因为只需要常数的空间
     */

}
