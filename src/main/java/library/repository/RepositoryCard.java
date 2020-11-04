package library.repository;

import library.entity.CardLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCard extends JpaRepository<CardLibrary,Long> {
}
