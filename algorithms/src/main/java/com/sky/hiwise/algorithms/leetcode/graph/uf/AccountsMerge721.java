package com.sky.hiwise.algorithms.leetcode.graph.uf;

import java.util.*;
import java.util.stream.Collectors;

public class AccountsMerge721 {

    /**
     * 721. 账户合并
     * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
     * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
     * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
     * 示例 1：
     * 输入：
     * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
     * 输出：
     * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
     * 解释：
     * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
     * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
     * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
     * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int mailCount = 0;
        Map<String, Integer> emailIndex = new HashMap<>();
        Map<String, String> emailName = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                emailIndex.put(account.get(i), mailCount++);
                emailName.put(account.get(i), name);
            }
        }

        UnionFind uf = new UnionFind(mailCount);
        for(List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                uf.union(firstIndex, emailIndex.get(account.get(i)));
            }
        }

        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailIndex.keySet()) {
            int idx = uf.find(emailIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(idx, new ArrayList<>());
            account.add(email);
            indexToEmails.put(idx, account);
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailName.get(emails.get(0));
            List<String> temp = new ArrayList<>();
            temp.add(name);
            temp.addAll(emails);
            ans.add(temp);
        }
        return ans;
    }
    /**
     * 方法一：哈希表 + 并查集
     * 两个账户需要合并，当且仅当两个账户至少有一个共同的邮箱地址，因此这道题的实质是判断所有的邮箱地址中有哪些邮箱地址必定属于同一人，可以使用并查集实现。
     * 为了使用并查集实现账户合并，需要知道一共有多少个不同的邮箱地址，以及每个邮箱对应的名称，因此需要使用两个哈希表分别记录每个邮箱对应的编号和每个邮箱对应的名称，遍历所有的账户并在两个哈希表中记录相应的信息。
     * 虽然同一个邮箱地址可能在多个账户中出现，但是同一个邮箱地址在两个哈希表中都只能存储一次。
     * 然后使用并查集进行合并操作。由于同一个账户中的邮箱地址一定属于同一个人，因此遍历每个账户，对账户中的邮箱地址进行合并操作。并查集存储的是每个邮箱地址对应的编号，合并操作也是针对编号进行合并。
     * 完成并查集的合并操作之后，即可知道合并后有多少个不同的账户。遍历所有的邮箱地址，对于每个邮箱地址，通过并查集得到该邮箱地址属于哪个合并后的账户，即可整理出每个合并后的账户包含哪些邮箱地址。
     * 对于每个合并后的账户，需要整理出题目要求的返回账户的格式，具体做法是：将邮箱地址排序，账户的名称可以通过在哈希表中查找任意一个邮箱对应的名称得到，将名称和排序后的邮箱地址整理成一个账户列表。对所有合并后的账户整理出账户列表，即可得到最终答案。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/accounts-merge/solution/zhang-hu-he-bing-by-leetcode-solution-3dyq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
