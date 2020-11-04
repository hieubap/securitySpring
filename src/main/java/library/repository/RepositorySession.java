package library.repository;

import library.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySession extends JpaRepository<Session,Long> {
}
