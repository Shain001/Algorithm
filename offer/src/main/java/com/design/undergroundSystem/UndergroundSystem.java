package com.design.undergroundSystem;

import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {
    private final Map<String, Station> stationMap;
    private final Map<Integer, Passenger> passengerMap;

    public UndergroundSystem() {
        this.stationMap = new HashMap<>();
        this.passengerMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        var passenger = passengerMap.getOrDefault(id, new Passenger(id));
        var from = stationMap.getOrDefault(stationName, new Station(stationName));
        passengerMap.putIfAbsent(id, passenger);
        stationMap.putIfAbsent(stationName, from);

        passenger.checkIn(from, t);
    }
    
    public void checkOut(int id, String stationName, int t) {
        if (!passengerMap.containsKey(id)) {
            return;
        }

        var passenger = passengerMap.get(id);
        var to = stationMap.getOrDefault(stationName, new Station(stationName));
        stationMap.putIfAbsent(stationName, to);

        passenger.checkout(to, t);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        if (!stationMap.containsKey(startStation) || !stationMap.containsKey(endStation)) {
            return -1;
        }

        var from = stationMap.get(startStation);
        var to = stationMap.get(endStation);

        return  to.getAverageTime(from);
    }
}