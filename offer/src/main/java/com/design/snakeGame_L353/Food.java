package com.design.snakeGame_L353;

import java.util.LinkedList;
import java.util.Queue;

public class Food {
    Queue<Pair> foods;

    public Food(int[][] foods) {
        this.foods = new LinkedList<>();
        for (int[] food: foods) {
            var f = new Pair(food[0], food[1]);
            this.foods.offer(f);
        }
    }

    public Pair getCurFood() {
        return this.foods.peek();
    }

    /**
     * @return return the number of food left
     */
    public int updateFood() {
        if (this.foods.isEmpty()) {
            return 0;
        }
        this.foods.poll();
        return this.foods.size();
    }
}
