package zw.co.onlinebooks.bookshop.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}