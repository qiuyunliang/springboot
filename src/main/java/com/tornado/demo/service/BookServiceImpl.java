package com.tornado.demo.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tornado.demo.dao.SqlSessionUtil;
import com.tornado.demo.entity.Book;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public void save(Book book) {
        SqlSession session = SqlSessionUtil.getInstance().openSession();
        session.insert("com.tornado.demo.BookMapper.insertBook", book);
        session.selectOne("com.tornado.demo.BookMapper.selectBook", 1);
        return;
    }

    @Override
    public PageInfo<Book> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        SqlSession session = SqlSessionUtil.getInstance().openSession();
        List<Book> list = session.selectList("com.tornado.demo.BookMapper.selectBook");
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        // 具体使用
        redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        stringRedisTemplate.opsForValue().set("user:name", "张三");
        return pageInfo;
    }

    @Override
    public List<Book> findAll() {
        SqlSession session = SqlSessionUtil.getInstance().openSession();
        List<Book> list = session.selectList("com.tornado.demo.BookMapper.selectBook");
        return list;
    }
}
