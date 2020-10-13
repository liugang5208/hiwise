package com.sky.hiwise.algorithms.leetcode.interview;

public class SwapNumbers1601 {

    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }
}
