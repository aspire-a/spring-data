package yte.intern.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByAgeGreaterThan(Long age, Sort sort);
    Page<Book> findByPublishDateAfter(LocalDate publishDate, Pageable pageable);
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthorAndAgeGreaterThan(String author, Long age);
    Integer countByAuthor(String author);
    Boolean existsByAuthor(String author);


    @Query(value = "select b from Book b where b.title = :title")
    List<Book> findByTitleQuery(@Param("title") String title);

    @Query(value = "select b from Book b where b.age > :age")
    List<Book> findByAgeGreaterThanQuery(@Param("age") Long age, Sort sort);

    @Query(value = "select b from Book b where b.publishDate > :publishDate")
    Page<Book> findByPublishDateAfterQuery(@Param("publishDate") LocalDate publishDate, Pageable pageable);

    @Query(value = "select b from Book b where b.title like %:title%")
    List<Book> findByTitleContainingQuery(@Param("title") String title);

    @Query(value = "select b from Book b where b.author = :author and b.age > :age ")
    List<Book> findByAuthorAndAgeGreaterThanQuery(@Param("author") String author,
                                                  @Param("age") Long age);

    @Query(value = "select count(b) from Book b where b.author = :author")
    Integer countByAuthorQuery(@Param("author") String author);

    @Query(value = "select count(*)>=1 from Book where author = :author", nativeQuery = true)
    Boolean existsByAuthorQuery(@Param("author") String author);
}