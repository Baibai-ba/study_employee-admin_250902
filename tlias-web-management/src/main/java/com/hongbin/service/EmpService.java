package com.hongbin.service;

import com.hongbin.pojo.Emp;
import com.hongbin.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    public List<Emp> getEmpsList(String name, Short gender, LocalDate beginScope, LocalDate endScope,Integer pageNo, Integer rows);
//    public Long getEmpsCount(String name, Short gender, LocalDate beginScope, LocalDate endScope);
    public PageBean searchEmpReturnPageBean(String name, Short gender, LocalDate beginScope, LocalDate endScope, Integer pageNo, Integer rows);
    public int deleteEmpsByIds(List<Integer> ids);
    public int insertEmp(Emp emp);
    public Emp getEmpById(Integer id);
    public int updateEmp(Emp emp);
    public Emp loginCheck(Emp emp);
}
