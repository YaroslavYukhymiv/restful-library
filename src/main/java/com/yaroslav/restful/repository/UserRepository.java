package com.yaroslav.restful.repository;

import com.yaroslav.restful.entity.Book;
import com.yaroslav.restful.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findUserById(int id);
}
