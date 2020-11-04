package library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.entity.Book;
import library.entity.CardLibrary;
import library.entity.Session;
import library.entity.Student;
import library.repository.RepositoryBook;
import library.repository.RepositoryCard;
import library.repository.RepositorySession;
import library.repository.RepositoryStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ServiceProcess {
    public static final int STUDENT = 1;
    public static final int CARD = 2;
    public static final int SESSION = 3;
    public static final int BOOK = 4;


    private final RepositoryStudent repositoryStudent;
    private final RepositoryBook repositoryBook;
    private final RepositorySession repositorySession;
    private final RepositoryCard repositoryCard;


    @Autowired
    public ServiceProcess(RepositoryStudent repositoryStudent,
                          RepositoryBook repositoryBook,
                          RepositorySession repositorySession,
                          RepositoryCard repositoryCard){
        this.repositoryStudent = repositoryStudent;
        this.repositoryBook = repositoryBook;
        this.repositorySession = repositorySession;
        this.repositoryCard = repositoryCard;
    }


    // student
    public Object getbyID(int TYPE, Long id){
        switch (TYPE){
            case STUDENT:{
                return repositoryStudent.findById(id).get();
            }
            case CARD:{
                return repositoryCard.findById(id).get();
            }
            case SESSION:{
                return repositorySession.findById(id).get();
            }
            case BOOK:{
                return repositoryBook.findById(id).get();
            }
        }
        return null;
//        try {
//            return repositoryStudent.findById(id).get();
//        }
//        catch(NoSuchElementException e){
//            return null;
//        }
    }
    public List<Object> getAll(int TYPE){
        switch (TYPE){
            case STUDENT:{
                return new ArrayList<Object>(repositoryStudent.findAll());
            }
            case CARD:{
                return new ArrayList<Object>(repositoryCard.findAll());
            }
            case SESSION:{
                return new ArrayList<Object>(repositorySession.findAll());
            }
            case BOOK:{
                return new ArrayList<Object>(repositoryBook.findAll());
            }
        }
        return null;
    }
    public boolean isExist(int TYPE, Long id){
        switch (TYPE){
            case STUDENT:{
                return repositoryStudent.existsById(id);
            }
            case CARD:{
                return repositoryCard.existsById(id);
            }
            case SESSION:{
                return repositorySession.existsById(id);
            }
            case BOOK:{
                return repositoryBook.existsById(id);
            }
        }
        return false;
    }
    /*
    java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class
    library.entity.Student (java.util.LinkedHashMap is in module java.base of loader 'bootstrap';
    library.entity.Student is in unnamed module of loader 'app')


    UnrecognizedPropertyException: Unrecognized field "mssv" (class library.entity.Session), not marked as
    ignorable (8 known properties: "card", "booknumber", "date_borrowed", "numbercard", "id",
    "expiration_date", "status", "book"])

    quên break tại switch;
     */
    public String create(int TYPE, Object object){
        ObjectMapper mapper = new ObjectMapper();
        switch (TYPE){
            case STUDENT:{
                Student student = mapper.convertValue(object,Student.class);
                if(student.getMssv() == null){
                    return "mssv is null. mssv can't null";
                }
                System.out.printf("mssv = " + student.getMssv());
                repositoryStudent.save(student);
                break;
            }
            case CARD:{
                CardLibrary cardLibrary = mapper.convertValue(object,CardLibrary.class);

                if(!isExist(STUDENT,cardLibrary.getMssv()))
                    return "this student is not exist";

                List<CardLibrary> list = repositoryCard.findAll();
                // Long(2018) != Long(2018) vì là đối tượng nhưng long(2018) == long(2018)

                for(CardLibrary c : list){
                    if ((long)c.getMssv() == (long)cardLibrary.getMssv()){
                        System.out.println("This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
                        return "This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")";
                    }
                }


                cardLibrary.setStatus("con han");

                long year = TimeUnit.MILLISECONDS.convert(365,TimeUnit.DAYS);
                cardLibrary.setExpiration_date(new Timestamp(System.currentTimeMillis()+year));

                System.out.println("creating ... " + list.size());
                repositoryCard.save(cardLibrary);
                break;
            }
            case SESSION:{
                Session session = mapper.convertValue(object,Session.class);

                // kiểm tra thông tin sách và số thẻ request có null không
                if(session.getBooknumber()== null || session.getNumbercard()== null)
                    return "cant create Session: Error : booknumber or cardnumber is null";
                else{
                    // nếu không null thì có tồn tại trong db hay không
                    if(!isExist(CARD,session.getNumbercard()))
                        return "card is not exist";
                    if(!isExist(BOOK,session.getBooknumber()))
                        return "book is not exist";
                }

/*
    ????????????
      org.hibernate.HibernateException: More than one row with the given identifier was
      found: 10, for class: library.entity.Session
      => trường khóa ngoại bị trùng dữ liệu ==> xóa đi
*/
                // kiểm tra book đã được mượn chưa
                Book book = mapper.convertValue(getbyID(BOOK, session.getBooknumber()),Book.class);
                List<Session> list = repositorySession.findAll();
                for(Session s : list){
                    if((long)s.getBooknumber() == (long)book.getId()){
                        return "this book is borrowed";
                    }
                }

                book.setStatus("borrowed");
                repositoryBook.save(book);
                session.setDate_borrowed(new Date());
                long days = TimeUnit.MILLISECONDS.convert(30,TimeUnit.DAYS);
                session.setExpiration_date(new Timestamp(System.currentTimeMillis()+days));
                session.setStatus("con han");

                repositorySession.save(session);

                break;
            }
            case BOOK:{
                /*
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    lúc request book mà không truyền đầy đủ các trường thì toàn bộ các
    trường còn thiếu không cần biết trong db có dữ liệu hay không đều bị chuyển thành
    null ========>  gây mất mát dữ liệu
    ///////
    put thực hiện khi có học sinh sách

     */
                Book book = mapper.convertValue(object,Book.class);
                if(book.getName() == null){
                    return "name of book cant null. name book is null";
                }

                repositoryBook.save(book);
                break;
            }
        }
        return "create successful";
    }
    public void update(int TYPE,long id){
        ObjectMapper mapper = new ObjectMapper();
        switch (TYPE){
            case STUDENT:{
                break;
            }
            case CARD:{
                break;
            }
            case SESSION:{
                Book book = repositorySession.getOne(id).getBook();
                book.setStatus("trong kho");
                repositoryBook.save(book);

                repositorySession.deleteById(id);
                break;
            }
            case BOOK:{
                break;
            }
        }
    }
    public void delete(int TYPE,long id){
//        ObjectMapper mapper = new ObjectMapper();
        switch (TYPE){
            case STUDENT:{
                repositoryStudent.deleteById(id);
                break;
            }
            case CARD:{
                repositoryCard.deleteById(id);
                break;
            }
            case SESSION:{
                /*
                set numbercard và booknumber = null không khi xóa
                session thì sẽ xóa cả card và book ===> mất mát dữ liệu
                không cho booknumber = null thì không thể xóa
                xóa 2 lần

                ===> do cascade book để all nên khi xóa từ parent thì book
                cũng bị xóa

                 */
                repositorySession.deleteById(id);
                break;
            }
            case BOOK:{
                repositoryBook.deleteById(id);
                break;
            }
        }
    }

}
