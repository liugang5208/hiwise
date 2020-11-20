package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueueReconstructionByHeight406 {
    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     * 注意：
     * 总人数少于1100人。
     * 示例
     * 输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] peo : people) {
            ans.add(peo[1], peo);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        int[][] people = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        //System.out.println((new QueueReconstructionByHeight406()).reconstructQueue(people));
        List<Integer> test = new ArrayList<>();
        test.add(0, 55);
        test.add(0,66);
        test.add(0, 77);
        test.add(0,88);
        for (int t :test) {
            System.out.println(t);
        }
        System.out.println(test.size());

    }
}
