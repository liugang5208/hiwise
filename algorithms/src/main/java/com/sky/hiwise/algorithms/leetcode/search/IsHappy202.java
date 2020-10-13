package com.sky.hiwise.algorithms.leetcode.search;

import java.util.HashSet;

public class IsHappy202 {

    public static void main(String[] args) {
        int n = 19;
        IsHappy202 isHappy202  = new IsHappy202();
        System.out.println(isHappy202.isHappy(n));
        //System.out.println(n/10 + ":" +n);
    }

    private HashSet<Integer> set = new HashSet<>();

    /**
     * 202. 快乐数
     写一个算法来判断一个数是不是“快乐数”。
     一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
     示例: 输入: 19  输出: true
     解释:
     12 + 92 = 82  82 + 22 = 68  62 + 82 = 100  12 + 02 + 02 = 1
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        System.out.println(n);
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n = n/10;
        }
        if (res == 1) {
            return true;
        }
        if (set.contains(res)) {
            return false;
        }
        set.add(res);
        return isHappy(res);
    }

    public int getsum(int x) {
        int sum=0;
        while(x != 0){
            sum+=(x%10)*(x%10);
            x=x/10;
        }
        return sum;
    }

    /**
     * 4 是非快乐数 遇到4就退出比用HashSet解法速度快
     * @param n
     * @return
     */
    public boolean isHappy1(int n) {
        if(n<=0)return false;
        while(n != 1){
            n = getsum(n);
            if(n == 4)
                return false;
        }
        return true;
    }
}
