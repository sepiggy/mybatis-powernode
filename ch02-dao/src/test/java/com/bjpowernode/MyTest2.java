package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.dao.impl.StudentDaoImpl;
import com.bjpowernode.domain.Student;
import org.junit.Test;

import java.util.List;

/**
 * 通过 dao 接口的实现类直接操作 java 对象，进而操作持久层
 */
public class MyTest2 {

    /**
     * Mybatis动态代理的原理解析:
     *
     * 通过以下两行语句就可以执行一条sql查询
     * String sqlId = "com.bjpowernode.dao.StudentDao.selectById";
     * Student student = sqlSession.selectOne(sqlId, id);
     * 也就是在mybatis中只要知道sqlId和调用sqlSession的哪个方法(selectOne, selectList ...)就可以执行一条sql语句
     *
     * 1. 如何确定sqlId ?
     * 在dao分层结构中调用dao的语句:
     * Student student  = dao.selectById(1001);
     * 1)dao: 通过反射能够得到全限定类型名称
     * dao是StudentDao类型的,全限定名称是 com.bjpowernode.dao.StudentDao
     * 2)selectById: dao中的方法名称, 约定方法名称就是mapper文件中标签的id
     * 所以通过dao.selectById()能得到 sqlId="com.bjpowernode.dao.StudentDao.selectById";
     *
     * 2. 如何确定调用SqlSession的哪个方法 ?
     * 1) 根据dao接口的方法返回值类型确定:
     *    如果返回值类型是普通对象类型,例如Student,调用SqlSession#selectOne
     *    如果返回值类型是List类型,调用SqlSession#selectList
     * 2) 根据mapper文件中的标签，如果标签是<insert>, 调用SqlSession#insert
     *
     * mybatis框架，如果发现使用dao的接口调用能确定执行sql语句的必要信息,mybatis会简化dao对象的实现。
     * 由mybatis框架在程序执行期间, 根据你的Dao接口, 创建一个内存中的接口的实现类对象 (通过动态代理实现)
     * mybatis把这个技术叫做dao技术(动态代理,dao的动态代理).
     *
     * dao动态代理技术: 由mybatis创建 StudentDao接口的实现类 Proxy(StudentDaoImpl)
     * 使用框架创建的StudentDaoImpl代替 "你自己手工实现的StudentDaoImpl类的功能"，
     * 不用开发人员写dao接口的实现类。
     *
     * 使用dao的代理要求 (约定):
     * 1. mapper文件中的namespace必须是dao接口的全限定名称
     * 2. mapper文件中标签的id必须是dao接口中的方法名称
     */
    @Test
    public void testSelectOne() {
        // 要使用dao的方法
        // 接口类型  变量 =  new 接口的实现类();
        StudentDao dao = new StudentDaoImpl();
        Student student = dao.selectById(1001);
        System.out.println("通过dao执行方法，的到的对象==" + student);
    }

    @Test
    public void testSelectStudents() {
        StudentDao dao = new StudentDaoImpl();
        List<Student> students = dao.selectStudents();
        students.forEach(stu -> System.out.println("stu=" + stu));
    }

    @Test
    public void testInsert() {
        StudentDao dao = new StudentDaoImpl();

        Student student = new Student();
        student.setId(1009);
        student.setName("周强");
        student.setEmail("zhouqiang@qq.com");
        student.setAge(28);

        dao.insertStudent(student);
    }
}
