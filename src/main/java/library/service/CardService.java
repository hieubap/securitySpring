package library.service;

import library.entity.CardLibrary;
import library.repository.CardRepository;
import library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public CardLibrary getByMssv(String mssv){
        return repositoryCard.findByMssv(mssv);
    }
    public CardLibrary getbyID(Long id){
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

    public void update(CardLibrary cardLibrary){
        repositoryCard.save(cardLibrary);
    }
    public void delete(long id){
        repositoryCard.deleteById(id);
    }
}
