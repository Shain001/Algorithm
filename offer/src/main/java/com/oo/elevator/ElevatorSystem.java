package com.oo.elevator;

import java.util.*;

import static com.oo.elevator.ElevatorSystem.Elevator.Status.*;

public class ElevatorSystem {
    interface ElevatorHandler {
        void addRequest(Request request);

        void handleRequest();
    }

    class OverloadException extends RuntimeException {
        public OverloadException(int capacity, int currentNumberOfPassengers) {
            super(String.format("Elevator can only take %s people, current number: %s", capacity, currentNumberOfPassengers));
        }
    }

    class InvalidTargetLayerException extends RuntimeException {
        public InvalidTargetLayerException(int low, int high) {
            super(String.format("Elevator can only go between %s and %s", low, high));
        }
    }

    class Elevator implements ElevatorHandler {
        enum Status {
            UP, DOWN, STOP
        }

        private String id;
        private int capacity;
        private List<Passenger> passengers;
        private Status currentStatus;
        private Deque<Request> pendingRequests;
        private Request currentRequest;
        private int currentLayer;

        public Elevator(int capacity) {
            this.id = UUID.randomUUID().toString();
            this.capacity = capacity;
            this.passengers = new ArrayList<>();
            this.pendingRequests = new LinkedList<>();
            this.currentRequest = null;
            this.currentStatus = STOP;
            this.currentLayer = 0;
        }

        @Override
        public void addRequest(Request request) {
            // check capacity;
            if (this.passengers.size() >= capacity) {
                throw new OverloadException(this.capacity, this.passengers.size());
            }
            this.passengers.add(request.owner);
            this.pendingRequests.offerLast(request);
        }

        @Override
        public void handleRequest() {
            while (!pendingRequests.isEmpty()) {
                var cur = pendingRequests.pollFirst();

                if (currentStatus == STOP) {
                    if (cur.to > currentLayer) {
                        this.goesUp(cur);
                    } else {
                        this.goesDown(cur);
                    }
                } else if (currentStatus == UP) {
                    if (cur.to >= currentLayer) {
                        this.goesUp(cur);
                    } else {
                        this.pendingRequests.addLast(cur);
                    }
                } else {
                    if (cur.to <= currentLayer) {
                        this.goesDown(cur);
                    } else {
                        this.pendingRequests.addLast(cur);
                    }
                }
            }
        }

        private void goesUp(Request reqeust) {
            this.currentStatus = UP;
            this.currentRequest = reqeust;
            System.out.printf("Elevator id %s is going %s from %s to layer %s %n", this.id, this.currentStatus.toString(), this.currentLayer, this.currentRequest.to);
            arrive();
        }

        private void goesDown(Request reqeust) {
            this.currentStatus = DOWN;
            this.currentRequest = reqeust;
            System.out.printf("Elevator id %s is going %s from %s to layer %s %n", this.id, this.currentStatus.toString(), this.currentLayer, this.currentRequest.to);
            arrive();
        }

        private void arrive() {
            this.currentLayer = this.currentRequest.to;
            this.currentStatus = STOP;
            this.passengers.remove(this.currentRequest.owner);
            System.out.printf("Elevator id %s arrives layer %s %n", this.id, this.currentRequest.to);

            this.currentRequest = null;
        }
    }

    class Passenger {
        private String passengerId;
        private Request request;

        public Passenger(String passengerId) {
            this.passengerId = passengerId;
            this.request = null;
        }

        public void addRequest(Request request) {
            this.request = request;
        }
    }

    class Request {
        enum RequestStatus {
            PENDING, IN_PROCESS, CLOSED
        }

        private int to;
        private int from;
        private RequestStatus requestStatus;
        private String requestId;
        private final Passenger owner;

        public Request(int to, int from, Passenger owner) {
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

    private ElevatorHandler elevator;
    private Map<String, Passenger> passengers;
    private int height;
    private int low;

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
        var request = new Request(to, from, passenger);
        elevator.addRequest(request);
        handle();
    }

    private Passenger getOrCreatePassenger(String passengerId) {
        if (passengerId.contains(passengerId)) {
            return passengers.get(passengerId);
        } else {
            return new Passenger(passengerId);
        }
    }

    public void goDown(String passengerId, int from, int to) {
        if (to < low) {
            throw new InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new Request(to, from, passenger);
        elevator.addRequest(request);
        handle();
    }

    private void handle() {
        elevator.handleRequest();
    }

    public static void main(String[] args) {

        var system = new ElevatorSystem(10, 1, 15);
        system.goUp("1", 7, 8);
        system.goDown("2", 1, 4);
        system.goUp("3", 2, 1);
    }
}
