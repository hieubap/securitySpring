package library.api.controller;

import library.api.entity.Student;
import library.api.exceptionhandle.responceEntity.EntityResponse;
import library.api.service.StudentService;
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
        EntityResponse entityResponse = new EntityResponse(200, new Timestamp(System.currentTimeMillis()),"get ok",
                studentService.getByID(id));
        return entityResponse;
    }

    @RequestMapping(value = "/student/mssv={mssv}", method = RequestMethod.GET)
    public EntityResponse<Student> getByMssv(@PathVariable String mssv) {
        return new EntityResponse<>(HttpStatus.OK,"successful",
                studentService.getByMssv(mssv));
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
        studentService.add(student);
        return new EntityResponse<>(HttpStatus.OK,"create successful",student);
    }

    @RequestMapping(value = {"/student/update"}, method = RequestMethod.PUT)//?id=
    public EntityResponse<Student> updatebyid(@RequestBody Student student,@RequestParam Long id){
        return new EntityResponse<>(HttpStatus.OK,"update successful",
                studentService.updateById(student,id));
    }

    @RequestMapping(value = {"/student/update/mssv={mssv}"}, method = RequestMethod.PUT)
    public EntityResponse<Student> updatebymssv(@RequestBody Student student,@PathVariable String mssv){
        return new EntityResponse<>(HttpStatus.OK,"update successful with mssv",
                studentService.updateByMssv(student, mssv));
    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE)
    public EntityResponse<Student> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new EntityResponse<>(HttpStatus.OK,"delete successful",null);
    }
}
