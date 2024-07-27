package com.shain.others;

import java.util.*;

public class WebSite_L1152 {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // 1. Group User - Visit
        // time: O(n) ; space: O(n) -> n = length of input array =
        Map<String, List<Visit>> userWeb = new HashMap<>();

        for (int i = 0; i < username.length; i++) {
            userWeb.computeIfAbsent(username[i], k -> new ArrayList<>()).add(new Visit(website[i], timestamp[i]));
        }

        // 2. Group User - Pattern
        // time: O(N * (mLogm * m^3)) ; space: O(N*m)
        // N: number of Users
        // m: number of patterns for each user
        Map<String, Set<Pattern>> userPattern = new HashMap<>();

        for (Map.Entry<String, List<Visit>> entry : userWeb.entrySet()) {
            var userName = entry.getKey();
            var webs = entry.getValue();
            webs.sort(Comparator.comparingInt(o -> o.timestamp));

            for (int i = 0; i < webs.size(); i++) {
                for (int j = i + 1; j < webs.size(); j++) {
                    for (int k = j + 1; k < webs.size(); k++) {
                        var pattern = new Pattern(webs.get(i).website, webs.get(j).website, webs.get(k).website);

                        userPattern.computeIfAbsent(userName, o -> new HashSet<>()).add(pattern);
                    }
                }
            }
        }

        // Group Patterns and count freq
        // time: O(m * N) space: O(m*N)
        // N: number of Users
        // m: number of patterns for each user
        Map<Pattern, Integer> pattenCount = new HashMap<>();

        for (Map.Entry<String, Set<Pattern>> entry : userPattern.entrySet()) {
            var patterns = entry.getValue();
            for (Pattern p : patterns) {
                pattenCount.put(p, pattenCount.getOrDefault(p, 0) + 1);
                pattenCount.merge(p, 1, Integer::sum);
            }
        }

        // Sort
        // time: O(n * logn) -> n = m * N
        // space: O(n)
        PriorityQueue<PatternFreq> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.freq == o2.freq) {
                return o1.pattern.pattern.compareTo(o2.pattern.pattern);
            }
            return Integer.compare(o2.freq, o1.freq);
        });

        for (Map.Entry<Pattern, Integer> entry : pattenCount.entrySet()) {
            var pf = new PatternFreq(entry.getKey(), entry.getValue());
            pq.add(pf);
        }

        return pq.poll().pattern.patternList;

    }

    class Visit {
        private String website;
        private int timestamp;

        public Visit(String web, int time) {
            this.website = web;
            this.timestamp = time;
        }
    }

    class PatternFreq {
        public Pattern pattern;
        public int freq;

        public PatternFreq(Pattern pattern, int freq) {
            this.pattern = pattern;
            this.freq = freq;
        }
    }

    class Pattern {
        public String pattern;
        public List<String> patternList;

        public Pattern(String web1, String web2, String web3) {
            pattern = web1 + "-" + web2 + "-" + web3;
            patternList = List.of(web1, web2, web3);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if (o.getClass() != Pattern.class) {
                return false;
            }

            Pattern converted = (Pattern) o;
            return converted.pattern.equals(this.pattern);
        }

        @Override
        public int hashCode() {
            return this.pattern.hashCode();
        }
    }
}
