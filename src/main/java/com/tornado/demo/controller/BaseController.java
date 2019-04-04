package com.tornado.demo.controller;

import com.tornado.demo.dao.SqlSessionUtil;
import com.tornado.demo.entity.Book;
import com.tornado.demo.entity.Customer;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @RequestMapping(method = RequestMethod.GET, path = "/query")
    public String query() {
        SqlSession session = SqlSessionUtil.getInstance().openSession();
        // Book customer = session.selectOne("com.tornado.demo.BookMapper.selectBook", 1);
        Customer customer = session.selectOne("com.tornado.demo.CustomerMapper.selectCustomer", 1);
        return customer.toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test";
    }
}
