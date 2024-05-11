package com.oo.elevator;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElevatorSystem_MultipleElevators {

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

    private interface MoveRequestHandler {
        void addRequest(MoveRequest request);

        boolean isAvailable();
    }

    // 状态模式 + 模版模式
    private interface MoveRequestHandlerState {
        void move(int to);

        void stop();

    }

    // 状态模式 + 模版模式
    private abstract class ElevatorStateTemplate implements MoveRequestHandlerState {
        protected Elevator handler;

        private ElevatorStateTemplate(Elevator handler) {
            this.handler = handler;
        }

        @Override
        public final void move(int to) {
            doMove(to);
        }

        protected abstract void doMove(int to);

        @Override
        public void stop() {
            handler.currentStatus = handler.stopState;
            System.out.printf("Elevator id %s arrives layer %s %n", handler.id, handler.currentRequest.to);
        }
    }

    private class ElevatorUpState extends ElevatorStateTemplate {
        private ElevatorUpState(Elevator elevator) {
            super(elevator);
        }

        @Override
        protected void doMove(int to) {
            if (handler.currentLayer < to) {
                System.out.printf("Elevator id %s is going UP from %s to layer %s %n", handler.id, handler.currentLayer, to);
                handler.currentLayer = to;  // Move the elevator up to the target floor
            }
        }
    }

    private class ElevatorDownState extends ElevatorStateTemplate {
        private ElevatorDownState(Elevator elevator) {
            super(elevator);
        }

        @Override
        protected void doMove(int to) {

            if (handler.currentLayer > to) {
                System.out.printf("Elevator id %s is going DOWN from %s to layer %s %n", handler.id, handler.currentLayer, to);
                handler.currentLayer = to;
            }
        }
    }

    private class ElevatorStopState extends ElevatorStateTemplate {
        private ElevatorStopState(Elevator elevator) {
            super(elevator);
        }

        @Override
        protected void doMove(int to) {
            if (handler.currentLayer > to) {
                handler.currentStatus = handler.downState;
            } else {
                handler.currentStatus = handler.upState;
            }

            handler.currentStatus.move(to);
        }
    }


    private class Elevator implements MoveRequestHandler {

        private final int id;
        private final int capacity;
        private final List<Passenger> passengers;
        private final Deque<MoveRequest> pendingRequests;
        private MoveRequest currentRequest;
        private int currentLayer;
        // 状态模式
        private MoveRequestHandlerState currentStatus;
        private final ElevatorStopState stopState;
        private final ElevatorUpState upState;
        private final ElevatorDownState downState;

        public Elevator(int id, int capacity) {
            this.id = id;
            this.capacity = capacity;
            this.passengers = new ArrayList<>();
            this.pendingRequests = new LinkedList<>();
            this.currentRequest = null;
            this.stopState = new ElevatorStopState(this);
            this.upState = new ElevatorUpState(this);
            this.downState = new ElevatorDownState(this);
            this.currentStatus = stopState;
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

        @Override
        public boolean isAvailable() {
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

        private boolean canHandleNow(MoveRequest cur) {
            return (currentStatus == stopState) ||
                    (currentStatus == upState && cur.to >= currentLayer) ||
                    (currentStatus == downState && cur.to <= currentLayer);
        }

        private void doHandle(MoveRequest request) {
            this.currentRequest = request;

            pickPassenger(request);

            sendPassenger(request);

            closeCurrentRequest();
        }

        private void sendPassenger(MoveRequest request) {
            System.out.printf("Elevator id %s has got passenger %s, start sending %n", this.id, request.owner.passengerId);
            this.currentStatus.move(request.to);
            this.currentStatus.stop();
        }

        private void pickPassenger(MoveRequest request) {
            System.out.printf("Elevator id %s is picking up passenger %s %n", this.id, request.owner.passengerId);
            this.currentStatus.move(request.from);
            this.currentStatus.stop();
        }

        private void closeCurrentRequest() {
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
        private MoveRequest.RequestStatus requestStatus;
        private String requestId;
        private final Passenger owner;

        public MoveRequest(int from, int to, Passenger owner) {
            this.to = to;
            this.from = from;
            this.requestStatus = MoveRequest.RequestStatus.PENDING;
            this.requestId = UUID.randomUUID().toString();
            this.owner = owner;
        }

        public void closeRequest() {
            this.requestStatus = MoveRequest.RequestStatus.CLOSED;
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
            throw new InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new MoveRequest(from, to, passenger);
        passenger.addRequest(request);

        var selectedElevator = findClosestElevator(request);
        selectedElevator.addRequest(request);
    }

    public void goDown(String passengerId, int from, int to) {
        if (to < low) {
            throw new InvalidTargetLayerException(low, height);
        }

        var passenger = getOrCreatePassenger(passengerId);
        var request = new MoveRequest(from, to, passenger);
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
            if (!elevator.isAvailable()) {
                continue;
            }

            // 计算距离，考虑方向性
            int distance = Math.abs(elevator.currentLayer - from);
            boolean isSameDirection = (elevator.currentStatus == elevator.upState && from >= elevator.currentLayer) ||
                    (elevator.currentStatus == elevator.downState && from <= elevator.currentLayer) ||
                    (elevator.currentStatus == elevator.stopState);

            if (distance < smallestDistance && isSameDirection) {
                smallestDistance = distance;
                selected = e;
            }
        }

        if (selected == null) {
            // 如果没有合适的电梯，选择最近的备选
            for (MoveRequestHandler e : elevators) {
                Elevator elevator = (Elevator) e;
                if (!elevator.isAvailable()) {
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


    private Passenger getOrCreatePassenger(String passengerId) {
        passengers.putIfAbsent(passengerId, new Passenger(passengerId));
        return passengers.get(passengerId);
    }

    public static void main(String[] args) {
        var system = new ElevatorSystem_MultipleElevators(10, 1, 15, 3);
        system.goUp("1", 7, 8);
        system.goDown("2", 1, 4);
        system.goUp("3", 2, 1);
    }
}
