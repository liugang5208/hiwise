package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.*;

public class WaterPuzzle {

    /**
     * 一个水桶装5升水，一个桶装3升水，怎么利用这2个桶得到4升水？
     */
    private int end = -1;
    private int[] pre;
    public void WaterPuzzle() {
        boolean[] visited = new boolean[100];
        pre = new int[100];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int a = cur / 10, b = cur % 10;
            //max a = 5 max b = 3
            List<Integer> nexts = new ArrayList<>();
            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);
            nexts.add(0 * 10 + b);
            nexts.add(a * 10 + 0);

            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + (b + x));
            int y = Math.min(5 - a, b);
            nexts.add((a + y) * 10 + (b - y));

            for (Integer next : nexts) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    pre[next] = cur;
                    if (next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) {
            return res;
        }
        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WaterPuzzle wp = new WaterPuzzle();
        wp.WaterPuzzle();
        System.out.println(wp.result());
    }
}
