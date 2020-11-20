package library.controller;

import library.entity.Student;
import library.exception.ApiRequestException;
import library.exception.ApiRequestSuccessfull;
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
        EntityResponse entityResponse = new EntityResponse(200,
                new Timestamp(System.currentTimeMillis()),"get ok",studentService.getByID(id));
        return entityResponse;
    }
    @RequestMapping(value = "/student/mssv={mssv}", method = RequestMethod.GET)
    public Student getByMssv(@PathVariable String mssv) {
        return studentService.getByMssv(mssv);
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
    public List<Student> findbycource(@PathVariable String cource) {
        return studentService.findbycource(cource);
    }
    @RequestMapping(value = "/student/find/instute={instute}", method = RequestMethod.GET)
    public List<Student> findbyinstute(@PathVariable String instute) {
        return studentService.findbyinstute(instute);
    }
    @RequestMapping(value = "/student/find/phone={phone}", method = RequestMethod.GET)
    public List<Student> findbyphone(@PathVariable String phone) {
        return studentService.findbyphone(phone);
    }
    @RequestMapping(value = "/student/find/gender={gender}", method = RequestMethod.GET)
    public List<Student> findbygender(@PathVariable boolean gender) {

        throw new ApiRequestException("truong hop nay dang mac loi. se sua lai sau");
    }

    @RequestMapping(value = "/student/create", method = RequestMethod.POST)
    public void create(@RequestBody Student student){
        System.out.println("create student");
        studentService.create(student);

    }


    @RequestMapping(value = {"/student/update"}, method = RequestMethod.PUT)
    public void updatebyid(@RequestBody Student student,@RequestParam Long id){
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
        buffer.setAll(student);
        studentService.create(buffer);

        throw new ApiRequestSuccessfull("update student successful");
    }

    @RequestMapping(value = {"/student/update/mssv={mssv}"}, method = RequestMethod.PUT)
    public void updatebymssv(@RequestBody Student student,@PathVariable String mssv){
        if (student.getName()== null){
            throw new ApiRequestException("name of student cant null. enter name");
        }
        if (studentService.getByMssv(mssv) == null ){
            throw new ApiRequestException("this mssv does not exist.");
        }

        Student student1 = studentService.getByMssv(mssv);
        student1.setAll(student);
        studentService.create(student1);

        throw new ApiRequestSuccessfull("update student successful");
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
        throw new ApiRequestSuccessfull("student with id: " + id +" is deleted");
    }
}
