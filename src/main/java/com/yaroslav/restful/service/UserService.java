package com.yaroslav.restful.service;

import com.yaroslav.restful.entity.Book;
import com.yaroslav.restful.entity.User;
import com.yaroslav.restful.repository.BookRepository;
import com.yaroslav.restful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public boolean takeBook(User user, int idBook){

        User userFromDB = userRepository.findUserById(user.getId());
        Book book = bookRepository.findById(idBook);

        if(book.isFree() == true){
            Set<Book> bookSet = userFromDB.getBooks();
            bookSet.add(book);
            userFromDB.setBooks(bookSet);
            book.setFree(false);

            bookRepository.save(book);
            userRepository.save(userFromDB);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnBook(User user, int idBook){

        User userFromDB = userRepository.findUserById(user.getId());
        Book bookFromDB = bookRepository.findById(idBook);

        Set<Book> books = userFromDB.getBooks();
        books.remove(bookRepository.findById(idBook));
        userFromDB.setBooks(books);
        userRepository.save(userFromDB);

        bookFromDB.setFree(true);
        bookRepository.save(bookFromDB);
        return true;
    }
}
