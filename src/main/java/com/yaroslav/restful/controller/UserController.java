package com.yaroslav.restful.controller;

import com.yaroslav.restful.entity.Book;
import com.yaroslav.restful.entity.User;
import com.yaroslav.restful.repository.BookRepository;
import com.yaroslav.restful.repository.UserRepository;
import com.yaroslav.restful.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    Logger logger =  LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Iterable<User> getUsers(){
        logger.trace("Users: " + userRepository.findAll() + "");
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public Optional<User> userById(@RequestParam int id){
        logger.trace("User: " + userRepository.findUserById(id));
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user){
        userRepository.save(user);
        logger.trace("User: " + userRepository.findUserById(user.getId()));
        return new ResponseEntity("User saved", HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody User user){
        userRepository.save(user);
        logger.trace("User: " + userRepository.findUserById(user.getId()));
        return new ResponseEntity("User updated", HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity deletUserById(@RequestParam int id){
        userRepository.deleteById(id);
        return new ResponseEntity("User " + id + " deleted", HttpStatus.OK );
    }

    @RequestMapping(value = "/take", method = RequestMethod.PUT)
    public ResponseEntity userTakeBook(@RequestBody User user, @RequestParam int idBook){

        if(userService.takeBook(user, idBook)){
            logger.trace("User: " + userRepository.findUserById(user.getId()));
            return new ResponseEntity("User has taken a book", HttpStatus.OK);
        } else {
            return new ResponseEntity("The book has been taken erlier", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/return", method = RequestMethod.PUT)
    public ResponseEntity returnBook(@RequestBody User user, @RequestParam int idBook){
        if(userService.returnBook(user, idBook)){
            logger.trace("User: " + userRepository.findUserById(user.getId()));
            return new ResponseEntity("Book was returned", HttpStatus.OK);
        } else {
            return new ResponseEntity("Happed a mistak", HttpStatus.BAD_REQUEST);
        }
    }
}
