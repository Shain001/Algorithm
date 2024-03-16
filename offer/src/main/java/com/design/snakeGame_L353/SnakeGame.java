package com.design.snakeGame_L353;

/**
 * 1. points
 * 2. width and length
 */
public class SnakeGame {
    private final int Width;
    private final int Height;
    private final Pair INITIAL = new Pair(0,0);
    private Snake snake;
    private Food food;
    private int points;
    private int sizeOfGraph;
    /**
     *
     * @param width
     * @param height
     * @param food
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.points = 0;
        this.Width = width-1;
        this.Height = height-1;
        this.food = new Food(food);
        this.snake= new Snake(INITIAL);
        this.sizeOfGraph = width * height;
    }

    /**
     * 返回移动后的得分
     * @param direction
     * @return
     */
    public int move(String direction) throws Exception {
        // 0. get the current head;
        var head = this.snake.getHead();
        // 1. check if it is out of boarder;
        var newHead = doMove(direction, head);
        if (newHead == null) {
            return -1;
        }

        // 2. if not -> check if the next direction is food;
        // 3. if it is food -> move with food; also, update next food; at the same check snake size to see if it wins.
        // 4. if it is not food -> move without food;
        if (newHead.equals(this.food.getCurFood())) {
            int newSize = this.snake.movesWithFood(food.getCurFood());
            this.food.updateFood();
            this.points++;
            return newSize > this.sizeOfGraph? -1: this.points;
        } else {
            this.snake.movesWithoutNewFood(newHead);
        }

        return this.points;
    }

    private Pair doMove(String direction, Pair head) throws Exception {
        var newHead = switch (direction) {
            case "R" -> new Pair(head.row(), head.col() + 1);
            case "L" -> new Pair(head.row(), head.col() - 1);
            case "U" -> new Pair(head.row() - 1, head.col());
            case "D" -> new Pair(head.row() + 1, head.col());
            default -> throw new Exception("Error Input");
        };

        return isValid(newHead)? newHead: null;
    }

    private boolean isValid(Pair newHead) {
        return newHead.row() <= this.Height && newHead.row() >= 0 && newHead.col() <= this.Width && newHead.col() >= 0;
    }

}
