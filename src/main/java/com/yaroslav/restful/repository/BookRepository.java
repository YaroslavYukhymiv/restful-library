package com.yaroslav.restful.repository;

import com.yaroslav.restful.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    public Book findById(int id);
}
