package library.controller;

import library.entity.Book;
import library.exception.ApiRequestException;
import library.exceptionhandle.responceEntity.EntityResponse;
import library.service.BookService;
import library.service.HeadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService bookService;
    private final HeadBookService headBookService;

    @Autowired
    public BookController(BookService bookService,
                          HeadBookService headBookService) {
        this.bookService = bookService;
        this.headBookService = headBookService;
    }

    @RequestMapping(value = "/books/show", method = RequestMethod.GET)
    public EntityResponse<List<Book>> getAllBook() {
            List<Book> books = bookService.getAllBook();
            if (books.isEmpty()) {
                throw new ApiRequestException("books is empty");
            }
            return new EntityResponse<>(HttpStatus.OK, "Find all books", books);

    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public EntityResponse<Book> addBook(@RequestBody Book book) {
            if (book.getId() == null || book.getHeadBookId() == null) {
                throw new ApiRequestException("id/headbookid field of book is not null");
            }
            if (bookService.isExist(book.getId())) {
                throw new ApiRequestException("The given id is exist !");
            }
            if (!headBookService.isExist(book.getHeadBookId())){
                throw new ApiRequestException("this headbook with id = " + book.getHeadBookId() + " is not exist!");
            }

            book.setId(book.getId());
            bookService.addBook(book);
            return new EntityResponse<>(HttpStatus.CREATED, "Create Successful", book);
    }

    @RequestMapping(value = "books/getOne", method = RequestMethod.GET)
    public EntityResponse<Optional<Book>> findBook(@RequestParam Long id) {
        if (bookService.isExist(id)) {
            Optional<Book> book = bookService.findBookById(id);
            return new EntityResponse<>(HttpStatus.OK, "information of book with id = " + id, book);
        }
        throw new ApiRequestException("this book is not exist");
    }

    @RequestMapping(value = "/books/update", method = RequestMethod.PUT)
    public EntityResponse<Book> update(@RequestBody Book book, @RequestParam Long id) {
            if (bookService.isExist(id)) {
                bookService.updateBook(book);
                return new EntityResponse<>(HttpStatus.OK, "Update Successful", book);
            }
            throw new ApiRequestException("this id is not exist");
    }

    @RequestMapping(value = "/books/delete", method = RequestMethod.DELETE)
    public EntityResponse<Optional<Book>> delete(@RequestParam Long id) {
            if (bookService.isExist(id)) {
                bookService.deleteBookById(id);
                return new EntityResponse<>(HttpStatus.OK, "Delete successful", bookService.findBookById(id));
            }
            throw new ApiRequestException("error delete");
    }
}
