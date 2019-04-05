package com.tornado.demo.service;

import com.github.pagehelper.PageInfo;
import com.tornado.demo.entity.Book;

import java.util.List;

public interface BookService {
    void save(Book book);

    PageInfo<Book> findAll(int page, int size);

    List<Book> findAll();
}
