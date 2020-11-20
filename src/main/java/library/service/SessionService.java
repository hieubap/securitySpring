package library.service;


import library.entity.Book;
import library.entity.Session;
import library.exception.ApiRequestException;
import library.repository.BookRepository;
import library.repository.CardRepository;
import library.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;
    private final BookRepository bookRepository;
    private final CardRepository cardRepository;

    @Autowired
    public SessionService(SessionRepository repositoryCard,
                          BookRepository headBookRepository,
                          CardRepository cardRepository){
        this.sessionRepository = repositoryCard;
        this.bookRepository = headBookRepository;
        this.cardRepository = cardRepository;
    }

    public Session getbyID(Long id){
        return sessionRepository.getOne(id);
    }
    public List<Session> getAll(){
        return sessionRepository.findAll();
    }
    public boolean isExist(Long id){
        return sessionRepository.existsById(id);
    }
    public void create(Session session){
        // kiểm tra thông tin sách và số thẻ request có null không
                if(session.getBooknumber()== null || session.getNumbercard()== null)
                    throw new ApiRequestException( "cant create Session: Error : booknumber or cardnumber is null");
                else{
                    // nếu không null thì có tồn tại trong db hay không
                    if(!cardRepository.existsById(session.getNumbercard()))
                        throw new ApiRequestException( "card is not exist");
                    if(!bookRepository.existsById(session.getBooknumber()))
                        throw new ApiRequestException( "book is not exist" );
                }
                // kiểm tra book đã được mượn chưa
                Book headbook = bookRepository.getOne( session.getBooknumber());

//                if(headbook.get){
//                    throw new ApiRequestException( "this book is borrowed");
//                }

//                headbook.setStatus("borrowed");
                bookRepository.save(headbook);
                session.setDate_borrowed(new Date());
                long days = TimeUnit.MILLISECONDS.convert(30,TimeUnit.DAYS);
                session.setExpiration_date(new Timestamp(System.currentTimeMillis()+days));
                session.setStatus("con han");

                sessionRepository.save(session);
//        throw new ApiRequestSuccessfull( "create successful" );
    }
    public void update(long id){

    }
    public void delete(long id){
        sessionRepository.deleteById(id);
    }
}
