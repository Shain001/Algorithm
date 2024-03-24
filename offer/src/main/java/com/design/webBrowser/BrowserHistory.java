package com.design.webBrowser;

import java.util.Deque;
import java.util.LinkedList;

class BrowserHistory {
    private Deque<String> back;
    private Deque<String> forward;
    private String curPage;

    public BrowserHistory(String homepage) {
        this.back = new LinkedList<>();
        this.forward = new LinkedList<>();
        this.curPage = homepage;
    }

    // 1. add the new page to the back stack;
    // 2. clear all the urls in the forward stack;
    public void visit(String url) {
        if (url.isBlank())
            return;

        this.back.push(curPage);
        curPage = url;

        if (!this.forward.isEmpty())
            this.forward.clear();
    }

    // 1. pop steps times from the back, push the poped url to the forward
    // note: steps > size of back
    public String back(int steps) {
        while (steps > 0) {
            if (this.back.isEmpty())
                break;
            this.forward.push(curPage);
            curPage = this.back.pop();
            steps--;
        }

        return curPage;
    }

    public String forward(int steps) {
        while (steps > 0) {
            if (this.forward.isEmpty())
                break;
            this.back.push(curPage);
            curPage = this.forward.pop();
            steps--;
        }

        return curPage;
    }
}