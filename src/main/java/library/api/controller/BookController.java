package library.api.controller;

import library.api.entity.Book;
import library.api.responceEntity.EntityResponse;
import library.api.exportExcel.ExcelHandle;
import library.api.service.BookService;
import library.api.service.HeadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;
    private final HeadBookService headBookService;

    @Autowired
    public BookController(BookService bookService, HeadBookService headBookService) {
        this.bookService = bookService;
        this.headBookService = headBookService;
    }

    @GetMapping(value = "/ex")
    public EntityResponse export() throws IOException {
        ExcelHandle.excelBook(bookService.getAllBook());
        EntityResponse entityResponse = new EntityResponse(200,
                new Timestamp(System.currentTimeMillis()), "export successful", null);
        return entityResponse;
    }

    @GetMapping(value = "/showall")
    public EntityResponse<List<Book>> getAllBook() {
        List<Book> books = bookService.getAllBook();
        return new EntityResponse<>(HttpStatus.OK, "Find all books", books);
    }

    @GetMapping(value = "id={id}")
    public EntityResponse<Optional<Book>> findBook(@PathVariable Long id) {
        return new EntityResponse<>(HttpStatus.OK, "information of book with id = " + id, bookService.getBookById(id));
    }

    @GetMapping(path = "/add")
    public EntityResponse<Book> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new EntityResponse<>(HttpStatus.CREATED, "Create Successful", book);
    }

    @PutMapping(value = "/update")
    public EntityResponse<Book> update(@RequestBody Book book, @RequestParam Long id) {
        bookService.updateBook(book, id);
        return new EntityResponse<>(HttpStatus.OK, "Update Successful", book);
    }

    @DeleteMapping(value = "/delete")
    public EntityResponse<Optional<Book>> delete(@RequestParam Long id) {
        bookService.deleteBookById(id);
        return new EntityResponse<>(HttpStatus.OK, "Delete successful", bookService.getBookById(id));
    }
}
