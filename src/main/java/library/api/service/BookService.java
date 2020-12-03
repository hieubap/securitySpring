package library.api.service;

import library.api.entity.Book;
import library.exceptionhandle.exception.ApiRequestException;
import library.api.repository.BookRepository;
import library.api.repository.HeadBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final HeadBookRepository headBookRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       HeadBookRepository headBookRepository) {
        this.bookRepository = bookRepository;
        this.headBookRepository = headBookRepository;
    }

    public List<Book> getAllBook() {
        if( bookRepository.count() == 0)
        {
            throw new ApiRequestException("books is empty");
        }
        return bookRepository.findAll();
    }
    public Book getById(Long id){
        return bookRepository.getOne(id);
    }
    public Optional<Book> getBookById(Long id) {
        if (!isExist(id)) {
            throw new ApiRequestException("this book is not exist");
        }
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        if ( book.getHeadBook() == null) {
            throw new ApiRequestException("headbookid field of book is not null");
        }
        else if (book.getHeadBook() != null){
            headBookRepository.save(book.getHeadBook());
        }
        else if (isExist(book.getId())) {
            throw new ApiRequestException("The given id is exist !");
        }
        else if (!headBookRepository.existsById(book.getHeadBookId())){
            throw new ApiRequestException("this headbook with id = " + book.getHeadBookId() + " is not exist! create headbook");
        }

        book.setStatus("binh thuong");
        book.setAddedDate(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(Book book,Long id) {
        if (!isExist(id)){
            throw new ApiRequestException("this id is not exist");
        }
        Book book1 = bookRepository.getOne(book.getId());
        book1.set(book);
        bookRepository.save(book1);
        return book1;
    }

    public void deleteBookById(Long id) {
        if (!isExist(id)){
            throw new ApiRequestException("this id is not exist");
        }
        bookRepository.deleteById(id);
    }

    public boolean isExist(Long id) {
        return bookRepository.findById(id).isPresent();
    }
}
