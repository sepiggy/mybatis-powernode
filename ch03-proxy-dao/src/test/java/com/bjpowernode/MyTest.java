package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 测试 Mybatis 的动态代理: SqlSession#getMapper
 */
public class MyTest {

    @Test
    public void testSelectById() {
        // 1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        // 2.获取dao的代理对象
        StudentDao dao = session.getMapper(StudentDao.class);
        Student student = dao.selectById(1001);
        System.out.println("student = " + student);
        // 3.关闭SqlSession对象
        session.close();
    }

    @Test
    public void testSelectStudets() {
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        // com.sun.proxy.$Proxy 等价于自己实现 StudentDaoImpl
        System.out.println("dao===" + dao.getClass().getName());
        List<Student> students = dao.selectStudents();
        students.forEach(stu -> System.out.println("stu=" + stu));
        session.close();
    }

}
