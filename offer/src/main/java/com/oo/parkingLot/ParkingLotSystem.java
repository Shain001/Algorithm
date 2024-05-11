//package com.oo.parkingLot;
//
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import static com.oo.parkingLot.ParkingLotSystem.Vehicle.VehicleType.CAR;
//import static com.oo.parkingLot.ParkingLotSystem.Vehicle.VehicleType.ELECTRIC;
//
///**
// *
// * 1. objects
// * ParkingLot(int capaciy)
// * Floors (Info Portal; ParkingSpots; DisplayBoard)
// * ParkingSpots
// * InfoPotal
// * Customers
// * Cars [Car, Truck, Van, MotorCycle]
// * Entry (ParkingDisplayBoard [GroundFloor])
// * Exit Point
// * Parking Ticket (fees)
// *
// * Exit Pannel
// * Parking Attendent
// *
// * 2. function
// *
// * payAtPannel
// * payAtAttendent
// * payAtInfoPortal
// * payByCash
// * payByCard
// *
// * 3. non-functional
// * show message when full at entrance panel
// * each floor diplayboard show fee
// *
// * payment calculate system
// *
// *
// **/
//
//public class ParkingLotSystem {
//    class VehicleFactory {
//        public static Vehicle createVehicle(Vehicle.VehicleType type) {
//            switch (type) {
//                case CAR: return new Car();
//                case VAN: return new Van();
//                case TRUCK: return new Truck();
//                case ELECTRIC: return new Electric();
//                case MOTORCYCLE: return new Motorcycle();
//                default: return null; // todo: exception
//            }
//        }
//    }
//    abstract static class Vehicle {
//        enum VehicleType {
//            CAR, TRUCK, VAN, MOTORCYCLE, ELECTRIC
//        }
//        protected VehicleType type;
//
//        public Vehicle(VehicleType type) {
//            this.type = type;
//        }
//    }
//
//    static class Electric extends Vehicle{
//        public Electric() {
//            super(ELECTRIC);
//        }
//    }
//
//    static class Car extends Vehicle {
//        public Car() {
//            super(CAR);
//        }
//    }
//
//    static class Truck extends Vehicle {
//        public Truck() {
//            super(VehicleType.TRUCK);
//        }
//    }
//
//    static class Van extends Vehicle {
//        public Van() {
//            super(VehicleType.VAN);
//        }
//    }
//
//    static class Motorcycle extends Vehicle {
//        public Motorcycle() {
//            super(VehicleType.MOTORCYCLE);
//        }
//    }
//
//    // todo: this may have differnt type of ticket?
//    class Ticket {
//        private int startTime;
//        private int endTime;
//        private Customer customer;
//    }
//
//    class Customer {
//        private Vehicle vehicle;
//        private int customerId;
//        private Ticket ticket;
//        private Payment payment;
//
//        private Customer(int id, String vehicleType) {
//            this.vehicle = VehicleFactory.createVehicle(Vehicle.VehicleType.valueOf(vehicleType.toUpperCase()));
//            this.customerId = id;
//            this.ticket = null;
//            this.payment = null;
//        }
//    }
//
//    public class Payment {
//        enum PaymentType {
//            credit, cash
//        }
//        private double amount;
//        private PaymentType type;
//    }
//
//    class ParkingAttend {
//        private int id;
//    }
//
//    abstract class Floor {
//        private int layer;
//        private DisplayBoard displayBoard;
//        private InfoPortal portal;
//        private List<ParkingSlot> parkingSlots;
//
//        public ParkingSlot getParkingSlotByType(String type) {
//
//            return null; // todo:
//        }
//    }
//
//    class GroundFloor extends Floor {
//        private Map<Integer, Entry> entries;
//        private List<Exit> exits;
//
//        public void assignTicket(Customer customer, int entryId, int startTime) {
//            var entry = entries.get(entryId);
//            var ticket = entry.getTicket(startTime);
//            customer.ticket = ticket;
//        }
//    }
//
//    class NormalFloor extends Floor {
//
//    }
//
//    class DisplayBoard {
//        private String message;
//    }
//
//    class InfoPortal {
//        private int id;
//    }
//
//    class Rate {
//        private double pricePerHour;
//    }
//
//    abstract class ParkingSlot {
//        enum ParkingSlotType {
//            COMPACT, LARGE, HANDICAPPED, MOTORCYCLE, ELECTRIC
//        }
//
//        private String id;
//        private ParkingSlotType slotType;
//        private Vehicle vehicle;
//        private Rate rate;
//
//        public ParkingSlot(ParkingSlotType type, Rate rate) {
//            this.id = UUID.randomUUID().toString();
//            this.slotType = type;
//            this.rate = rate;
//        }
//        public boolean isAvailable() {
//            return this.vehicle == null;
//        }
//    }
//
//    class CompactParkingSlot extends ParkingSlot {
//
//        public CompactParkingSlot(ParkingSlotType type, Rate rate) {
//            super(ParkingSlotType.COMPACT, rate);
//        }
//    }
//
//    class LargeParkingSlot extends ParkingSlot {
//
//        public LargeParkingSlot(ParkingSlotType type, Rate rate) {
//            super(ParkingSlotType.LARGE, rate);
//        }
//    }
//
//    class HandicappedParkingSlot extends ParkingSlot {
//
//        public HandicappedParkingSlot(ParkingSlotType type, Rate rate) {
//            super(ParkingSlotType.HANDICAPPED, rate);
//        }
//    }
//
//    class ElectricParkingSlot extends ParkingSlot {
//        public ElectricParkingSlot(ParkingSlotType type, Rate rate) {
//            super(type, rate);
//        }
//    }
//
//    class MotorcycleParkingSlot extends ParkingSlot {
//
//        public MotorcycleParkingSlot(ParkingSlotType type, Rate rate) {
//            super(ParkingSlotType.MOTORCYCLE, rate);
//        }
//    }
//    class ExitPannel {
//        private int id;
//    }
//
//    class Entry {
//        private int id;
//
//        public Ticket getTicket(int startTime) {
//            return null;
//        }
//    }
//
//    class Exit {
//        private int id;
//        private ParkingAttend attend;
//        private ExitPannel pannel;
//    }
//
//    enum PaymentPlace {
//        EXIT, EXIT_PANEL, INFO_PORTAL
//    }
//
//    enum PaymentMethod {
//        cash, credit
//    }
//
//    private List<NormalFloor> floors;
//    // contains entries
//    private GroundFloor groundFloor;
//    private int capacity;
//    private Map<Integer, Customer> customers;
//
//    public void park(int customerId, String carType, int parkTime, int entryId, String wantedParkSlotType) {
//        var customer = this.getOrCreateCustomer(customerId, carType);
//        groundFloor.assignTicket(customer, entryId, parkTime);
//        var selectedParkingSlot = this.findAvailableSlotByType(wantedParkSlotType);
//        customer.park(selectedParkingSlot);
//    }
//
//    private ParkingSlot findAvailableSlotByType(String type) throws Exception {
//        ParkingSlot found;
//        for (Floor floor: floors) {
//            found = floor.getParkingSlotByType(type);
//            if (found != null) {
//                return found;
//            }
//        }
//
//        throw new Exception(String.format("No %s type parking slot", type));
//    }
//
//    public void exit(int customerId, int endTime, int exitId, PaymentPlace paymentPlace, PaymentMethod paymentMethod) {
//        var customer = customers.get(customerId);
//        customer.payFees(endTime, paymentPlace, paymentMethod);
//        var exit = groundFloor.getExitById(exitId);
//        customer.leave(exit);
//    }
//
//    private Customer getOrCreateCustomer(int customerId, String carType) {
//        if (customers.containsKey(customerId)) {
//            return customers.get(customerId);
//        }
//
//        var customer = new Customer(customerId, carType);
//
//        customers.put(customerId, customer);
//
//        return customer;
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
//
