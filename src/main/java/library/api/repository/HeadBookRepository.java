package library.api.repository;

import library.api.entity.HeadBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeadBookRepository extends JpaRepository<HeadBook,Long> {
    public List<HeadBook> findByNameContains(String data);
    public List<HeadBook> findByAuthorContains(String data);
    public List<HeadBook> findByPublisherContains(String data);
}
