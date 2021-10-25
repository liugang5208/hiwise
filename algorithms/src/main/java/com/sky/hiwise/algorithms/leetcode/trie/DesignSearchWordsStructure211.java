package com.sky.hiwise.algorithms.leetcode.trie;

public class DesignSearchWordsStructure211 {
    public static void main(String[] args) {
        //["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        //[[],["bad"],["dad"],["mad"], ["pad"],["bad"],[".ad"],["b.."]]
        (new DesignSearchWordsStructure211()).test();
        //false,true,true,true
    }

    public void test() {
        WordDictionary test = new WordDictionary();
        test.addWord("bad");
        test.addWord("dad");
        test.addWord("mad");
        System.out.println(test.search("pad"));
        System.out.println(test.search("bad"));
        System.out.println(test.search(".ad"));
        System.out.println(test.search("b.."));
    }
    class WordDictionary {
        private Trie trie;
        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, trie);
        }

        private boolean dfs(String word, int index, Trie node) {
            if (index == word.length()) {
                return node.isWord();
            }
            char ch = word.charAt(index);
            if (Character.isLetter(ch)) {
                int chIndex = ch - 'a';
                Trie children = node.getChildren()[chIndex];
                if (children != null && dfs(word, index + 1, children)) {
                    return true;
                }
            } else {
               for (int i = 0; i < 26; i++) {
                   Trie children = node.getChildren()[i];
                   if (children != null && dfs(word, index + 1, children)) {
                       return true;
                   }
               }
            }
            return false;
        }
    }

    class Trie {

        private boolean isWord;
        private Trie[] children;
        public Trie() {
            isWord = false;
            children = new Trie[26];
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isWord = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isWord() {
            return isWord;
        }

    }
}
