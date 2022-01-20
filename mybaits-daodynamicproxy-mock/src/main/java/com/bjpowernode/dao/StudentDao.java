package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;

import java.util.List;

public interface StudentDao {

    Student selectById(Integer id);

    List<Student> selectStudents();

    int insertStudent(Student student);
}
