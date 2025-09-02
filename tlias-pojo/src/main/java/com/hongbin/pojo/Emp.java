package com.hongbin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender; //性别，1 男，2 女
    private String image;
    private Short job; //职位，1 班主任，2 讲师，3 学工主管，4 教研主管，5 咨询师
    private LocalDate entrydate;
    private Integer deptId; //部门id
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
