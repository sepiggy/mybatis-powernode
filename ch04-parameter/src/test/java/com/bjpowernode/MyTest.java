package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.utils.MyBatisUtil;
import com.bjpowernode.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * parameterType 属性
 */
public class MyTest {

    @Test
    public void testSelectById() {
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student student = dao.selectById(1001);
        System.out.println("student = " + student);
        session.close();
    }

    @Test
    public void testOneSimpleParameter() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectByEmail("zs@qq.com");
        System.out.println("email===" + student);
        sqlSession.close();
    }

    @Test
    public void testSelectByNameOrAge() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectByNameOrAge("李四", 26);
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByObject() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setName("李思思");
        student.setAge(26);
        student.setEmail("小乔");
        List<Student> students = dao.selectByObject(student);
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByObject2() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        QueryParam param = new QueryParam();
        param.setP1("东皇"); // 虽然p1的类型是Object,Mybatis通过反射可以获取实际参数类型
        param.setP2(20);
        List<Student> students = dao.selectByQueryParam(param);
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByPosition() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        //按位置传递参数
        List<Student> students = dao.selectByPosition("李四", 20);
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByMap() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        // 使用map传递参数
        Map<String, Object> data = new HashMap<>();
        data.put("myname", "李思思");
        data.put("myage", 20);
        List<Student> students = dao.selectStudentByMap(data);
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testUpdateStudent() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setId(1001);
        student.setName("张峰");
        student.setEmail("ZHANGFENG@qq.com");
        student.setAge(20);
        int rows = dao.updateStudent(student);
        sqlSession.commit();
        System.out.println("更新学生的rows=" + rows);
        sqlSession.close();
    }

}
