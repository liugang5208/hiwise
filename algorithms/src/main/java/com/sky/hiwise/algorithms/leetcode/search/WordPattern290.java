package com.sky.hiwise.algorithms.leetcode.search;

import java.util.HashMap;

public class WordPattern290 {
    public static void main(String[] args) {
        String s = "AaBbcdf";
        for (int i= 0;i<s.length();i++) {
            System.out.println((int)s.charAt(i));
        }
    }

    /**
     * 290. 单词模式
     给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循这种模式。
     这里的 遵循 指完全匹配，例如在pattern里的每个字母和字符串 str 中的每个非空单词存在双向单映射关系。
     例如：
     1. pattern = "abba", str = "dog cat cat dog", 返回true
     2. pattern = "abba", str = "dog cat cat fish", 返回false.
     3. pattern = "aaaa", str = "dog cat cat dog" , 返回false.
     4. pattern = "abba", str = "dog dog dog dog" , 返回false.
     说明:
     你可以假设 pattern 只包含小写字母， str 包含了由单个空格分开的小写单词。
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        String []word = str.split(" ");
        if (word.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for( int i = 0; i < word.length; i++) {

            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(word[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(word[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), word[i]);
            }
        }
        return true;
    }

    /**
     * 205. 同构字符串
     给定两个字符串 s 和 t，判断它们是否是同构的。
     如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     示例 1:输入: s = "egg", t = "add" 输出: true
     示例 2:输入: s = "foo", t = "bar" 输出: false
     示例 3:输入: s = "paper", t = "title" 输出: true
     说明:你可以假设 s 和 t 具有相同的长度。
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public boolean isIsomorphic1(String s, String t) {
        int[] a = new int[256];
        int[] b = new int[256];
        for(int i=0; i<s.length(); i++){
            int j = s.charAt(i);
            int k = t.charAt(i);
            if(a[j]==0 && b[k]==0){
                a[j] = k;
                b[k] = j;
                continue;
            }
            if(a[j]==0 || a[j]!=k){
                return false;
            }
        }
        return true;
    }

    /**
     * 一个数组存储 ASCII值对应的在字符串中的索引位置
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        int[] m = new int[256];
        for(int i=0;i<s.length();i++)
        {
            if(m[s.charAt(i)]!=m[t.charAt(i)+128]) return false;
            m[s.charAt(i)]=m[t.charAt(i)+128] = i+1;
        }
        return true;
    }
}
