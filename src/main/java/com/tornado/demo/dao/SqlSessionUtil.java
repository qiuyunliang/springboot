package com.tornado.demo.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public final class SqlSessionUtil implements SqlSessionFactory {

    // 应用声明周期只存在唯一的SQL Session 工厂
    private SqlSessionFactory factory;

    private SqlSessionUtil() {
        String resource = "mybatis/conf/mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final class SqlSessionUtilHolder {
        private static final SqlSessionUtil INSTANCE = new SqlSessionUtil();
    }

    public static SqlSessionUtil getInstance() {
        return SqlSessionUtilHolder.INSTANCE;
    }

    public SqlSession openSession() {
        return factory.openSession();
    }

    @Override
    public SqlSession openSession(boolean b) {
        return factory.openSession(b);
    }

    @Override
    public SqlSession openSession(Connection connection) {
        return factory.openSession(connection);
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel transactionIsolationLevel) {
        return factory.openSession(transactionIsolationLevel);
    }

    @Override
    public SqlSession openSession(ExecutorType executorType) {
        return factory.openSession(executorType);
    }

    @Override
    public SqlSession openSession(ExecutorType executorType, boolean b) {
        return factory.openSession(executorType, b);
    }

    @Override
    public SqlSession openSession(ExecutorType executorType, TransactionIsolationLevel transactionIsolationLevel) {
        return factory.openSession(executorType, transactionIsolationLevel);
    }

    @Override
    public SqlSession openSession(ExecutorType executorType, Connection connection) {
        return factory.openSession(executorType, connection);
    }

    @Override
    public Configuration getConfiguration() {
        return factory.getConfiguration();
    }
}
