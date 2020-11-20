package library.repository;

import library.entity.CardLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardLibrary,Long> {
    public boolean existsByMssv(String mssv);
}
