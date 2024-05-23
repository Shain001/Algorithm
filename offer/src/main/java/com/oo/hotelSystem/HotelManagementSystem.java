package com.oo.hotelSystem;

import java.util.*;

import static com.oo.hotelSystem.HotelManagementSystem.Booking.BookingStatus.CANCELED;
import static com.oo.hotelSystem.HotelManagementSystem.Booking.BookingStatus.PENDING;
import static com.oo.hotelSystem.HotelManagementSystem.Room.RoomStatus.AVAILABLE;
import static com.oo.hotelSystem.HotelManagementSystem.Room.RoomStatus.CHECKED_IN;
import static com.oo.hotelSystem.HotelManagementSystem.Room.RoomType.*;

public class HotelManagementSystem {
    static class RoomFactory {
        public static Room createRoom(int id, Room.RoomType type) {
            return switch (type) {
                case SINGLE -> new SingleRoom(id);
                case DOUBLE -> new DoubleRoom(id);
                case SUIT -> new SuitRoom(id);
            };
        }
    }

    static abstract class Room {
        enum RoomType {
            SINGLE, DOUBLE, SUIT;
        }

        enum RoomStatus {
            AVAILABLE, CHECKED_IN;
        }

        private int id;
        private final RoomType roomType;
        private RoomStatus status;
        private final List<Booking> bookings;

        public Room(int id, RoomType type) {
            this.id = id;
            this.roomType = type;
            this.status = AVAILABLE;
            this.bookings = new ArrayList<>();
        }

        protected void addBooking(Booking booking) {
            this.bookings.add(booking);
        }

        protected void checkIn(Booking booking) {
            this.bookings.remove(booking);
            this.status = CHECKED_IN;
        }

        protected void checkIn() {
            this.status = CHECKED_IN;
        }

        protected void checkOut() {
            this.status = AVAILABLE;
        }

        protected boolean checkAvailability(int startTime, int endTime) {
            if (this.bookings.isEmpty()) {
                return true;
            }

            for (Booking b : bookings) {
                // todo: update logic here
                if (b.startTime <= endTime && b.endTime >= startTime) {
                    return false;
                }
            }

            return true;
        }
    }

    static class SingleRoom extends Room {
        public SingleRoom(int id) {
            super(id, SINGLE);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom(int id) {
            super(id, DOUBLE);
        }
    }

    static class SuitRoom extends Room {
        public SuitRoom(int id) {
            super(id, SUIT);
        }
    }

    class Customer {
        private final int id;
        private Room room;
        private List<Booking> bookings;

        public Customer(int id) {
            this.id = id;
        }

        public void addBooking(Booking booking) {
            this.bookings.add(booking);
        }

        public void checkIn(Room room) {
            this.room = room;
        }

        public void checkout() {
            this.room = null;
        }
    }

    class Booking {
        enum BookingStatus {
            PENDING, CHECKED_IN, CANCELED;
        }

        private Customer customer;
        private BookingStatus status;
        private final Room room;
        private final int startTime;
        private final int endTime;
        private final int id;

        public Booking(int id, Customer customer, Room room, int startTime, int endTime) {
            this.id = id;
            this.customer = customer;
            this.room = room;
            this.startTime = startTime;
            this.endTime = endTime;
            this.status = PENDING;
        }

        public void cancel() {
            this.status = CANCELED;
        }

        public void checkIn() {
            this.status = BookingStatus.CHECKED_IN;
        }
    }

    interface RoomRepository {
        List<Room> findAvailableRoomsByDateType(int start, int end, Room.RoomType type);

        void addRoom(Room room);
    }

    interface BookingRepository {
        void addBooking(Customer customer, Booking booking);

        void cancleBooking(Customer customer, Booking booking);

        Booking getBookingById(int id);
    }

    abstract class CustomerService {
        protected final RoomRepository roomRepo;

        public CustomerService(RoomRepository roomRepo) {
            this.roomRepo = roomRepo;
        }

        abstract public List<Room> searchAvailableRooms(int start, int end, Room.RoomType type);

        abstract public void checkIn(Customer customer, Room room, Optional<Booking> booking);

        abstract public void checkout(Customer customer);

        abstract public void calcelBooking(Customer customer, Booking booking);
    }

    class CustomerServiceIml extends CustomerService {
        public CustomerServiceIml(RoomRepository roomRepo) {
            super(roomRepo);
        }

        @Override
        public List<Room> searchAvailableRooms(int start, int end, Room.RoomType type) {
            return roomRepo.findAvailableRoomsByDateType(start, end, type);
        }

        @Override
        public void checkIn(Customer customer, Room room, Optional<Booking> booking) {
            customer.checkIn(room);
            room.checkIn(customer);
            if (booking.isPresent()) {
                booking.checkIn();
            }
        }

        @Override
        public void checkout(Customer customer);

        @Override
        public void calcelBooking(Customer customer, Booking booking);
    }

    class InMemoRoomRepository implements RoomRepository {
        List<Room> rooms;

        // todo: costructor

        @Override
        public List<Room> findAvailableRoomsByDateType(int start, int end, Room.RoomType type) {
            var results = new ArrayList<Room>();
            for (Room r : rooms) {
                if (r.roomType == type && r.checkAvailability(start, end)) {
                    results.add(r);
                }
            }

            return results;
        }

        @Override
        public void addRoom(Room room) {
            this.rooms.add(room);
        }
    }


    private RoomRepository roomRepo;
    private BookingRepository bookingRepo;
    private CustomerService customerService;
    private Map<Integer, Customer> customers;

    public HotelManagementSystem() {
        this.roomRepo = new InMemoRoomRepository();
        this.bookingRepo = new ();
        this.customerService = new CustomerServiceIml(bookingRepo);
        this.customers = new HashMap<>();
    }

    public List<Room> findAvailableRoomsByDateType(int start, int end, String type) {
        return roomRepo.findAvailableRoomsByDateType(start, end, Room.RoomType.valueOf(type.toUpperCase()));
    }

    public void checkInWithoutBooking(int customerId, Room room) {
        var customer = getOrCreateCustomer(customerId);

        this.customerService.checkIn(customer, room, Optional.empty());
    }

    public void checkInWithBooking(int customerId, int bookingId) {
        var customer = getOrCreateCustomer(customerId);
        var booking = bookingRepo.getBookingById(bookingId);
        this.customerService.checkIn(customer, booking.room, Optional.empty());
    }

    private Customer getOrCreateCustomer(int customerId) {
        var customer = customers.getOrDefault(customerId, new Customer(customerId));
        customers.putIfAbsent(customerId, customer);
        return customer;
    }


    public static void main(String[] args) {

    }
}
