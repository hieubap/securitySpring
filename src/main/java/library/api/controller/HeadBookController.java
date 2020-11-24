package library.api.controller;//package com.example.final_library.controller;

import library.api.entity.HeadBook;
import library.api.exceptionhandle.responceEntity.EntityResponse;
import library.api.service.HeadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
public class HeadBookController {
    private final HeadBookService headbookService;

    @Autowired
    public HeadBookController(HeadBookService bookService) {
        this.headbookService = bookService;
    }

    // danh sách các đầu sách
    @RequestMapping(value = "/headbook/show", method = RequestMethod.GET)
    public EntityResponse<List<HeadBook>> getAllBook() {
        List<HeadBook> books = headbookService.getAllHeadBook();
        return new EntityResponse<List<HeadBook>>(HttpStatus.OK, "Find all headbooks", books);
    }

    @RequestMapping(value = "headbook/id={id}", method = RequestMethod.GET)
    public EntityResponse<Optional<HeadBook>> findBook(@PathVariable Long id) {
        Optional<HeadBook> book = headbookService.getHeadBookById(id);
        return new EntityResponse<>(HttpStatus.OK, "information of headbook with id = " + id, book);
    }

    @RequestMapping(value = "/headbook/add", method = RequestMethod.POST)
    public EntityResponse<HeadBook> addBook(@RequestBody HeadBook headBook) {
        headbookService.addHeadBook(headBook);
        return new EntityResponse<>(HttpStatus.CREATED, "Create Successful", headBook);
    }

    @GetMapping("headbook/name={name}")
    public EntityResponse<List<HeadBook>> findbyname(@PathVariable String name) {
        return new EntityResponse<>(HttpStatus.OK, "find successful", headbookService.findbyname(name));
    }

    @GetMapping("headbook/author={name}")
    public EntityResponse<List<HeadBook>> findbyauthor(@PathVariable String name) {
        return new EntityResponse<>(HttpStatus.OK, "find successful", headbookService.findbyauthor(name));
    }

    @GetMapping("headbook/publisher={name}")
    public EntityResponse<List<HeadBook>> findbypublisher(@PathVariable String name) {
        return new EntityResponse<>(HttpStatus.OK, "find successful", headbookService.findbypublish(name));
    }

    @RequestMapping(value = "/headbook/update", method = RequestMethod.PUT)
    public EntityResponse<HeadBook> update(@RequestBody HeadBook book, @RequestParam Long id) {
        headbookService.updateHeadBook(book, id);
        return new EntityResponse<>(HttpStatus.OK, "Update Successful", book);
    }

    @RequestMapping(value = "/headbook/delete", method = RequestMethod.DELETE)
    public EntityResponse<Optional<HeadBook>> delete(@RequestParam Long id) {
        headbookService.deleteHeadBookById(id);
        return new EntityResponse<>(HttpStatus.OK, "Delete successful", headbookService.getHeadBookById(id));
    }
}
