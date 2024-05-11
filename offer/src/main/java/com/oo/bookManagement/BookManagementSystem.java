package com.oo.bookManagement;

import java.util.*;

import static com.oo.bookManagement.BookManagementSystem.Record.RecordStatus.*;

public class BookManagementSystem {
    abstract class Book {
        enum BookType {
            PAPER, ELECTRIC
        }

        private final int id;
        private final BookType bookType;
        final String name;
        private Record currentRecord;

        public Book(String name, int id, BookType type) {
            this.bookType = type;
            this.id = id;
            this.name = name;
        }

        public boolean isAvailable() {
            return currentRecord == null;
        }
    }

    class PaperBook extends Book{
        public PaperBook(String name, int id) {
            super(name, id, BookType.PAPER);
        }
    }

    class ElectricBook extends Book {
        public ElectricBook(String name, int id) {
            super(name, id, BookType.ELECTRIC);
        }
    }

    class Customer {
        private final int id;
        private final List<Record> closedRecords;
        private final List<Record> openRecords;
        private final Map<Book, Record> currentBorrows;

        public Customer(int id) {
            this.id = id;
            this.closedRecords = new ArrayList<>();
            this.openRecords = new ArrayList<>();
            currentBorrows = new HashMap<>();
        }

        public void borrowBook(Record record) {
            this.openRecords.add(record);
            for (Book b : record.unreturnedBooks) {
                currentBorrows.put(b, record);
            }
            System.out.printf("Record id %s has been added to customer's openRecords, customer id: %s %n", record.id, this.id);
        }

        public void returnBooks(List<Book> books) {
            for (Book b : books) {
                var record = currentBorrows.get(b);
                var isClosedAfterReturn = record.returnBook(b);
                System.out.printf("Book name: %s, id: %s is returned %n", b.name, b.id);
                checkClosedRecord(record, isClosedAfterReturn);
            }
        }

        private void checkClosedRecord(Record record, boolean isClosedAfterReturn) {
            if (isClosedAfterReturn) {
                System.out.printf("Record %s has been closed since all book returned %n", record.id);
                closedRecords.add(record);
            }
        }
    }

    class Record {
        enum RecordStatus {
            IN_PROGRESS, RETURNED, HALF_RETURNED
        }
        private String id;
        private final List<Book> unreturnedBooks;
        private final List<Book> returnedBooks;
        private RecordStatus status;
        private int startDate;
        private int endDate;
        private Customer borrower;

        public Record(List<Book> books, Customer borrower) {
            this.id = UUID.randomUUID().toString();
            this.unreturnedBooks = new ArrayList<>();
            this.returnedBooks = new ArrayList<>();
            this.startDate = 123;
            this.status = IN_PROGRESS;
            this.borrower = borrower;
            updateBookStatus(books);
        }

        private void updateBookStatus(List<Book> books) {
            for (Book b : books) {
                b.currentRecord = this;
                unreturnedBooks.add(b);
                System.out.printf("Book id : %s has been marked as borrowed %n", b.id);
            }
        }

        public boolean returnBook(Book book) {
            if (! unreturnedBooks.contains(book)) {
//                throw new RecordUpdateException();
                System.out.println("book not exist");
                return false;
            }

            unreturnedBooks.remove(book);
            returnedBooks.add(book);
            updateHalfReturned();

            if (unreturnedBooks.isEmpty()) {
                closeRecord();
                return true;
            }

            return false;

        }

        private void updateHalfReturned() {
            this.status = HALF_RETURNED;
        }

        private void closeRecord() {
            this.endDate = 234;
            this.status = RETURNED;
        }
    }

    private final List<PaperBook> paperBooks;
    private final List<ElectricBook> electricBooks;
    private final Map<Integer, Customer> customers;

    public BookManagementSystem() {
        this.paperBooks = new ArrayList<>();
        this.electricBooks = new ArrayList<>();
        this.customers = new HashMap<>();
    }

    public void borrowBook(List<String> names, int customerId) throws Exception {
        var customer = getCustomer(customerId);

        var targetBooks = new ArrayList<Book>();
        for (String n : names) {
            var books = searchBook(n);
            if (!books.isEmpty()) {
                for (Book b : books) {
                    if (b.bookType == Book.BookType.ELECTRIC) {
                        targetBooks.add(b);
                        break;
                    }

                    if (b.bookType == Book.BookType.PAPER && b.isAvailable()) {
                        targetBooks.add(b);
                        break;
                    }
                }
            }
        }

        var record = new Record(targetBooks, customer);
        customer.borrowBook(record);
    }

    public void returnBook(int customerId, List<Integer> ids) throws Exception {
        var customer = getCustomer(customerId);
        var books = new ArrayList<Book>();
        for (Integer i : ids) {
            for (Book b : paperBooks) {
                if (b.id == i) {
                    books.add(b);
                }
            }

            for (Book b: electricBooks) {
                if (b.id == i) {
                    books.add(b);
                }
            }
        }
        customer.returnBooks(books);
    }

    public List<Book> searchBook(String name) {
        var result = new ArrayList<Book>();

        for (PaperBook b: paperBooks) {
            if (b.name.equals(name)) {
                result.add(b);
            }
        }

        for (ElectricBook b : electricBooks) {
            if (b.name.equals(name)) {
                result.add(b);
            }
        }

        return result;
    }

    public List<Record> getUserOpenRecords(int customerId) throws Exception {
        var customer = getCustomer(customerId);

        return customer.openRecords;
    }

    public List<Record> getUserClosedRecords(int customerId) throws Exception {
        var customer = getCustomer(customerId);

        return customer.closedRecords;
    }

    public void addCustomer(int id) {
        this.customers.put(id, new Customer(id));
    }

    public void addBook(String name, int id, Book.BookType type) {
        switch (type) {
            case ELECTRIC -> this.electricBooks.add(new ElectricBook(name, id));
            case PAPER -> this.paperBooks.add(new PaperBook(name, id));
        }
    }

    private Customer getCustomer(int id) throws Exception {
        var customer = customers.get(id);
        if (customer == null) {
//            throw new CustomerNotExistException(customerId);
            throw new Exception("User Not Exist");
        }
        return customer;
    }

    public static void main(String[] args) throws Exception {
        var system = new BookManagementSystem();
        system.addBook("Book1", 1, Book.BookType.PAPER);
        system.addBook("Book2", 2, Book.BookType.PAPER);
        system.addBook("Book3", 3, Book.BookType.PAPER);
        system.addBook("Book4", 4, Book.BookType.PAPER);
        system.addBook("Book5", 5, Book.BookType.ELECTRIC);
        system.addBook("Book6", 6, Book.BookType.ELECTRIC);
        system.addBook("Book7", 7, Book.BookType.ELECTRIC);

        system.addCustomer(1);
        system.addCustomer(2);
        system.addCustomer(3);

        system.borrowBook(List.of("Book1", "Book2"), 1);
        system.borrowBook(List.of("Book4", "Book3"), 2);

        System.out.println(system.getUserOpenRecords(1));
        System.out.println(system.getUserOpenRecords(2));

        system.returnBook(1, List.of(1, 2));
        system.returnBook(2, List.of(3, 4));
    }

}
