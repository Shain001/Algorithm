package com.design.Trie;

import java.util.LinkedList;
import java.util.List;

public class TrieNode {
    protected char val;
    // 可以直接用数组， 因为是单词， 就26个字母最多
    protected TrieNode[] children;
    protected boolean isTail;

    public TrieNode() {
        this.isTail = false;
        this.val = ' ';
        this.children = new TrieNode[26];
    }

    public TrieNode(char val) {
        this.val = val;
        this.isTail = false;
        this.children = new TrieNode[26];
    }

    protected TrieNode getChild(char val) {
        return children[val-'a'];
    }

    protected TrieNode addChild(char val) {
        if (children[val-'a'] != null) {
            return children[val-'a'];
        }

        var child = new TrieNode(val);
        this.children[val-'a'] = child;
        return child;
    }

}
