package com.design.snakeGame_L353;

public class Test {
    public static void main(String[] args) throws Exception {
        var game = new SnakeGame(3,3,new int[][]{{0,0},{0,2,}, {0,1}, {2,2},{0,1}});
        System.out.println(game.move("R"));
//        System.out.println(game.move("D"));
        System.out.println(game.move("R"));
//        System.out.println(game.move("U"));
//        System.out.println(game.move("L"));
//        System.out.println(game.move("U"));
    }
}
