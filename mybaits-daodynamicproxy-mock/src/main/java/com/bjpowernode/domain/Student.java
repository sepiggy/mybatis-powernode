package com.bjpowernode.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}
