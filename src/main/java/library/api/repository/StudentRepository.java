package library.api.repository;


import library.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Boolean existsByMssv(String mssv);
    public Student getByMssv(String mssv);

    public List<Student> findByMssvContains(String mssv);
    public List<Student> findByNameContains(String name);
    public List<Student> findByCourceContains(String name);
    public List<Student> findByInstituteContains(String name);
    public List<Student> findByPhoneContains(String name);
    public List<Student> findByGenderContains(Boolean name);
}
