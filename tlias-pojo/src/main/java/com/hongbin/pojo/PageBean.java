package com.hongbin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;  //总记录数
    private List<Emp> rows; //当前页面数据
}
