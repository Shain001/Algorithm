package com.shain.graph.dfs;

import java.util.List;

public class RoomsKeys_L841 {
    private boolean[] visted;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        this.visted = new boolean[rooms.size()];
        dfs(0, rooms);

        for (Boolean b : visted) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int cur, List<List<Integer>> rooms) {
        if (this.visted[cur] == true) {
            return;
        }

        this.visted[cur] = true;

        List<Integer> connects = rooms.get(cur);

        for (Integer n: connects) {
            dfs(n, rooms);
        }
    }
}
