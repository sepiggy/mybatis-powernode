package com.bjpowernode.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 工具类：创建SqlSession对象
 */
public class MyBatisUtil {

    private MyBatisUtil() {
    }

    private static SqlSessionFactory factory = null;

    // 通过静态代码块在虚拟机加载的时候初始化 SqlSessionFactory 对象
    static {
        String config = "mybatis.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(config);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 创建方法，获取SqlSession对象
    public static SqlSession getSqlSession() {
        SqlSession session = null;
        if (factory != null) {
            session = factory.openSession(); // openSession(true);
        }
        return session;
    }
}
