package com.design.tweeter_L355;

import java.util.*;

/**
 * 另一种选择是 采用 push 模式：
 * 1。 user类中添加一个 news field 维护news 以及 followers field
 * 2。 每当一个用户发送tweet， 想所有followers 推送这个tweet 并更新news 列表。
 * challenge：
 * 当一个用户新关注了一个用户时， news中将无法包括新关注人的历史消息。 所以需要在 follow 方法中更新 news 列表， 将新关注的人的历史消息 merge到
 * news 列表中。
 *
 * 方案1： 类似当前采用的实现， 新关注user时， 从它的历史消息中 获取 N 条， N 为 news的最大数量， 然后合并筛选。
 * drawback： 会获取多余的消息， 效率低。
 * 方案2： 获取新关注user的消息时， 检查当前时间 t， 如果：
 * 1。 当前用户的news 已经满了， 并且 news 时间属于 x - y （y为当前时间）， 且 x 要 早于 新关注user的最后一条tweet时间， 则不需要更新。
 * 2。 如果没满，  且 x 要 早于 新关注user的最后一条tweet时间， 则 从新关注user中获取 M 条 x之前发送的消息。
 * 3。 不管news满还是没满， 新用户最新的tweet 在 x， y 之间 ， 则要开始merge。。
 * 4。 略
 * 总之就是 把 news 和新用户的 posts 想象成 两列火车， 只要两个车有重叠， 就需要merge。 merge过程懒得想了。
 */
public class User {
    private int userId;
    private final List<User> follows;
    // use as stack
    private final Deque<Tweet> posts;

    public User(int userId) {
        this.userId = userId;
        this.follows = new LinkedList<>();
        this.posts = new LinkedList<>();
    }

    // pull 模式， 效率低
    protected List<Integer> getNews(int n) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t2.postedAt.compareTo(t1.postedAt));
        List<Integer> ans = new ArrayList<>();

        // for each user, add n posts to the pq, so that we can ensure the order.
        addToPq(pq, this, n);

        for (User u : follows) {
            addToPq(pq, u, n);
        }

        while (n > 0 && !pq.isEmpty()) {
            ans.add(pq.poll().id);
            n--;
        }

        return ans;
    }

    // 尝试使用 迭代器 模拟 合并 k 个有序链表， 不行
    // 因为 迭代器在 pq比较过程中被推进了。
    // 如果想要继续这种方法， 应该要自建一个 Node 类， 把 posts 存成链表的形式。
//    protected List<Integer> getNews_pullV2(int n) {
//        PriorityQueue<Iterator<Tweet>> pq = new PriorityQueue<>((t1, t2) -> t2.next().postedAt.compareTo(t1.next().postedAt));
//        List<Integer> ans = new ArrayList<>();
//
//        addToPq_V2(this, pq);
//        for (User follow: follows) {
//            addToPq_V2(follow, pq);
//        }
//
//        while (n > 0 && !pq.isEmpty()) {
//            Iterator<Tweet> cur = pq.poll();
//
//            ans.add(cur.next().id);
//
//            if (cur.hasNext()) {
//                pq.add(cur);
//            }
//        }
//
//        return ans;
//    }
//
//    private void addToPq_V2(User user, PriorityQueue<Iterator<Tweet>> pq) {
//        if (!user.posts.isEmpty()) {
//            pq.add(user.posts.iterator());
//        }
//    }

    private void addToPq(PriorityQueue<Tweet> pq, User u, int n) {
        Iterator<Tweet> iterator = posts.iterator();
        while (n > 0 && iterator.hasNext()) {
            pq.add(iterator.next());
            n--;
        }
    }

    protected void follow(User user) {
        if (this.follows.contains(user)) {
            return;
        }

        this.follows.add(user);
    }

    protected void unfollow(User user) {
        this.follows.remove(user);
    }

    protected void post(Tweet t) {
        this.posts.offerFirst(t);
    }
}
