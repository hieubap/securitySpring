package library.service;

import library.entity.CardLibrary;
import library.exception.ApiRequestException;
import library.repository.CardRepository;
import library.repository.StudentRepository;
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

    @Autowired
    public CardService(CardRepository repositoryCard,
                       StudentRepository studentRepo){
        this.repositoryCard = repositoryCard;
        this.studentRepo = studentRepo;
    }


    // student
    public CardLibrary getbyID(Long id){
        return repositoryCard.getOne(id);
//        try {
//            return repositoryStudent.findById(id).get();
//        }
//        catch(NoSuchElementException e){
//            return null;
//        }
    }
    public List<CardLibrary> getAll(){
        return repositoryCard.findAll();
    }
    public boolean isExist(Long id){
        return repositoryCard.existsById(id);
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
    public void create(CardLibrary cardLibrary){
        if(cardLibrary.getMssv() == null){
            throw new ApiRequestException( "mssv cant null.");
        }

        if(!studentRepo.existsByMssv(cardLibrary.getMssv()))
            throw new ApiRequestException( "this student is not exist");

        // Long(2018) != Long(2018) vì là đối tượng nhưng long(2018) == long(2018)

        if (repositoryCard.existsByMssv(cardLibrary.getMssv())){
            System.out.println("This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
            throw new ApiRequestException( "This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
        }

        cardLibrary.setStatus("con han");

        long year = TimeUnit.MILLISECONDS.convert(365,TimeUnit.DAYS);
        cardLibrary.setExpiration_date(new Timestamp(System.currentTimeMillis()+year));
        String idstudent = cardLibrary.getMssv();
        cardLibrary.setStudentId(studentRepo.findByMssv(idstudent).getId());

        repositoryCard.save(cardLibrary);

//        throw new ApiRequestSuccessfull( "create successful" );
    }
    public void update(long id){

    }
    public void delete(long id){
        repositoryCard.deleteById(id);
    }
}
