package library.controller;

import library.entity.Student;
import library.exception.ApiRequestException;
import library.exceptionhandle.responceEntity.EntityResponse;
import library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student/showall", method = RequestMethod.GET)
    public EntityResponse getAll()
    {
        EntityResponse entityResponse = new EntityResponse(200,
                new Timestamp(System.currentTimeMillis()),"successful",studentService.showStudent());
        return entityResponse;
    }

    @RequestMapping(value = "/student/id={id}", method = RequestMethod.GET)
    public EntityResponse getById(@PathVariable Long id) {
        if (!studentService.isExist(id))
            throw new ApiRequestException("this id is not exist");

        EntityResponse entityResponse = new EntityResponse(200,
                new Timestamp(System.currentTimeMillis()),"get ok",studentService.getByID(id));
        return entityResponse;
    }

    @RequestMapping(value = "/student/mssv={mssv}", method = RequestMethod.GET)
    public EntityResponse<Student> getByMssv(@PathVariable String mssv) {
        if (!studentService.isExist(mssv))
            throw new ApiRequestException("khong co hoc sinh nao co mssv la:" + mssv + " kiem tra lai!");

        return new EntityResponse<>(HttpStatus.OK,"successful",studentService.getByMssv(mssv));
    }
    @RequestMapping(value = "/student/find/name={name}", method = RequestMethod.GET)
    public EntityResponse<List<Student>> findbyname(@PathVariable String name) {
        return new EntityResponse<>(HttpStatus.OK,"find ok",studentService.findbyname(name));
    }
    @RequestMapping(value = "/student/find/mssv={mssv}", method = RequestMethod.GET)
    public EntityResponse<List<Student>> findbymssv(@PathVariable String mssv) {
        return new EntityResponse<>(HttpStatus.OK,"",studentService.findbymssv(mssv));
    }
    @RequestMapping(value = "/student/find/cource={cource}", method = RequestMethod.GET)
    public EntityResponse<List<Student>> findbycource(@PathVariable String cource) {
        return new EntityResponse<>(HttpStatus.OK,"find by cource",studentService.findbycource(cource));
    }
    @RequestMapping(value = "/student/find/instute={instute}", method = RequestMethod.GET)
    public EntityResponse<List<Student>> findbyinstute(@PathVariable String instute) {
        return new EntityResponse<>(HttpStatus.OK,"find by instute",studentService.findbyinstute(instute)) ;
    }
    @RequestMapping(value = "/student/find/phone={phone}", method = RequestMethod.GET)
    public EntityResponse<List<Student>> findbyphone(@PathVariable String phone) {
        return new EntityResponse<>(HttpStatus.OK,"find by phone",studentService.findbyphone(phone)) ;
    }
//    @RequestMapping(value = "/student/find/gender={gender}", method = RequestMethod.GET)
//    public List<Student> findbygender(@PathVariable boolean gender) {
//        throw new ApiRequestException("truong hop nay dang mac loi. se sua lai sau");
//    }

    @RequestMapping(value = "/student/create", method = RequestMethod.POST)
    public EntityResponse<Student> create(@RequestBody Student student){
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

        if (studentService.isExist(student.getMssv())){
            throw new ApiRequestException("this student with mssv: "+student.getMssv()+" is existed.");
        }

        studentService.save(student);
        return new EntityResponse<>(HttpStatus.OK,"create successful",student);
    }


    @RequestMapping(value = {"/student/update"}, method = RequestMethod.PUT)//?id=
    public EntityResponse<Student> updatebyid(@RequestBody Student student,@RequestParam Long id){
        if (student.getName()== null){
            throw new ApiRequestException("name of student cant null. enter name");
        }
        if (student.getMssv()==null){
            throw new ApiRequestException("mssv of student cant null. enter mssv");
        }
        if (!studentService.isExist(student.getMssv())){
            throw new ApiRequestException("this student with mssv does not exist.");
        }
        if (id == null){
            throw new ApiRequestException("id cannot null");
        }
        Student buffer = studentService.getByID(id);
        buffer.set(student);
        studentService.save(buffer);

        return new EntityResponse<>(HttpStatus.OK,"update successful",buffer);
    }

    @RequestMapping(value = {"/student/update/mssv={mssv}"}, method = RequestMethod.PUT)
    public EntityResponse<Student> updatebymssv(@RequestBody Student student,@PathVariable String mssv){
        if (student.getName()== null){
            throw new ApiRequestException("name of student cant null. enter name");
        }
        if (studentService.getByMssv(mssv) == null ){
            throw new ApiRequestException("this mssv does not exist.");
        }

        Student student1 = studentService.getByMssv(mssv);
        student1.set(student);
        studentService.save(student1);

        return new EntityResponse<>(HttpStatus.OK,"update successful with mssv",student1);
    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE)
    public EntityResponse<Student> delete(@PathVariable Long id) {
        if(!studentService.isExist(id)){
            throw new ApiRequestException("khong ton tai sinh vien co id = " + id);
        }
        studentService.delete(id);
        return new EntityResponse<>(HttpStatus.OK,"delete successful",null);
    }
}
