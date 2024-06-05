### 面试资料
https://github.com/Snailclimb/JavaGuide   
https://github.com/h2pl/JavaTutorial  
https://pdai.tech/md/outline/x-outline.html   

### 字符串数组排序时间复杂度
有一个字符串数组，将数组中的每一个字符串按照字母排序；之后再将整个字符串数组按照字典序排序。整个操作的时间复杂度？  
假设最长字符串的长度为s；数组中有n个字符串

## 操作系统
什么是用户态和内核态？  
进程和线程     
进程间通信方式 使用场景 解决什么问题   
进程运行过程中的状态，进程调度有哪些算法？  
父进程如何创建一个子进程  调用fork创建子进程 如何处理相关资源？  
共享内存 创建或获取共享内存的方法 shmget  semget  
什么是虚拟内存?有什么用？  
IO多路复用 select poll epoll 区别 对比 优劣   阻塞/非阻塞  同步/异步 概念  
http 状态码  https 与http优劣对比  https工作原理（加密过程  对称 非对称加密）   
TCP如何保证传输的可靠性？  拥塞控制是怎么实现的？ 有哪些算法  

### 虚拟内存
总结来说，虚拟内存主要提供了下面这些能力：  
隔离进程：物理内存通过虚拟地址空间访问，虚拟地址空间与进程一一对应。每个进程都认为自己拥有了整个物理内存，进程之间彼此隔离，一个进程中的代码无法更改正在由另一进程或操作系统使用的物理内存。  
提升物理内存利用率：有了虚拟地址空间后，操作系统只需要将进程当前正在使用的部分数据或指令加载入物理内存。  
简化内存管理：进程都有一个一致且私有的虚拟地址空间，程序员不用和真正的物理内存打交道，而是借助虚拟地址空间访问物理内存，从而简化了内存管理。  
多个进程共享物理内存：进程在运行过程中，会加载许多操作系统的动态库。这些库对于每个进程而言都是公用的，它们在内存中实际只会加载一份，这部分称为共享内存。  
提高内存使用安全性：控制进程对物理内存的访问，隔离不同进程的访问权限，提高系统的安全性。  
提供更大的可使用内存空间：可以让程序拥有超过系统物理内存大小的可用内存空间。这是因为当物理内存不够用时，可以利用磁盘充当，将物理内存页（通常大小为 4 KB）保存到磁盘文件（会影响读写速度），数据或代码页会根据需要在物理内存与磁盘之间移动。  

## Redis相关
Redis如何解决哈希冲突？ 如何解决rehash开销的影响？  
为了避免产生过大的AOF日志文件，如何处理？  
会触发AOF日志重写，这个过程会阻塞Redis的主线程，进而影响Redis的性能吗？  
执行AOF重写任何时候都可以执行吗？有哪些限制条件？  
重写时的新写操作记录如何处理？   
数据过期删除策略 惰性删除和定期删除  
Redis 分布式锁 主从高可用 主从如何保证数据一致，RDB + 缓冲器写命令同步  

## 分布式理论
分布式系统 CAP定理、理论
分布式id生成方案有哪些？优劣分析
uuid生成的订单号 能否作为主键

## 常见问题分析
消息队列 消息重复如何处理？  
顺序消息  
消息处理异常等  
zookeeper分布式锁 如何解决惊群效应
kafka架构 原理 为何性能较快 如何保证高可用  网络通信模型
Netty 基于主从 Reactors 多线程模型原理
Netty如何解决Selector空轮询bug，最终导致CPU 100 %

## MySQL相关
MySQL InnoDB 引擎使用 redo log(重做日志) 保证事务的持久性，使用 undo log(回滚日志) 来保证事务的原子性。  
MySQL数据库的数据备份、主备、主主、主从都离不开binlog，需要依靠binlog来同步数据，保证数据一致性。  
事务的隔离级别 不同级别分别会出现什么问题  

## java相关
1. java中sleep方法和wait方法区别
（1）wait 方法必须配合synchronized 一起使用，不然在运行时就会抛出 IllegalMonitorStateException 的异常  
而 sleep 可以单独使用，无需配合 synchronized 一起使用。  
（2)wait 方法属于 Object 类的方法，而 sleep 属于 Thread 类的方法  
（3）sleep 方法必须要传递一个超时时间的参数，且过了超时时间之后，线程会自动唤醒。而 wait 方法可以不传递任何参数，不传递任何参数时表示永久休眠，直到另一个线程调用了 notify 或 notifyAll 之后，休眠的线程才能被唤醒。  
也就是说 sleep 方法具有主动唤醒功能，而不传递任何参数的 wait 方法只能被动的被唤醒。  
（4）wait 方法会主动的释放锁，而 sleep 方法则不会  
（5）线程进入状态不同  
调用 sleep 方法线程会进入 TIMED _WAITING 有时限等待状态，而调用无参数的 wait 方法，线程会进入 WAITING 无时限等待状态  
线程阻塞和线程等待区别：  
在java中，线程阻塞状态是线程本身不可计划的，而线程等待状态是线程本身计划之内的。  
相同点：  
（1）都会暂停线程的执行。  
区别点：  
（1）线程进入阻塞状态是被动的, 而线程进入等待状态是主动的。  
阻塞状态的被动：线程在同步代码外，获取对象锁失败时，线程进入阻塞状态；何时获取对象锁失败不可知，即线程阻塞状态是线程本身不可计划的。  
等待状态的主动：线程在同步代码内，等待其他线程操作时，线程接入等待状态；何时等待其他线程操作可知，即线程等待状态是线程本身计划之内的。

2. 线程池运行机制和相关原理，拒绝策略等  
3. ThreadLocal 使用场景，底层实现原理
4. ReentrantLock 实现原理 CAS AQS
5. CMS G1 垃圾收集器运行原理 解决什么问题
6. synchronized与ReentrantLock
7. HashMap ConcurrentHashMap 

## 算法提
1. 合并两个有序链表  
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。  
示例： 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4  

2. 层序遍历二叉树 思路？ PriorityQueue  

3. 三数之和
给定一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = target ？找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组。  
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，target = 0  
满足要求的三元组集合为：  
[  
[-1, 0, 1],  
[-1, -1, 2]  
]  

4. 请检查一个链表是否为回文链表。  
   输入：head = [1,2,2,1]  
   输出：true  
   
5. 删除排序链表中的重复元素  
   给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。  
   示例 1: 输入: 1->2->3->3->4->4->5 输出 : 1->2->5  
   示例 2: 输入: 1->1->1->2->3 输出 : 2->3  
   
```java
public class Test {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode curr = dummyHead.next;
        boolean isDelCurr = false;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
                isDelCurr = true;
            } else {
                if (isDelCurr) {
                    pre.next = curr.next;
                    curr = pre.next;   //curr = curr.next;
                    isDelCurr = false;
                } else {
                    pre = pre.next;
                    curr = curr.next;
                }
            }
        }

        if (isDelCurr) {
            pre.next = curr.next;
            curr = pre.next;   //curr = curr.next;
            isDelCurr = false;
        }

        return dummyHead.next;
    }
}
```

6. 最长公共子序列  
   给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。  
   一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。  
   例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。  
   两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。  
   示例 1：  
   输入：text1 = "abcde", text2 = "ace"  
   输出：3  
   解释：最长公共子序列是 "ace" ，它的长度为 3 。  
```java
public class Test{
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
           return 0;
        }
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] lcs = new int[len1 + 1][len2 + 1];
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[len1][len2];
    }
}
```

```java
public class Test{
    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {   

    }

    public List<List<Integer>> threeSum(int [] nums, int target) {  

    } 

    public boolean isPalindrome(ListNode head) {  

    }  
}
```
