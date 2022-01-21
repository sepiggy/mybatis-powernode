package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.utils.MyBatisUtil;
import com.bjpowernode.vo.QueryParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest2 {
    @Test
    public void testQueryStudent() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        // ${}占位符会把内容通过字符串拼接的方式原样不动的拼接到sql语句上,因此下面这行语句需要手动加上单引号''
        List<Student> students = dao.queryStudent("'李四' or id > 0 ");
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.queryStudentOrderById();
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderByName() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.queryStudentOrderByName();
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderByColName() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        /**
        相应的 Dao 层代码:
        List<Student> queryStudentOrderByColName(@Param("myname") String name,
                @Param("colName") String colName,
                @Param("tableName") String tableName);

        <select id="queryStudentOrderByColName" resultType="com.bjpowernode.domain.Student">
            select *
            from ${tableName}
            where name = #{myname}
            order by ${colName} desc
        </select>
        **/
        List<Student> students = dao.queryStudentOrderByColName("张飞", "id", "student");
        students.forEach(stu -> System.out.println("stu=" + stu));
        sqlSession.close();
    }

}
