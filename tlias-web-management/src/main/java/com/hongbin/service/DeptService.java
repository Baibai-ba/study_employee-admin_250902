package com.hongbin.service;

import com.hongbin.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> getAllDepts();
    public Dept getDeptById(int id);
    public void deleteDeptById(int id);
    public void insertDept(Dept dept);
    public void updateDept(Dept dept);
    public void deleteEmpsByDeptId(int deptId);
}
