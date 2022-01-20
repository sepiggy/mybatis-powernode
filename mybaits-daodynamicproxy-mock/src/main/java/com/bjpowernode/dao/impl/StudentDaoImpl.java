package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 引入 dao 层实现持久层操作
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public Student selectById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String sqlId = "com.bjpowernode.dao.StudentDao.selectById";
        Student student = sqlSession.selectOne(sqlId, id);
        sqlSession.close();
        return student;
    }

    @Override
    public List<Student> selectStudents() {
        SqlSession session = MyBatisUtil.getSqlSession();
        String sqlId = "com.bjpowernode.dao.StudentDao.selectStudents";
        List<Student> students = session.selectList(sqlId);
        session.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {
        SqlSession session = MyBatisUtil.getSqlSession();
        String sqlId = "com.bjpowernode.dao.StudentDao.insertStudent";
        int rows = session.insert(sqlId, student);
        session.commit();
        System.out.println("insert的行数===" + rows);
        session.close();
        return rows;
    }
}
