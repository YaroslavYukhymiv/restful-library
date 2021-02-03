package com.yaroslav.restful.controller;

import com.yaroslav.restful.entity.Book;
import com.yaroslav.restful.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public Iterable<Book> getBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam int id){
        return bookRepository.findById(id);
    }

    @PostMapping("/book")
    public ResponseEntity saveBook(@RequestBody Book book){
        bookRepository.save(book);
        return new ResponseEntity("Book has saved", HttpStatus.CREATED);
    }

    @PutMapping("/book")
    public ResponseEntity updateBook(@RequestBody Book book){
        bookRepository.save(book);
        return new ResponseEntity("Book: " + book.getId() + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/book")
    public ResponseEntity deleteBook(@RequestParam int id){
        bookRepository.deleteById(id);
        return new ResponseEntity("Book id: " + id + "has been daleted", HttpStatus.OK);
    }
}
