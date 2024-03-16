package com.design.Trie;

public class Trie {
    TrieNode head;

    public Trie() {
        this.head = new TrieNode();
    }

    public void insert(String word) {
        doInsert(head, word);
    }

    private void doInsert(TrieNode cur, String word) {
        if (word.isBlank()) {
            cur.isTail = true;
            return;
        }

        var node = cur.getChild(word.charAt(0));

        if (node == null) {
            var child = cur.addChild(word.charAt(0));
            doInsert(child, word.substring(1));
        } else {
            doInsert(node, word.substring(1));
        }
    }

    public boolean search(String word) {
        var lastWord = doSearch(head, word);
        return lastWord != null && lastWord.isTail;
    }

    // return null if the whole word not contained in the tree
    // otherwise return the last character's node.
    private TrieNode doSearch(TrieNode head, String word) {
        if (word.isBlank()) {
            return head;
        }

        var child = head.getChild(word.charAt(0));
        return child == null? null: doSearch(child, word.substring(1));
    }

    public boolean startsWith(String prefix) {
        return doSearch(head, prefix) != null;
    }
}
