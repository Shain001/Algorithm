package com.design.undergroundSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Station {
    private String name;
    private Map<Station, Integer> countPeopleMap;
    private Map<Station, Integer> countTimeMap;

    public Station(String name) {
        this.name = name;
        this.countPeopleMap = new HashMap<>();
        this.countTimeMap = new HashMap<>();
    }

    public void addCheckout(Station from, int totalTime) {
        countTimeMap.put(from, countTimeMap.getOrDefault(from, 0) + totalTime);
        countPeopleMap.put(from, countPeopleMap.getOrDefault(from, 0) + 1);
    }

    public double getAverageTime(Station from) {
        if (!countTimeMap.containsKey(from)) {
            return -1;
        }

        var totalPeople = countPeopleMap.get(from);
        var totalTime = countTimeMap.get(from);

        return (double) totalTime / (double) totalPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != getClass()) {
            return false;
        }

        var station = (Station) o;

        return station.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
