package library.controller;

import library.entity.Book;
import library.entity.BorrowBook;
import library.entity.Student;
import library.service.ServiceProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller{
    @Autowired
    ServiceProcess serviceProcess;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String getHome(){
        return "/studen" +
                "/book";
    }
    // student
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getIndex(){
        return serviceProcess.getAllStudent();
    }

    @RequestMapping(value = "/student",method = RequestMethod.POST)
    @ResponseBody
    public void createstudent(@RequestBody Student student){
        serviceProcess.createStudent(student);
    }
    @RequestMapping(value = "/student/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deletestudent(@PathVariable Long id){
        serviceProcess.deleteStudent(id);
    }

    // book
    @RequestMapping(value = "/book",method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllTable(){
        List<Book> t = serviceProcess.getAllBook();
        return serviceProcess.getAllBook();
    }
    // post thực hiên cả 2 nhiệm vụ thêm và chỉnh sửa book không sửa student
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    @ResponseBody
    public void createbook(@RequestBody Book book){
        serviceProcess.createBook(book);
    }
    /*
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    lúc request book mà không truyền đầy đủ các trường thì toàn bộ các
    trường còn thiếu không cần biết trong db có dữ liệu hay không đều bị chuyển thành
    null ========>  gây mất mát dữ liệu
    ///////
    put thực hiện khi có học sinh sách

     */
    @RequestMapping(value = "/book",method = RequestMethod.PUT)
    @ResponseBody
    public void putbook(@RequestBody Book book){
        serviceProcess.updatebook(book, book.getId());
    }
    @RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deletebook(@PathVariable Long id){
        serviceProcess.deleteBook(id);
    }

    @RequestMapping(value = "/borrow",method = RequestMethod.GET)
    @ResponseBody
    public List<BorrowBook> getAllBorrow(){
        return serviceProcess.getAllborrowBook();
    }
    @RequestMapping(value = "/borrow",method = RequestMethod.POST)
    @ResponseBody
    public void createborrow(@RequestBody BorrowBook book){
        serviceProcess.createborrowBook(book);
    }
    @RequestMapping(value = "/borrow/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteborrow(@PathVariable Long id){
        serviceProcess.deleteborrowBook(id);
    }


}