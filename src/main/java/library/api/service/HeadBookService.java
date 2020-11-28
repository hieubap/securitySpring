package library.api.service;

import library.api.entity.HeadBook;
import library.api.exceptionhandle.exception.ApiRequestException;
import library.api.repository.HeadBookRepository;
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
        if (headBookRepository.count() == 0) {
            throw new ApiRequestException("headbooks is empty");
        }
        return headBookRepository.findAll();
    }

    public Optional<HeadBook> getHeadBookById(Long id) {
        if (!isExist(id)){
            throw new ApiRequestException("this headbook is not exist");
        }
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
        if (headBook.getId() == null || headBook.getName() == null ||
                headBook.getAuthor() == null || headBook.getPublisher()== null ||
                headBook.getPrice() == null || headBook.getNumberOfPages() == null) {
            throw new ApiRequestException("id/name/author/publisher/price/numberofpage field of headbook is null");
        }
        if (isExist(headBook.getId())) {
            throw new ApiRequestException("The given id is exist !");
        }

        headBookRepository.save(headBook);
    }

    public void updateHeadBook(HeadBook headBook,Long id) {
        if(!isExist(id)){
            throw new ApiRequestException("this headbook is not exist");
        }
        HeadBook headBook1 = headBookRepository.getOne(headBook.getId());
        headBook1.setHeadBook(headBook);
        headBookRepository.save(headBook1);
    }


    public void deleteHeadBookById(Long id) {
        if(!isExist(id)){
            throw new ApiRequestException("this headbook is not exist");
        }
        headBookRepository.deleteById(id);
    }

    public boolean isExist(Long id){
        return headBookRepository.existsById(id);
    }
}
