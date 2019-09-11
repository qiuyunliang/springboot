package com.tornado.demo.controller;

import com.github.pagehelper.PageInfo;
import com.tornado.demo.dao.SqlSessionUtil;
import com.tornado.demo.entity.Book;
import com.tornado.demo.entity.Customer;
import com.tornado.demo.mapper.CustomerMapper;
import com.tornado.demo.service.BookServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class BaseController {

    @Autowired
    BookServiceImpl bookService;

    @RequestMapping(method = RequestMethod.GET, path = "/query")
    public String query() {
        SqlSession session = SqlSessionUtil.getInstance().openSession();
        // 方式一
        Customer customer1 = session.selectOne("com.tornado.demo.CustomerMapper.selectCustomer", 1);
        // 方式一
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        Customer customer2 = customerMapper.selectCustomer(1);
        return customer1.toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/user/list")
    public PageInfo<Book> findUserList(int page, int size) {
        PageInfo<Book> pageInfo = bookService.findAll(page, size);
        return pageInfo;
    }
}
