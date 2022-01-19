package com.bjpowernode.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {

    // 属性名和列名一样
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}
