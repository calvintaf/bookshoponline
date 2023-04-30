package zw.co.onlinebooks.bookshop.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByIsAvailable(Boolean isAvailable);
}