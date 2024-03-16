package com.design.snakeGame_L353;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Snake should have a body -> contains all the elements contains in the body.
 * Snake should have a head -> the current position of the head. -> discuss later to see if we need this.
 *
 * Have to confirm a data structure to store the body -> ArrayList / LinkedList / Queue / stack / Deque
 *
 * why these candidates: 1. elements should be ordered, 2. need to be able to move the snake.
 *
 * when we move the snake:
 * 1. there is no food -> move the whole body. the length doesn't change
 * 2. it gets the food -> add the new head to the body, original or the body doesn't change. and the new head is the food.
 * 3. if it is the boader of the graph, or if it reaches the body -> it dies.
 *
 * Because: 1. the length of body change -> arrayList is not ideal; 2. we need to update from the head and tail both, deque is the best.
 *
 */
public class Snake {
    private Deque<Pair> body;

    public Snake(Pair start) {
        this.body = new LinkedList<>();
        this.body.offerFirst(start);
    }

    public Pair getHead() {
        return this.body.peek();
    }

    // return the current head position.
    // return -1 if dead
    public int movesWithoutNewFood(Pair newHead) {
        if (!isValid(newHead)) {
            return -1;
        }
        this.body.offerFirst(newHead);
        this.body.pollLast();
        return this.body.size();
    }

    // return the current head position.
    // return -1 if dead
    public int movesWithFood(Pair newHead) {
        if (!isValid(newHead)) {
            return -1;
        }
        this.body.offerFirst(newHead);
        return this.body.size();
    }

    // inValid if it reaches its body, or the boarder
    private Boolean isValid(Pair newHead) {
        return !this.body.contains(newHead);
    }
}
