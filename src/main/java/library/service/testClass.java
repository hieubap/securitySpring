package library.service;

import library.entity.Student;
import library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class testClass {
    private final StudentRepository repository;

    @Autowired
    public testClass(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    public void addStudent(Student entity) {
        repository.save(entity);
    }


}
