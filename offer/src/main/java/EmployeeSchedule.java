import java.util.*;

public class EmployeeSchedule {

    static class TimeInterval {
        String name;
        int start;
        int end;

        TimeInterval(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        // On
        List<TimeInterval> intervals = Arrays.asList(
                new TimeInterval("小明", 1, 2),
                new TimeInterval("梅梅", 1, 3),
                new TimeInterval("阿宝", 2, 10),
                new TimeInterval("阿娟", 5, 15)
        );

        // 使用 TreeMap 记录时间点的变化
        TreeMap<Integer, Set<String>> timeMap = new TreeMap<>();

        // O n*k*logk -> k is the range of time / worst and average time complexity
        // 记录时间点的变化
        for (TimeInterval interval : intervals) {
            for (int time = interval.start; time < interval.end; time++) {
                timeMap.computeIfAbsent(time, k -> new TreeSet<>()).add(interval.name);
            }
        }

        var e = timeMap.firstEntry();
        var startTime = e.getKey();
        var employees = e.getValue();

        // Om -> m = entries数量， worst case m = k
        // 比较集合 Set = O(e) -> e 为集合大小。 worst case e = n
        // 所以 O(m*e) -> O(k*n)
        for (Map.Entry<Integer, Set<String>> entries : timeMap.entrySet()) {
            var curE = entries.getValue();
            var curTime = entries.getKey();

            if (!curE.equals(employees)) {
                System.out.println(startTime + "-" + curTime + ":" + employees.toString());
                startTime = curTime;
                employees = curE;
            }
        }
        System.out.println(startTime + "-" + (timeMap.lastKey()+1) + ":" + employees.toString());
    }
}
