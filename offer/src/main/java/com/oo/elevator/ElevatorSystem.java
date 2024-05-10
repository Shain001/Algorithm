package com.oo.elevator;

import java.util.*;

public class ElevatorSystem {

    private interface MoveRequestHandler {
        void addRequest(MoveRequest request);
    }

    private static class OverloadException extends RuntimeException {
        public OverloadException(int capacity, int currentNumberOfPassengers) {
            super(String.format("Elevator can only take %s people, current number: %s", capacity, currentNumberOfPassengers));
        }
    }

    private static class InvalidTargetLayerException extends RuntimeException {
        public InvalidTargetLayerException(int low, int high) {
            super(String.format("Elevator can only go between %s and %s", low, high));
        }
    }

    private class Elevator implements MoveRequestHandler {
        private enum Status {
            UP, DOWN, STOP;

            // only write for practice
            public Status fromString(String direction) {
                for (Status s : Status.values()) {
                    if (s.toString().equalsIgnoreCase(direction)) {
                        return s;
                    }
                }
                // should have an exception here
                return null;
            }
        }

        private String id;
        private final int capacity;
        private final List<Passenger> passengers;
        private final Deque<MoveRequest> pendingRequests;
        private Status currentStatus;
        private MoveRequest currentRequest;
        private int currentLayer;

        public Elevator(int capacity) {
            this.id = UUID.randomUUID().toString();
            this.capacity = capacity;
            this.passengers = new ArrayList<>();
            this.pendingRequests = new LinkedList<>();
            this.currentRequest = null;
            this.currentStatus = Status.STOP;
            this.currentLayer = 0;
        }

        @Override
        public void addRequest(MoveRequest request) {
            if (this.passengers.size() >= capacity) {
                throw new OverloadException(this.capacity, this.passengers.size());
            }

            this.passengers.add(request.owner);
            this.pendingRequests.offerLast(request);
            handleRequest();
        }

        private void handleRequest() {
            while (!pendingRequests.isEmpty()) {
                var cur = pendingRequests.pollFirst();
                if (canHandle(cur)) {
                    doHandle(cur);
                } else {
                    this.pendingRequests.add(cur); // If the top request can't be handled now, put back to queue and process later
                }
            }
        }

        private boolean canHandle(MoveRequest cur) {
            return (currentStatus == Status.STOP) ||
                    (currentStatus == Status.UP && cur.to >= currentLayer) ||
                    (currentStatus == Status.DOWN && cur.to <= currentLayer);
        }

        private void doHandle(MoveRequest request) {
            this.currentRequest = request;

            pick(request);

            send(request);

            closeRequest();
        }

        private void send(MoveRequest request) {
            System.out.printf("Elevator id %s has got passenger %s, start sending %n", this.id, request.owner.passengerId);
            if (this.currentLayer < request.to) {
                move(request.to, Status.UP);
            } else {
                move(request.to, Status.DOWN);
            }
        }

        private void pick(MoveRequest request) {
            System.out.printf("Elevator id %s is picking up passenger %s %n", this.id, request.owner.passengerId);
            if (this.currentLayer < request.from) {
                move(request.from, Status.UP);
            } else {
                move(request.from, Status.DOWN);
            }
        }

        private void move(int to, Status direction) {
            this.currentStatus = direction;
            System.out.printf("Elevator id %s is going %s from %s to layer %s %n", this.id, this.currentStatus, this.currentLayer, to);
            this.currentLayer = to;
            arrive(to);
        }

        private void arrive(int to) {
            this.currentLayer = to;
            this.currentStatus = Status.STOP;
            System.out.printf("Elevator id %s arrives layer %s %n", this.id, to);
        }

        private void closeRequest() {
            this.currentRequest.closeRequest();
            this.currentRequest = null;
        }
    }

    private class Passenger {
        private final String passengerId;
        private final List<MoveRequest> requests;

        public Passenger(String passengerId) {
            this.passengerId = passengerId;
            this.requests = new ArrayList<>();
        }

        public void addRequest(MoveRequest request) {
            this.requests.add(request);
        }
    }

    private class MoveRequest {
        enum RequestStatus {
            PENDING, IN_PROCESS, CLOSED
        }

        private int to;
        private int from;
        private RequestStatus requestStatus;
        private String requestId;
        private final Passenger owner;

        public MoveRequest(int from, int to, Passenger owner) {
            this.to = to;
            this.from = from;
            this.requestStatus = RequestStatus.PENDING;
            this.requestId = UUID.randomUUID().toString();
            this.owner = owner;
        }

        public void closeRequest() {
            this.requestStatus = RequestStatus.CLOSED;
        }
    }

    private final MoveRequestHandler elevator;
    private final Map<String, Passenger> passengers;
    private final int height;
    private final int low;

    public ElevatorSystem(int height, int low, int capacity) {
        this.elevator = new Elevator(capacity);
        this.height = height;
        this.low = low;
        this.passengers = new HashMap<>();
    }

    public void goUp(String passengerId, int from, int to) {
        if (to > height) {
            throw new InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new MoveRequest(from, to, passenger);
        passenger.addRequest(request);
        elevator.addRequest(request);
    }

    public void goDown(String passengerId, int from, int to) {
        if (to < low) {
            throw new InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new MoveRequest(to, from, passenger);
        passenger.addRequest(request);
        elevator.addRequest(request);
    }

    private Passenger getOrCreatePassenger(String passengerId) {
        passengers.putIfAbsent(passengerId, new Passenger(passengerId));
        return passengers.get(passengerId);
    }

    public static void main(String[] args) {
        var system = new ElevatorSystem(10, 1, 15);
        system.goUp("1", 7, 8);
        system.goDown("2", 1, 4);
        system.goUp("3", 2, 1);
    }
}
