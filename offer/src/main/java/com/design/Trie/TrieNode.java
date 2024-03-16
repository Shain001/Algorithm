package com.design.Trie;

import java.util.LinkedList;
import java.util.List;

public class TrieNode {
    protected char val;
    // 可以直接用数组， 因为是单词， 就26个字母最多
    protected List<TrieNode> children;
    protected boolean isTail;

    public TrieNode() {
        this.isTail = false;
        this.val = ' ';
        this.children = new LinkedList<>();
    }

    public TrieNode(char val) {
        this.val = val;
        this.isTail = false;
        this.children = new LinkedList<>();
    }

    protected TrieNode getChild(char val) {
        for (TrieNode n : children) {
            if (n.val == val) {
                return n;
            }
        }

        return null;
    }

    protected TrieNode addChild(char val) {
        var child = new TrieNode(val);
        this.children.add(child);
        return child;
    }

}
