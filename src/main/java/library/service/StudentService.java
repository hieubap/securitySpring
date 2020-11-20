package library.service;

import library.entity.Student;
import library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> showStudent(){
        return studentRepo.findAll();
    }
    public Student getByID(Long id){
        return studentRepo.getOne(id);
    }
    public Student getByMssv(String id){
        return studentRepo.findByMssv(id);
    }

    public List<Student> findbyname(String name){
        return studentRepo.findByNameContains(name);
    }
    public List<Student> findbymssv(String mssv){
        return studentRepo.findByMssvContains(mssv);
    }
    public List<Student> findbycource(String cource){
        return studentRepo.findByCourceContains(cource);
    }
    public List<Student> findbyinstute(String instute){
        return studentRepo.findByInstituteContains(instute);
    }
    public List<Student> findbyphone(String phone){
        return studentRepo.findByPhoneContains(phone);
    }
//    public List<Student> findbygender(Boolean gender){
//        return studentRepo.findByGenderContains(gender);
//    }


    public boolean isExist(String mssv){
        return studentRepo.existsByMssv(mssv);
    }
    public boolean isExist(Long id){return studentRepo.existsById(id);}

    public void save(Student student){
        studentRepo.save(student);
    }
    public void delete(Long id){
        studentRepo.deleteById(id);
    }
}
