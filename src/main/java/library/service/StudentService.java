package library.service;

import library.entity.Student;
import library.exception.ApiRequestException;
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
    public List<Student> findbygender(Boolean gender){
        return studentRepo.findByGenderContains(gender);
    }


    public boolean isExist(String mssv){
        if (studentRepo.existsByMssv(mssv))
            return true;
        return false;
    }

    public void create(Student student){
        if (student.getName()== null){
            throw new ApiRequestException("name of student cant null. enter name");
        }
        if (student.getMssv()==null){
            throw new ApiRequestException("mssv of student cant null. enter mssv");
        }
        if (student.getCource()==null){
            throw new ApiRequestException("cource of student cant null. enter mssv");
        }
        if (student.getInstitute()==null){
            throw new ApiRequestException("institute of student cant null. enter mssv");
        }
        if (student.getPhone()==null){
            throw new ApiRequestException("phone of student cant null. enter mssv");
        }

        if (isExist(student.getMssv())){
            throw new ApiRequestException("this student with mssv: "+student.getMssv()+" is existed.");
        }
        studentRepo.save(student);
    }
    public void delete(Long id){
        studentRepo.deleteById(id);
    }
}
