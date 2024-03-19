package com.shain.graph.bfs;

import java.util.*;

class FriendsVideo_L1311 {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(id);
        boolean[] visited = new boolean[friends.length];
        visited[id] = true;
        int l = 0;

        while (l < level) {
            int size = queue.size();

            while (size > 0) {
                int curId = queue.poll();

                for (Integer friend : friends[curId]) {
                    if (!visited[friend]) {
                        queue.offer(friend);
                        visited[friend] = true; // 立即将朋友标记为已访问
                    }
                }
                size--;
            }

            l++;
        }

        Map<String, Integer> videoCount = new HashMap<>();

        for (Integer friendId : queue) {
            List<String> videos = watchedVideos.get(friendId);
            for (String video : videos) {
                videoCount.put(video, videoCount.getOrDefault(video, 0) + 1);
            }
        }

        // 按次数和字典序排序
        List<String> result = new ArrayList<>(videoCount.keySet());
        Collections.sort(result, Comparator.comparingInt((String a) -> videoCount.get(a)).thenComparing(a -> a));

        return result;
    }
}