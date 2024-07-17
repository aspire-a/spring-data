package yte.intern.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataApplication {


    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringDataApplication.class, args);

        List<Book> exampleBooks = new ArrayList<>();
        exampleBooks.add(new Book(null, "Clean Code", "Robert C. Martin", 11L, LocalDate.parse("2008-07-11")));
        exampleBooks.add(new Book(null, "Clean Agile", "Robert C. Martin", 0L, LocalDate.parse("2019-09-12")));
        exampleBooks.add(new Book(null, "Agile Software Development", "Robert C. Martin", 17L, LocalDate.parse("2002-10-25")));
        exampleBooks.add(new Book(null, "Code Complete 2", "Steve McConnell", 26L, LocalDate.parse("1993-05-30")));
        exampleBooks.add(new Book(null, "Essential Scrum", "Kenneth S. Rubin", 7L, LocalDate.parse("2012-07-20")));
        exampleBooks.add(new Book(null, "Design Patterns", "Gang of Four", 25L, LocalDate.parse("1994-10-01")));
        exampleBooks.add(new Book(null, "Domain Driven Design", "Eric Evans", 16L, LocalDate.parse("2003-08-30")));
        exampleBooks.add(new Book(null, "Test Driven Development", "Kent Beck", 17L, LocalDate.parse("2002-11-18")));
        exampleBooks.add(new Book(null, "Refactoring", "Kent Beck", 7L, LocalDate.parse("2012-03-09")));
        exampleBooks.add(new Book(null, "Extreme Programming Explained", "Kent Beck", 15L, LocalDate.parse("2004-11-26")));


        BookRepository bookRepository = context.getBean(BookRepository.class);

        bookRepository.saveAll(exampleBooks);

        List<Book> foundBooks = bookRepository.findByTitle("Domain Driven Design");
        System.out.println("Find Byt Title: " + foundBooks + "\n");

        foundBooks = bookRepository.findByAgeGreaterThan((long)15, Sort.by("age"));
        System.out.println("Find Byt Age: " + foundBooks + "\n");

        Page<Book> foundBooksAsPage = bookRepository.findByPublishDateAfter(LocalDate.parse("2000-12-31"), PageRequest.of(1, 5));
        System.out.println("Find Byt Publish Date: " + foundBooksAsPage.stream().toList() + "\n");

        foundBooks = bookRepository.findByTitleContaining("Clean");
        System.out.println("Find Byt Title Contaning: " + foundBooks + "\n");

        foundBooks = bookRepository.findByAuthorAndAgeGreaterThan("Robert C. Martin", (long)10);
        System.out.println("Find Byt Author Contaning: " + foundBooks + "\n");

        Integer foundBookNumber = bookRepository.countByAuthor("Kent Beck");
        System.out.println("Find Byt Number of Books: " + foundBookNumber + "\n");

        Boolean isBookExist = bookRepository.existsByAuthor("Martin Fowler");
        System.out.println("Find Byt Author Exist: " + isBookExist + "\n");



        /////////////////////////////////////////////////////Query
        System.out.println("\n\n");


        foundBooks = bookRepository.findByTitleQuery("Domain Driven Design");
        System.out.println("Find Byt Title: " + foundBooks + "\n");

        foundBooks = bookRepository.findByAgeGreaterThanQuery((long)15, Sort.by("age"));
        System.out.println("Find Byt Age: " + foundBooks + "\n");

        foundBooksAsPage = bookRepository.findByPublishDateAfterQuery(LocalDate.parse("2000-12-31"), PageRequest.of(1, 5));
        System.out.println("Find Byt Publish Date: " + foundBooksAsPage.stream().toList() + "\n");

        foundBooks = bookRepository.findByTitleContainingQuery("Clean");
        System.out.println("Find Byt Title Contaning: " + foundBooks + "\n");

        foundBooks = bookRepository.findByAuthorAndAgeGreaterThanQuery("Robert C. Martin", (long)10);
        System.out.println("Find Byt Author Contaning: " + foundBooks + "\n");

        foundBookNumber = bookRepository.countByAuthorQuery("Kent Beck");
        System.out.println("Find Byt Number of Books: " + foundBookNumber + "\n");

        isBookExist = bookRepository.existsByAuthorQuery("Kent Beck");
        System.out.println("Find Byt Author Exist: " + isBookExist + "\n");
    }
}
