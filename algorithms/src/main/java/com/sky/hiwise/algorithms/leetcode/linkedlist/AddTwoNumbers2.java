package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class AddTwoNumbers2 {

    /**
     * 2. 两数相加
     * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 示例：  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)  输出：7 -> 0 -> 8  原因：342 + 465 = 807
     *
     * 算法
     * 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1和 l2 的表头开始相加。
     * 由于每位数字都应当处于 0…9 的范围内，我们计算两个数字的和时可能会出现“溢出”。
     * 例如，5+7=12。在这种情况下，我们会将当前位的数值设置为 2，并将进位 carry=1 带入下一次迭代。
     * 进位 carry 必定是 0 或 1，这是因为两个数字相加（考虑到进位）可能出现的最大和为 9+9+1=19。
     * 伪代码如下：
     *   ● 将当前结点初始化为返回列表的哑结点。
     *   ● 将进位 carry 初始化为 0。
     *   ● 将 p 和 q 分别初始化为列表 l1 和 l2 的头部。
     *   ● 遍历列表 l1 和 l2 直至到达它们的尾端。
     *       ○ 将 x 设为结点 p 的值。如果 p 已经到达 l1 的末尾，则将其值设置为 0。
     *       ○ 将 y 设为结点 q 的值。如果 q 已经到达 l2 的末尾，则将其值设置为 0。
     *       ○ 设定 sum=x+y+carry。
     *       ○ 更新进位的值，carry=sum/10。
     *       ○ 创建一个数值为 (sum mod 10) 的新结点，并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
     *       ○ 同时，将 p 和 q 前进到下一个结点。
     *   ● 检查 carry=1 是否成立，如果成立，则向返回列表追加一个含有数字 1 的新结点。
     *   ● 返回哑结点的下一个结点。
     * 请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：给定的 n 保证是有效的。进阶：你能尝试使用一趟扫描实现吗？
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 如果链表为空或者k小于等于0
        if (head == null || n <= 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 声明两个指向头结点的节点
        ListNode node1 = dummy, node2 = dummy;
        while (node1 != null) {
            node1 = node1.next;
            if (n < 1 && node1 != null) {
                node2 = node2.next;
            }
            n --;
        }

        node2.next = node2.next.next;
        return dummy.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * 示例 1:  输入: 1->1->2  输出: 1->2  示例 2:  输入: 1->1->2->3->3  输出: 1->2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {

            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * 86. 分隔链表
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     * 示例:  输入: head = 1->4->3->2->5->2, x = 3  输出: 1->2->2->4->3->5
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode dummyNode1 = new ListNode(0);
        ListNode dummyNode2 = new ListNode(0);
        ListNode node1 = dummyNode1, node2 = dummyNode2;

        while (head != null) {
            if (head.val < x) {
                node1.next = head;
                head = head.next;
                node1 = node1.next;
                node1.next = null;
            } else {
                node2.next = head;
                head = head.next;
                node2 = node2.next;
                node2.next = null;
            }
        }
        node1.next = dummyNode2.next;
        return dummyNode1.next;
    }

    /**
     * 328
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     * 示例 1:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     * 示例 2:
     * 输入: 2->1->3->5->6->4->7->NULL
     * 输出: 2->3->6->7->1->5->4->NULL
     * 说明:
     * 应当保持奇数节点和偶数节点的相对顺序。
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     * https://blog.csdn.net/tyler_download/article/details/54425777
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //奇数
        ListNode oddHead = head;

        //偶数
        ListNode evenHead = head.next;
        ListNode p = evenHead;

        while (oddHead.next != null && evenHead.next != null) {
            oddHead.next = evenHead.next;
            oddHead = oddHead.next;

            evenHead.next = oddHead.next;
            evenHead = evenHead.next;
        }

        oddHead.next = p;
        return head;
    }

}
