package library.api.service;

import library.api.entity.Student;
import library.exceptionhandle.exception.ApiRequestException;
import library.api.repository.StudentRepository;
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
        if (!isExist(id))
            throw new ApiRequestException("this id is not exist");
        return studentRepo.getOne(id);
    }
    public Student getByMssv(String mssv){
        if (!isExist(mssv))
            throw new ApiRequestException("khong co hoc sinh nao co mssv la:" + mssv + " kiem tra lai!");
        return studentRepo.getByMssv(mssv);
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

    public void add(Student student){
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
    public Student updateById(Student student, Long id){
        if (student.getName()== null){
            throw new ApiRequestException("name of student cant null. enter name");
        }
        if (student.getMssv()==null){
            throw new ApiRequestException("mssv of student cant null. enter mssv");
        }
        if (!isExist(student.getMssv())){
            throw new ApiRequestException("this student with mssv does not exist.");
        }
        if (id == null){
            throw new ApiRequestException("id cannot null");
        }
        Student buffer = getByID(id);
        buffer.set(student);
        return buffer;
    }
    public Student updateByMssv(Student student,String mssv){
        if (student.getName()== null){
            throw new ApiRequestException("name of student cant null. enter name");
        }
        if (getByMssv(mssv) == null ){
            throw new ApiRequestException("this mssv does not exist.");
        }
        Student student1 = getByMssv(mssv);
        student1.set(student);
        studentRepo.save(student1);

        return student1;
    }
    public void delete(Long id){
        if(!isExist(id)){
            throw new ApiRequestException("khong ton tai sinh vien co id = " + id);
        }
        studentRepo.deleteById(id);
    }
}
