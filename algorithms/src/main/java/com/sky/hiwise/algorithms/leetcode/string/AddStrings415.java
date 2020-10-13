package com.sky.hiwise.algorithms.leetcode.string;

public class AddStrings415 {

    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * 注意：
     *
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     */
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        int len1 = num1.length() - 1, len2 = num2.length() - 1;
        int carry = 0;
        StringBuffer ans = new StringBuffer();
        while(len1 >= 0 || len2 >= 0 || carry != 0) {
            int a = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int b = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
            int temp = a + b + carry;
            ans.append(temp % 10);
            carry = temp / 10;
            len1--;
            len2--;
        }
        ans.reverse();
        return ans.toString();
    }



    /**
     * 36进制由0-9，a-z，共36个字符表示，最小为'0'
     * '0''9'对应十进制的09，'a''z'对应十进制的1035
     * 例如：'1b' 换算成10进制等于 1 * 36^1 + 11 * 36^0 = 36 + 11 = 47
     * 要求按照加法规则计算出任意两个36进制正整数的和
     * 如：按照加法规则，计算'1b' + '2x' = '48'
     *
     * 要求：不允许把36进制数字整体转为10进制数字，计算出10进制数字的相加结果再转回为36进制
     *
     * @param args
     */

    public static void main(String [] args) {
        String result = addFunWithStr("1b", "2x");
        System.out.println("result = " + result);
    }

    /**
     * 获取值
     * @param ch
     * @return
     */
    public static int getIntFromChar(char ch) {
        int ret = -1;
        if (ch >='0' && ch <= '9') {
            ret = ch - '0';
        } else if (ch >= 'a' && ch <= 'z') {
            ret = (ch - 'a') + 10;
        }
        return ret;
    }

    public static String addFunWithStr(String param1, String param2) {

        StringBuffer stringBuffer = new StringBuffer();
        String symbol = "0123456789abcdefghijklmnopqrstuvwxyz";
        int param1Len = param1.length();
        int param2Len = param2.length();
        int i = param1Len - 1;
        int j = param2Len - 1;
        if (i < 0 || j < 0) {
            return null;
        }
        int temp = 0;
        while (i >= 0 && j >= 0) {
            char ch1 = param1.charAt(i);
            char ch2 = param2.charAt(j);
            int v1 = getIntFromChar(ch1);
            int v2 = getIntFromChar(ch2);
            int ret = v1 + v2;
            if (ret >= 36) {
                int index = ret - 36 + temp;
                char sv = symbol.charAt(index);
                stringBuffer.append(sv);
                temp = 1; //进位
            } else {
                int index = ret + temp;
                char sv = symbol.charAt(index);
                stringBuffer.append(sv);
                temp = 0;
            }
            i--;
            j--;
        }

        while (i >= 0) {
            char ch1 = param1.charAt(i);
            stringBuffer.append(ch1);

            i--;
        }

        while (j >= 0) {
            char ch2 = param2.charAt(i);
            stringBuffer.append(ch2);
            j--;
        }

        StringBuffer result = stringBuffer.reverse();
        return result.toString();
    }
}
