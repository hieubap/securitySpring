package library.api.service;

import library.api.entity.Book;
import library.api.entity.CardLibrary;
import library.api.entity.Session;
import library.exceptionhandle.exception.ApiRequestException;
import library.api.repository.CardRepository;
import library.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class CardService {
    private final CardRepository repositoryCard;
    private final StudentRepository studentRepo;
    private final BookService bookService;
    private final SessionService sessionService;

    @Autowired
    public CardService(CardRepository repositoryCard,
                       StudentRepository studentRepo,
                       BookService bookService,
                       SessionService sessionService){
        this.repositoryCard = repositoryCard;
        this.studentRepo = studentRepo;
        this.bookService = bookService;
        this.sessionService = sessionService;
    }

    public CardLibrary getByMssv(String mssv){
        if(!isExist(mssv)){
            throw new ApiRequestException("this student don't have card library ! check id");
        }
        return repositoryCard.findByMssv(mssv);
    }
    public CardLibrary getbyID(Long id){
        if(!isExist(id)){
            throw new ApiRequestException("this id is not exist ! check id");
        }
        return repositoryCard.getOne(id);
    }
    public List<CardLibrary> getAll(){
        return repositoryCard.findAll();
    }
    public boolean isExist(String mssv){
        return repositoryCard.existsByMssv(mssv);
    }
    public boolean isExist(Long id){
        return repositoryCard.existsById(id);
    }

    //************************
    public void add(CardLibrary cardLibrary){
        if(cardLibrary.getMssv() == null){
            throw new ApiRequestException( "mssv cant null.");
        }

        if(!studentRepo.existsByMssv(cardLibrary.getMssv()))
            throw new ApiRequestException( "this student is not exist");

        if (isExist(cardLibrary.getMssv())){
            System.out.println("This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
            throw new ApiRequestException( "This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
        }

        cardLibrary.setStatus("con han");

        long year = TimeUnit.MILLISECONDS.convert(365,TimeUnit.DAYS);
        cardLibrary.setExpiration_date(new Timestamp(System.currentTimeMillis()+year));
        String idstudent = cardLibrary.getMssv();
        cardLibrary.setStudentId(studentRepo.getByMssv(idstudent).getId());

        repositoryCard.save(cardLibrary);
    }
    public CardLibrary update(CardLibrary cardLibrary,Long id){
        if (!isExist(id)){
            throw new ApiRequestException("this id is not exist");
        }
        CardLibrary cardLibrary1 = repositoryCard.getOne(id);
        cardLibrary1.set(cardLibrary);
        repositoryCard.save(cardLibrary1);

        return cardLibrary1;
    }
    public void back(Long id){
        Session session =  sessionService.getbyID(id);
        session.setStatus("da tra");
        sessionService.update(session,id);

        Book book = bookService.getById(session.getIdBook());
        book.setStatus("binh thuong");
//        bookService.updateBook(book);
    }
    public void delete(long id){
        if(!isExist(id)){
            throw new ApiRequestException("this id is not exist");
        }
        repositoryCard.deleteById(id);
    }
}
