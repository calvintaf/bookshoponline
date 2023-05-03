package zw.co.onlinebooks.bookshop.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
    Category findCategoryById(Long id);
    Category findTopById(Long id);
}
