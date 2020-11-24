package library.api.repository;

import library.api.entity.CardLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardLibrary,Long> {
    public boolean existsByMssv(String mssv);
    public CardLibrary findByMssv(String mssv);
}
