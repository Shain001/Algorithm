package com.oo.bookManagement;

import java.util.*;

import static com.oo.bookManagement.BookManagementSystem.Record.RecordStatus.*;

public class BookManagementSystem {
    static class BookFactory {
        public static Book createBook(String name, int id, Book.BookType type) throws Exception {
            switch (type) {
                case ELECTRIC -> {
                    return new ElectricBook(name, id);
                }
                case PAPER -> {
                    return new PaperBook(name, id);
                }
                default -> throw new Exception("Invalid Type");
            }
        }
    }

    abstract static class Book {
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

    static class PaperBook extends Book {
        public PaperBook(String name, int id) {
            super(name, id, BookType.PAPER);
        }
    }

    static class ElectricBook extends Book {
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

        public void addRecord(Record record) {
            this.openRecords.add(record);
            updateCurrentBorrows(record);
            System.out.printf("Record id %s has been added to customer's openRecords, customer id: %s %n", record.id, this.id);
        }

        private void updateCurrentBorrows(Record record) {
            for (Book b : record.unreturnedBooks) {
                currentBorrows.put(b, record);
            }
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
            if (!unreturnedBooks.contains(book)) {
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

    interface BookRepository {
        void addBook(String name, int id, Book.BookType type) throws Exception;

        Book findBookById(int id) throws Exception;
        List<Book> findBooksById(List<Integer> ids) throws Exception;

        List<Book> findBooksByName(String name);
        List<Book> findAvailableBooksByName(List<String> names);
    }

    class InMemoryBookRepository implements BookRepository {
        private final Map<Integer, PaperBook> paperBookIdMap;
        private final Map<Integer, ElectricBook> electricBookIdMap;
        private final Map<String, Set<PaperBook>> paperBookNameMap;
        private final Map<String, Set<ElectricBook>> electricBookNameMap;

        public InMemoryBookRepository() {
            this.paperBookNameMap = new HashMap<>();
            this.electricBookNameMap = new HashMap<>();
            this.paperBookIdMap = new HashMap<>();
            this.electricBookIdMap = new HashMap<>();
        }

        @Override
        public void addBook(String name, int id, Book.BookType type) throws Exception {
            var book = BookFactory.createBook(name, id, type);
            switch (type) {
                case ELECTRIC -> {
                    this.electricBookIdMap.put(id, (ElectricBook) book);
                    var updatedElectric = this.electricBookNameMap.getOrDefault(name, new HashSet<>());
                    updatedElectric.add((ElectricBook) book);
                    this.electricBookNameMap.put(name, updatedElectric);
                }
                case PAPER -> {
                    this.paperBookIdMap.put(id, (PaperBook) book);
                    var updatedPaper = this.paperBookNameMap.getOrDefault(name, new HashSet<>());
                    updatedPaper.add((PaperBook) book);
                    this.paperBookNameMap.put(name, updatedPaper);
                }
            }
        }

        @Override
        public Book findBookById(int id) throws Exception {
            if (!paperBookIdMap.containsKey(id) && !electricBookIdMap.containsKey(id)) {
                throw new Exception("invalid book id");
            } else {
                return paperBookIdMap.containsKey(id) ? paperBookIdMap.get(id) : electricBookIdMap.get(id);
            }
        }

        @Override
        public List<Book> findBooksById(List<Integer> ids) throws Exception {
            var books = new ArrayList<Book>();

            for (Integer i : ids) {
                var book = this.findBookById(i);
                books.add(book);
            }

            return books;
        }

        @Override
        public List<Book> findBooksByName(String name) {
            var result = new ArrayList<Book>();

            result.addAll(electricBookNameMap.getOrDefault(name, Collections.emptySet()));
            result.addAll(paperBookNameMap.getOrDefault(name, Collections.emptySet()));

            return result;
        }

        @Override
        public List<Book> findAvailableBooksByName(List<String> names) {
            var result = new ArrayList<Book>();

            for (String n : names) {
                var books = this.findBooksByName(n);

                if (!books.isEmpty()) {
                    for (Book b : books) {
                        if (b.bookType == Book.BookType.ELECTRIC) {
                            result.add(b);
                            break;
                        }

                        if (b.bookType == Book.BookType.PAPER && b.isAvailable()) {
                            result.add(b);
                            break;
                        }
                    }
                }
            }

            return result;
        }
    }

    interface BorrowService {
        void borrowBooks(Customer customer, List<Book> books);

        void returnBooks(Customer customer, List<Book> books);
    }

    class inStoreBorrowService implements BorrowService {

        @Override
        public void borrowBooks(Customer customer, List<Book> books) {
            var record = new Record(books, customer);
            customer.addRecord(record);
        }

        @Override
        public void returnBooks(Customer customer, List<Book> books) {
            customer.returnBooks(books);
        }
    }

    private final BookRepository repository;
    private final Map<Integer, Customer> customers;
    private final BorrowService service;

    public BookManagementSystem() {
        this.service = new inStoreBorrowService();
        this.repository = new InMemoryBookRepository();
        this.customers = new HashMap<>();
    }

    public void borrowBook(List<String> names, int customerId) throws Exception {
        var customer = getCustomer(customerId);

        var availableBooks = this.repository.findAvailableBooksByName(names);

        service.borrowBooks(customer, availableBooks);
    }

    public void returnBook(int customerId, List<Integer> ids) throws Exception {
        var customer = getCustomer(customerId);

        var books = this.repository.findBooksById(ids);

        service.returnBooks(customer, books);

    }


    public List<Book> searchBookByName(String name) {
        return this.repository.findBooksByName(name);
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

    public void addBook(String name, int id, Book.BookType type) throws Exception {
        this.repository.addBook(name, id, type);
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

        testcase1(system);
    }

    private static void testcase1(BookManagementSystem system) throws Exception {
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
