package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;
import com.bjpowernode.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 演示 mybatis 获取方法参数
 */
public interface StudentDao {

    // 重载方法
    Student selectById(Integer id);

    // dao 接口的方法形参是一个简单类型的
    // 简单类型: java基本数据类型和String
    Student selectByEmail(String email);

    /*
      多个简单类型的参数
      使用@Param命名参数， 注解是mybatis提供的
      位置：在形参定义的前面
      属性：value 自定义的参数名称
     */
    List<Student> selectByNameOrAge(@Param("myname") String name,
                                    @Param("myage") Integer age);

    // 使用一个业务对象作为参数 (必须有set, get方法)
    List<Student> selectByObject(Student student);

    // 使用一个自定义对象作为参数
    List<Student> selectByQueryParam(QueryParam param);

    // 在Mapper文件中使用位置获取参数
    List<Student> selectByPosition(String name, Integer age);

    // 使用Map作为参数
    List<Student> selectStudentByMap(Map<String, Object> map);

    // 不仅能对select语句传参,insert,update,delete语句都可以
    int updateStudent(Student student);

    // ${}占位符的使用必须使用@Param指定命名
    List<Student> queryStudent(@Param("studentName") String name);

    List<Student> queryStudentOrderById();

    List<Student> queryStudentOrderByName();

    List<Student> queryStudentOrderByColName(@Param("myname") String name,
                                             @Param("colName") String colName,
                                             @Param("tableName") String tableName);
}
