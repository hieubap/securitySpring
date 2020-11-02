package library.repository;

import library.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBorrowBook extends JpaRepository<BorrowBook,Long> {
}
