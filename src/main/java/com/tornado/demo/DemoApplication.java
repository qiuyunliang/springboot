package com.tornado.demo;

import com.tornado.demo.dao.SqlSessionUtil;
import com.tornado.demo.entity.Coffee;
import com.tornado.demo.mapper.CoffeeMapper;
import com.tornado.demo.service.BookServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

@Log4j2
@SpringBootApplication
@MapperScan("com.tornado.demo.mapper")
public class DemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(jedisPoolConfig.toString());

//        try (Jedis jedis = jedisPool.getResource()) {
//            bookService.findAll().forEach(c -> jedis.hset("book", String.valueOf(c.getId()), c.getName()));
//
//            Map<String, String> menu = jedis.hgetAll("book");
//            log.info("books: {}", menu);
//
//            String price = jedis.hget("book", "1");
//            log.info("book name: {}", price);
//        }

        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
//        CoffeeMapper coffeeMapper = SqlSessionUtil.getInstance().openSession().getMapper(CoffeeMapper.class);
        Long id = coffeeMapper.save(c);
        log.info("Coffee {} => {}", id, c);

        c = coffeeMapper.findById(id);
        log.info("Coffee {}", c);
    }
}
