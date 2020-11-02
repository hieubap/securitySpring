package library.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import library.entity.Book;
import library.entity.BorrowBook;
import library.entity.Student;
import library.repository.RepositoryBook;
import library.repository.RepositoryBorrowBook;
import library.repository.RepositoryStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceProcess {
    private final RepositoryStudent repositoryStudent;
    private final RepositoryBook repositoryBook;
    private final RepositoryBorrowBook repositoryBorrowBook;


    @Autowired
    public ServiceProcess(RepositoryStudent repositoryStudent,
                          RepositoryBook repositoryBook,
                          RepositoryBorrowBook repositoryBorrowBook){
        this.repositoryStudent = repositoryStudent;
        this.repositoryBook = repositoryBook;
        this.repositoryBorrowBook = repositoryBorrowBook;
    }


    // student
    public List<Student> getAllStudent(){
        return repositoryStudent.findAll();
    }
    public void createStudent(Student student){
        repositoryStudent.save(student);
    }
    public void deleteStudent(long id){
        repositoryStudent.deleteById(id);
    }

    // book
    public List<Book> getAllBook(){
        return repositoryBook.findAll();
    }
    public void createBook(Book book){
        repositoryBook.saveAndFlush(book);
    }
    @JsonIgnore
    public void updatebook(Book book, Long id){
        repositoryBook.findById(id).get().setStudent(book.getStudents());
//        repositoryBook.save(book);
    }

    public void deleteBook(long id){
        repositoryBook.deleteById(id);
    }

    // borrow book
    public List<BorrowBook> getAllborrowBook(){
        return repositoryBorrowBook.findAll();
    }
    public void createborrowBook(BorrowBook book){
        repositoryBorrowBook.save(book);
    }

    public void deleteborrowBook(long id){
        repositoryBorrowBook.deleteById(id);
    }

}
