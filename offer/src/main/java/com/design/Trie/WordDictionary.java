package com.design.Trie;

class WordDictionary {
    TrieNode head;

    public WordDictionary() {
        this.head = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = head;
        for (char c : word.toCharArray()) {
            if (node.getChild(c) == null) {
                node.addChild(c);
            }
            node = node.getChild(c);
        }

        node.isTail = true;
    }

    public boolean search(String word) {
        return doSearch(head, word);
    }

    private boolean doSearch(TrieNode node, String word) {
        if (node == null)
            return false;

        if (word.isEmpty())
            return node.isTail;

        char firstChar = word.charAt(0);
        if (firstChar == '.') {

            for (TrieNode child : node.children) {
                if (child != null && doSearch(child, word.substring(1))) {
                    return true;
                }
            }

            return false;
        } else {
            TrieNode nextNode = node.getChild(firstChar);
            return doSearch(nextNode, word.substring(1));
        }
    }
}