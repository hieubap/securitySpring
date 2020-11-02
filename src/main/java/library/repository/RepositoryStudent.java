package library.repository;


import library.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryStudent extends JpaRepository<Student,Long> {
}
