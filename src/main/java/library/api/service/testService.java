//package library.service;
//
//import library.entity.Book;
//import library.exception.ApiRequestException;
//import library.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class testService {
//    private final BookRepository repository;
//
//    @Autowired
//    public testService(BookRepository repository) {
//        this.repository = repository;
//    }
//
//    public List<Book> getAllBook() {
//        return repository.findAll();
//    }
//
//    public Book getById(Long id) {
//        return repository.getOne(id);
//    }
//
//    public void createBook(Book entity) {
//        repository.save(entity);
//    }
//
//    public void updateBook(Book entity) {
//        Book entitybuffer = repository.getOne(id);
//        entitybuffer.set(entity);
//
//        repository.save(entitybuffer);
//    }
//
//    public void deleteBook(Long id) {
//        if (!repository.isExist(id))
//            throw new ApiRequestException("id nay khong ton tai");
//        repository.deleteById(id);
//    }
//}
