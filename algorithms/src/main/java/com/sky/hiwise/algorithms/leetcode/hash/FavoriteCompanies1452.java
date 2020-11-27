package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavoriteCompanies1452 {

    /**
     * 1452. 收藏清单
     * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
     * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
     * 示例 1：
     * 输入：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
     * 输出：[0,1,4]
     * 解释：
     * favoriteCompanies[2]=["google","facebook"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。
     * favoriteCompanies[3]=["google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 和 favoriteCompanies[1]=["google","microsoft"] 的子集。
     * 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
     * @param favoriteCompanies
     * @return
     */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> ans = new ArrayList<>();
        int n = favoriteCompanies.size();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                List<String> listi = favoriteCompanies.get(i);
                List<String> listj = favoriteCompanies.get(j);
                Set<String> setj = new HashSet<>(listj);
                if (setj.containsAll(listi)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> peopleIndexes1(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        Set<String>[] arr = new Set[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new HashSet(favoriteCompanies.get(i));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (arr[j].containsAll(arr[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans.add(i);
        }

        return ans;
    }
}
