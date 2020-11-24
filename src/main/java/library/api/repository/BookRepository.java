package library.api.repository;

import library.api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    public int countByHeadBookId(Long id);
    public int countByHeadBookIdAndStatusIsNot(Long id,String status);
}