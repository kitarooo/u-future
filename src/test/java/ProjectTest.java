import backend.microservices.Book;
import backend.microservices.Library;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Test {
    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("Effective Java", "Joshua Bloch", 2008, "978-0134685991");
        book2 = new Book("Clean Code", "Robert C. Martin", 2008, "978-0132350884");
        book3 = new Book("Design Patterns", "Erich Gamma", 1994, "978-0201633610");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Refactoring", "Martin Fowler", 1999, "978-0201485677");
        library.addBook(book);
        assertEquals(4, library.getAllBooks().size());
        assertTrue(library.getAllBooks().contains(book));
    }

    @Test
    public void testRemoveBook() {
        library.removeBook(book1.getIsbn());
        assertEquals(2, library.getAllBooks().size());
        assertTrue(library.getAllBooks().stream().noneMatch(b -> b.getIsbn().equals(book1.getIsbn())));
    }

    @Test
    public void testSearchByTitle() {
        List<Book> result = library.searchByTitle("Effective Java");
        assertEquals(1, result.size());
        assertEquals(book1, result.get(0));
    }

    @Test
    public void testSearchByAuthor() {
        List<Book> result = library.searchByAuthor("Joshua Bloch");
        assertEquals(1, result.size());
        assertEquals(book1, result.get(0));
    }

    @Test
    public void testSearchByYear() {
        List<Book> result = library.searchByYear(2008);
        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }
}

