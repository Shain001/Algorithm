package com.oo.elevator;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElevatorSystem_MultipleElevators {
    private interface MoveRequestHandler {
        void addRequest(ElevatorSystem_MultipleElevators.MoveRequest request);

        boolean isAvailale();
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

    private class Elevator implements ElevatorSystem_MultipleElevators.MoveRequestHandler {
        private enum Status {
            UP, DOWN, STOP;

            // only write for practice
            public ElevatorSystem_MultipleElevators.Elevator.Status fromString(String direction) {
                for (ElevatorSystem_MultipleElevators.Elevator.Status s : ElevatorSystem_MultipleElevators.Elevator.Status.values()) {
                    if (s.toString().equalsIgnoreCase(direction)) {
                        return s;
                    }
                }
                // should have an exception here
                return null;
            }
        }

        private final int id;
        private final int capacity;
        private final List<ElevatorSystem_MultipleElevators.Passenger> passengers;
        private final Deque<ElevatorSystem_MultipleElevators.MoveRequest> pendingRequests;
        private ElevatorSystem_MultipleElevators.Elevator.Status currentStatus;
        private ElevatorSystem_MultipleElevators.MoveRequest currentRequest;
        private int currentLayer;

        public Elevator(int id, int capacity) {
            this.id = id;
            this.capacity = capacity;
            this.passengers = new ArrayList<>();
            this.pendingRequests = new LinkedList<>();
            this.currentRequest = null;
            this.currentStatus = ElevatorSystem_MultipleElevators.Elevator.Status.STOP;
            this.currentLayer = 0;
        }

        @Override
        public void addRequest(ElevatorSystem_MultipleElevators.MoveRequest request) {
            if (this.passengers.size() >= capacity) {
                throw new ElevatorSystem_MultipleElevators.OverloadException(this.capacity, this.passengers.size());
            }

            this.passengers.add(request.owner);
            this.pendingRequests.offerLast(request);
            handleRequest();
        }

        @Override
        public boolean isAvailale() {
            return this.passengers.size() < this.capacity;
        }

        private void handleRequest() {
            while (!pendingRequests.isEmpty()) {
                var cur = pendingRequests.pollFirst();
                if (canHandleNow(cur)) {
                    doHandle(cur);
                } else {
                    this.pendingRequests.add(cur); // If the top request can't be handled now, put back to queue and process later
                }
            }
        }

        private boolean canHandleNow(ElevatorSystem_MultipleElevators.MoveRequest cur) {
            return (currentStatus == ElevatorSystem_MultipleElevators.Elevator.Status.STOP) ||
                    (currentStatus == ElevatorSystem_MultipleElevators.Elevator.Status.UP && cur.to >= currentLayer) ||
                    (currentStatus == ElevatorSystem_MultipleElevators.Elevator.Status.DOWN && cur.to <= currentLayer);
        }

        private void doHandle(ElevatorSystem_MultipleElevators.MoveRequest request) {
            this.currentRequest = request;

            pickPassenger(request);

            sendPassenger(request);

            closeCurrentRequest();
        }

        private void sendPassenger(ElevatorSystem_MultipleElevators.MoveRequest request) {
            System.out.printf("Elevator id %s has got passenger %s, start sending %n", this.id, request.owner.passengerId);
            if (this.currentLayer < request.to) {
                move(request.to, ElevatorSystem_MultipleElevators.Elevator.Status.UP);
            } else {
                move(request.to, ElevatorSystem_MultipleElevators.Elevator.Status.DOWN);
            }
        }

        private void pickPassenger(ElevatorSystem_MultipleElevators.MoveRequest request) {
            System.out.printf("Elevator id %s is picking up passenger %s %n", this.id, request.owner.passengerId);
            if (this.currentLayer < request.from) {
                move(request.from, ElevatorSystem_MultipleElevators.Elevator.Status.UP);
            } else {
                move(request.from, ElevatorSystem_MultipleElevators.Elevator.Status.DOWN);
            }
        }

        private void move(int to, ElevatorSystem_MultipleElevators.Elevator.Status direction) {
            this.currentStatus = direction;
            System.out.printf("Elevator id %s is going %s from %s to layer %s %n", this.id, this.currentStatus, this.currentLayer, to);
            this.currentLayer = to;
            arrive(to);
        }

        private void arrive(int to) {
            this.currentLayer = to;
            this.currentStatus = ElevatorSystem_MultipleElevators.Elevator.Status.STOP;
            System.out.printf("Elevator id %s arrives layer %s %n", this.id, to);
        }

        private void closeCurrentRequest() {
            this.currentRequest.closeRequest();
            this.currentRequest = null;
        }
    }

    private class Passenger {
        private final String passengerId;
        private final List<ElevatorSystem_MultipleElevators.MoveRequest> requests;

        public Passenger(String passengerId) {
            this.passengerId = passengerId;
            this.requests = new ArrayList<>();
        }

        public void addRequest(ElevatorSystem_MultipleElevators.MoveRequest request) {
            this.requests.add(request);
        }
    }

    private class MoveRequest {
        enum RequestStatus {
            PENDING, IN_PROCESS, CLOSED
        }

        private int to;
        private int from;
        private ElevatorSystem_MultipleElevators.MoveRequest.RequestStatus requestStatus;
        private String requestId;
        private final ElevatorSystem_MultipleElevators.Passenger owner;

        public MoveRequest(int from, int to, ElevatorSystem_MultipleElevators.Passenger owner) {
            this.to = to;
            this.from = from;
            this.requestStatus = ElevatorSystem_MultipleElevators.MoveRequest.RequestStatus.PENDING;
            this.requestId = UUID.randomUUID().toString();
            this.owner = owner;
        }

        public void closeRequest() {
            this.requestStatus = ElevatorSystem_MultipleElevators.MoveRequest.RequestStatus.CLOSED;
        }
    }

    private final List<MoveRequestHandler> elevators;
    private final Map<String, Passenger> passengers;
    private final int height;
    private final int low;

    public ElevatorSystem_MultipleElevators(int height, int low, int capacity, int numberOfElevators) {
        var countId = new AtomicInteger(1);
        elevators = Stream.generate(() -> new Elevator(countId.getAndIncrement(), capacity)).limit(numberOfElevators).collect(Collectors.toList());
        this.height = height;
        this.low = low;
        this.passengers = new HashMap<>();
    }

    public void goUp(String passengerId, int from, int to) {
        if (to > height) {
            throw new ElevatorSystem_MultipleElevators.InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new ElevatorSystem_MultipleElevators.MoveRequest(from, to, passenger);
        passenger.addRequest(request);

        var selectedElevator = findClosestElevator(request);
        selectedElevator.addRequest(request);
    }

    public void goDown(String passengerId, int from, int to) {
        if (to < low) {
            throw new ElevatorSystem_MultipleElevators.InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new ElevatorSystem_MultipleElevators.MoveRequest(from, to, passenger);
        passenger.addRequest(request);

        var selectedElevator = findClosestElevator(request);
        selectedElevator.addRequest(request);
    }

    private MoveRequestHandler findClosestElevator(MoveRequest request) {
        System.out.printf("Selecting elevator to pick passenger %s%n", request.owner.passengerId);
        int from = request.from;

        MoveRequestHandler selected = null;
        int smallestDistance = Integer.MAX_VALUE; // 追踪最小距离

        for (MoveRequestHandler e : elevators) {
            Elevator elevator = (Elevator) e;
            if (!elevator.isAvailale()) {
                continue;
            }

            // 计算距离，考虑方向性
            int distance = Math.abs(elevator.currentLayer - from);
            boolean isSameDirection = (elevator.currentStatus == Elevator.Status.UP && from >= elevator.currentLayer) ||
                    (elevator.currentStatus == Elevator.Status.DOWN && from <= elevator.currentLayer) ||
                    (elevator.currentStatus == Elevator.Status.STOP);

            if (distance < smallestDistance && isSameDirection) {
                smallestDistance = distance;
                selected = e;
            }
        }

        if (selected == null) {
            // 如果没有合适的电梯，选择最近的备选
            for (MoveRequestHandler e : elevators) {
                Elevator elevator = (Elevator) e;
                if (!elevator.isAvailale()) {
                    continue;
                }

                int distance = Math.abs(elevator.currentLayer - from);
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    selected = e;
                }
            }
        }

        System.out.printf("Selected Elevator %s, current stopped layer %s%n", ((Elevator) selected).id, ((Elevator) selected).currentLayer);
        return selected;
    }


    private ElevatorSystem_MultipleElevators.Passenger getOrCreatePassenger(String passengerId) {
        passengers.putIfAbsent(passengerId, new ElevatorSystem_MultipleElevators.Passenger(passengerId));
        return passengers.get(passengerId);
    }

    public static void main(String[] args) {
        var system = new ElevatorSystem_MultipleElevators(10, 1, 15, 3);
        system.goUp("1", 7, 8);
        system.goDown("2", 1, 4);
        system.goUp("3", 2, 1);
    }
}
