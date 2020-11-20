package library.service;

import library.entity.HeadBook;
import library.repository.HeadBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeadBookService {
    private final HeadBookRepository headBookRepository;

    @Autowired
    public HeadBookService(HeadBookRepository headBookRepository) {
        this.headBookRepository = headBookRepository;
    }

    public List<HeadBook> getAllHeadBook() {
        return headBookRepository.findAll();
    }

    public Optional<HeadBook> findHeadBookById(Long id) {
        return headBookRepository.findById(id);
    }

    public List<HeadBook> findbyname(String name){
        return headBookRepository.findByNameContains(name);
    }
    public List<HeadBook> findbyauthor(String name){
        return headBookRepository.findByAuthorContains(name);
    }
    public List<HeadBook> findbypublish(String name){
        return headBookRepository.findByPublisherContains(name);
    }


    public void addHeadBook(HeadBook headBook) {
        headBookRepository.save(headBook);
    }

    public void updateHeadBook(HeadBook headBook) {
        HeadBook headBook1 = headBookRepository.getOne(headBook.getId());
        headBook1.setHeadBook(headBook);
        headBookRepository.save(headBook1);
    }


    public void deleteHeadBookById(Long id) {
        headBookRepository.deleteById(id);
    }

    public boolean isExist(Long id){
        return headBookRepository.findById(id).isPresent();
    }
}
