package com.shain.graph.bfs;

import java.util.*;

public class Matrix01_L542 {
    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != Point.class) {
                return false;
            }

            Point convert = (Point) o;
            return convert.x == this.x && convert.y == this.y;
        }

        @Override
        public int hashCode() {
            return this.x + this.y;
        }
    }
    public int[][] updateMatrix(int[][] mat) {
        int[][] ans = new int[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = getDistance(i, j, mat);
                }
            }
        }

        return ans;
    }

    private int getDistance(int i, int j, int[][] mat) {
        Deque<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        queue.offer(new Point(i, j));
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                var cur = queue.poll();

                if (mat[cur.x][cur.y] == 0) {
                    return count;
                }

                if (cur.x + 1 < mat.length) {
                    Point next = new Point(cur.x + 1, cur.y);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
                if (cur.y + 1 < mat[0].length) {
                    Point next = new Point(cur.x, cur.y + 1);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
                if (cur.x - 1 >= 0) {
                    Point next = new Point(cur.x - 1, cur.y);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
                if (cur.y-1 >= 0) {
                    Point next = new Point(cur.x, cur.y - 1);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }

                size--;
            }


            count++;
        }
        return count;
    }
}
